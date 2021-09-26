package com.mylovin.spring.cycledeps.advanced;

import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Constructor;
import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class AdvancedMain {
    // 模拟beanFactory作用，存储所有bean的RootBeanDefinition结构
    private Map<String, RootBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 一级缓存
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 二级缓存
     */
    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

    /**
     * 三级缓存
     */
    private final Map<String, MyObjectFactory<?>> singletonFactories = new HashMap<>(16);

    /**
     * 当前正在创建中的单例名称
     */
    private final Set<String> singletonsCurrentlyInCreation =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    private final Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>(64);

    private final Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>(64);

    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<>(256));

    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);

    private final Set<String> registeredSingletons = new LinkedHashSet<>(256);

    private final ConcurrentMap<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    private boolean singletonsCurrentlyInDestruction = false;

    private boolean allowCircularReferences = true;

    private boolean allowRawInjectionDespiteWrapping = false;

    @Nullable
    private volatile BeanPostProcessorCache beanPostProcessorCache;

    private final List<BeanPostProcessor> beanPostProcessors = new BeanPostProcessorCacheAwareList();

    private final Set<PropertyEditorRegistrar> propertyEditorRegistrars = new LinkedHashSet<>(4);

    private final Set<String> inCreationCheckExclusions =
            Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    private final Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>(4);

    /**
     * 规范化
     * canonicalName -> resolvedName
     */
    private final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    /**
     * 读取bean定义, 当然在spring中肯定是根据配置 动态扫描注册的
     * <p>
     * InstanceA和InstanceB都有注解@Component, 所以, 在spring扫描读取配置类的时候, 会把他们两个扫描到BeanDefinitionMap中.
     * 这里, 我们省略这一步, 直接将instanceA和instanceB放到BeanDefinitionMap中.
     */
    private void loadBeanDefinations() {
        RootBeanDefinition instanceABeanDefination = new RootBeanDefinition(InstanceA.class);
        RootBeanDefinition instanceBBeanDefination = new RootBeanDefinition(InstanceB.class);

        beanDefinitionMap.put("instanceA", instanceABeanDefination);
        beanDefinitionMap.put("instanceB", instanceBBeanDefination);
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }

    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        // Quick check for existing instance without full singleton lock
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                synchronized (this.singletonObjects) {
                    // Consistent creation of early reference within full singleton lock
                    singletonObject = this.singletonObjects.get(beanName);
                    if (singletonObject == null) {
                        singletonObject = this.earlySingletonObjects.get(beanName);
                        if (singletonObject == null) {
                            MyObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                            if (singletonFactory != null) {
                                singletonObject = singletonFactory.getObject();
                                this.earlySingletonObjects.put(beanName, singletonObject);
                                this.singletonFactories.remove(beanName);
                            }
                        }
                    }
                }
            }
        }
        return singletonObject;
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }
    }

    protected void beforeSingletonCreation(String beanName) {
        if (!this.inCreationCheckExclusions.contains(beanName) && !this.singletonsCurrentlyInCreation.add(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }
    }

    public Object getSingleton(String beanName, MyObjectFactory<?> singletonFactory) {
        Assert.notNull(beanName, "Bean name must not be null");
        synchronized (this.singletonObjects) {
            Object singletonObject = this.singletonObjects.get(beanName);
            if (singletonObject == null) {
                if (this.singletonsCurrentlyInDestruction) {
                    throw new BeanCreationNotAllowedException(beanName,
                            "Singleton bean creation not allowed while singletons of this factory are in destruction " +
                                    "(Do not request a bean from a BeanFactory in a destroy method implementation!)");
                }
                // 这一步很重要，singletonsCurrentlyInCreation 标记正在创建中的bean
                beforeSingletonCreation(beanName);
                boolean newSingleton = false;
                singletonObject = singletonFactory.getObject();
                newSingleton = true;
                if (newSingleton) {
                    addSingleton(beanName, singletonObject);
                }
            }
            return singletonObject;
        }
    }

    protected Class<?> resolveBeanClass(RootBeanDefinition mbd, String beanName, Class<?>... typesToMatch) {
        return mbd.getBeanClass();
    }

    // 代理
/*    protected Object resolveBeforeInstantiation(String beanName, RootBeanDefinition mbd) {
        Object bean = null;
        if (!Boolean.FALSE.equals(mbd.beforeInstantiationResolved)) {
            // Make sure bean class is actually resolved at this point.
            if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
                Class<?> targetType = determineTargetType(beanName, mbd);
                if (targetType != null) {
                    bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
                    if (bean != null) {
                        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
                    }
                }
            }
            mbd.beforeInstantiationResolved = (bean != null);
        }
        return bean;
    }*/

    public Object instantiate(RootBeanDefinition bd, @Nullable String beanName) {
        // Don't override the class with CGLIB if no overrides.
        Constructor<?> constructorToUse;
        synchronized (bd.getBeanClass()) {
            constructorToUse = null;
            if (constructorToUse == null) {
                final Class<?> clazz = bd.getBeanClass();
                if (clazz.isInterface()) {
                    throw new BeanInstantiationException(clazz, "Specified class is an interface");
                }
                try {
                    if (System.getSecurityManager() != null) {
                        constructorToUse = AccessController.doPrivileged(
                                (PrivilegedExceptionAction<Constructor<?>>) clazz::getDeclaredConstructor);
                    } else {
                        constructorToUse = clazz.getDeclaredConstructor();
                    }
                } catch (Throwable ex) {
                    throw new BeanInstantiationException(clazz, "No default constructor found", ex);
                }
            }
        }
        return BeanUtils.instantiateClass(constructorToUse);
    }

    protected boolean isActuallyInCreation(String beanName) {
        return isSingletonCurrentlyInCreation(beanName);
    }

    public boolean isCurrentlyInCreation(String beanName) {
        Assert.notNull(beanName, "Bean name must not be null");
        return (!this.inCreationCheckExclusions.contains(beanName) && isActuallyInCreation(beanName));
    }

    protected void registerCustomEditors(PropertyEditorRegistry registry) {
        if (registry instanceof PropertyEditorRegistrySupport) {
            ((PropertyEditorRegistrySupport) registry).useConfigValueEditors();
        }
        if (!this.propertyEditorRegistrars.isEmpty()) {
            for (PropertyEditorRegistrar registrar : this.propertyEditorRegistrars) {
                try {
                    registrar.registerCustomEditors(registry);
                } catch (BeanCreationException ex) {
                    Throwable rootCause = ex.getMostSpecificCause();
                    if (rootCause instanceof BeanCurrentlyInCreationException) {
                        BeanCreationException bce = (BeanCreationException) rootCause;
                        String bceBeanName = bce.getBeanName();
                        if (bceBeanName != null && isCurrentlyInCreation(bceBeanName)) {
                            continue;
                        }
                    }
                    throw ex;
                }
            }
        }
        if (!this.customEditors.isEmpty()) {
            this.customEditors.forEach((requiredType, editorClass) ->
                    registry.registerCustomEditor(requiredType, BeanUtils.instantiateClass(editorClass)));
        }
    }

    protected void initBeanWrapper(BeanWrapper bw) {
        registerCustomEditors(bw);
    }

    protected BeanWrapper instantiateBean(String beanName, RootBeanDefinition mbd) {
        try {
            Object beanInstance;
            beanInstance = instantiate(mbd, beanName);
            BeanWrapper bw = new BeanWrapperImpl(beanInstance);
            initBeanWrapper(bw);
            return bw;
        } catch (Throwable ex) {
            throw new BeanCreationException(
                    mbd.getResourceDescription(), beanName, "Instantiation of bean failed", ex);
        }
    }

    protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd) {
        return instantiateBean(beanName, mbd);
    }

    protected void addSingletonFactory(String beanName, MyObjectFactory<?> singletonFactory) {
        Assert.notNull(singletonFactory, "Singleton factory must not be null");
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
                this.registeredSingletons.add(beanName);
            }
        }
    }

    BeanPostProcessorCache getBeanPostProcessorCache() {
        BeanPostProcessorCache bpCache = this.beanPostProcessorCache;
        if (bpCache == null) {
            bpCache = new BeanPostProcessorCache();
            for (BeanPostProcessor bp : this.beanPostProcessors) {
                if (bp instanceof InstantiationAwareBeanPostProcessor) {
                    bpCache.instantiationAware.add((InstantiationAwareBeanPostProcessor) bp);
                    if (bp instanceof SmartInstantiationAwareBeanPostProcessor) {
                        bpCache.smartInstantiationAware.add((SmartInstantiationAwareBeanPostProcessor) bp);
                    }
                }
                if (bp instanceof DestructionAwareBeanPostProcessor) {
                    bpCache.destructionAware.add((DestructionAwareBeanPostProcessor) bp);
                }
                if (bp instanceof MergedBeanDefinitionPostProcessor) {
                    bpCache.mergedDefinition.add((MergedBeanDefinitionPostProcessor) bp);
                }
            }
            this.beanPostProcessorCache = bpCache;
        }
        return bpCache;
    }

    protected boolean hasInstantiationAwareBeanPostProcessors() {
        return !getBeanPostProcessorCache().instantiationAware.isEmpty();
    }

    protected Object getEarlyBeanReference(String beanName, RootBeanDefinition mbd, Object bean) {
        Object exposedObject = bean;
        if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
            for (SmartInstantiationAwareBeanPostProcessor bp : getBeanPostProcessorCache().smartInstantiationAware) {
                exposedObject = bp.getEarlyBeanReference(exposedObject, beanName);
            }
        }
        return exposedObject;
    }

    protected boolean containsBean(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    public String canonicalName(String name) {
        String canonicalName = name;
        // Handle aliasing...
        String resolvedName;
        do {
            resolvedName = this.aliasMap.get(canonicalName);
            if (resolvedName != null) {
                canonicalName = resolvedName;
            }
        }
        while (resolvedName != null);
        return canonicalName;
    }

    public void registerDependentBean(String beanName, String dependentBeanName) {
        String canonicalName = canonicalName(beanName);

        synchronized (this.dependentBeanMap) {
            Set<String> dependentBeans =
                    this.dependentBeanMap.computeIfAbsent(canonicalName, k -> new LinkedHashSet<>(8));
            if (!dependentBeans.add(dependentBeanName)) {
                return;
            }
        }

        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean =
                    this.dependenciesForBeanMap.computeIfAbsent(dependentBeanName, k -> new LinkedHashSet<>(8));
            dependenciesForBean.add(canonicalName);
        }
    }

    protected void autowireByName(String beanName, AbstractBeanDefinition mbd, BeanWrapper bw, MutablePropertyValues pvs) {
        Set<String> result = new TreeSet<>();
        PropertyDescriptor[] pds = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            result.add(pd.getName());
        }
        String[] propertyNames = StringUtils.toStringArray(result);

        for (String propertyName : propertyNames) {
            if (containsBean(propertyName)) {
                Object bean = getBean(propertyName);
                pvs.add(propertyName, bean);
                registerDependentBean(propertyName, beanName);
            } else {

            }
        }
    }

    protected void populateBean(String beanName, RootBeanDefinition mbd, @Nullable BeanWrapper bw) {
        PropertyValues pvs = (mbd.hasPropertyValues() ? mbd.getPropertyValues() : null);
        MutablePropertyValues newPvs = new MutablePropertyValues(pvs);
        autowireByName(beanName, mbd, bw, newPvs);
    }

    protected boolean hasDependentBean(String beanName) {
        return this.dependentBeanMap.containsKey(beanName);
    }

    public String[] getDependentBeans(String beanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans == null) {
            return new String[0];
        }
        synchronized (this.dependentBeanMap) {
            return StringUtils.toStringArray(dependentBeans);
        }
    }

    protected void superrRemoveSingleton(String beanName) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.remove(beanName);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.remove(beanName);
        }
    }

    protected void removeSingleton(String beanName) {
        synchronized (singletonObjects) {
            superrRemoveSingleton(beanName);
            this.factoryBeanObjectCache.remove(beanName);
        }
    }

    protected boolean removeSingletonIfCreatedForTypeCheckOnly(String beanName) {
        if (!this.alreadyCreated.contains(beanName)) {
            removeSingleton(beanName);
            return true;
        } else {
            return false;
        }
    }

    protected Object doCreateBean(String beanName, RootBeanDefinition mbd)
            throws BeanCreationException {

        // Instantiate the bean.
        BeanWrapper instanceWrapper = null;
        if (mbd.isSingleton()) {
            instanceWrapper = this.factoryBeanInstanceCache.remove(beanName);
        }
        if (instanceWrapper == null) {
            instanceWrapper = createBeanInstance(beanName, mbd);
        }
        Object bean = instanceWrapper.getWrappedInstance();
        Class<?> beanType = instanceWrapper.getWrappedClass();

        // Eagerly cache singletons to be able to resolve circular references
        // even when triggered by lifecycle interfaces like BeanFactoryAware.
        boolean earlySingletonExposure = (mbd.isSingleton() && this.allowCircularReferences &&
                isSingletonCurrentlyInCreation(beanName));
        if (earlySingletonExposure) {
            addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, mbd, bean));
        }

        // Initialize the bean instance.
        Object exposedObject = bean;
        try {
            populateBean(beanName, mbd, instanceWrapper);
            //exposedObject = initializeBean(beanName, exposedObject, mbd);
        } catch (Throwable ex) {
            if (ex instanceof BeanCreationException && beanName.equals(((BeanCreationException) ex).getBeanName())) {
                throw (BeanCreationException) ex;
            } else {
                throw new BeanCreationException(
                        mbd.getResourceDescription(), beanName, "Initialization of bean failed", ex);
            }
        }

        if (earlySingletonExposure) {
            Object earlySingletonReference = getSingleton(beanName, false);
            if (earlySingletonReference != null) {
                if (exposedObject == bean) {
                    exposedObject = earlySingletonReference;
                } else if (!this.allowRawInjectionDespiteWrapping && hasDependentBean(beanName)) {
                    String[] dependentBeans = getDependentBeans(beanName);
                    Set<String> actualDependentBeans = new LinkedHashSet<>(dependentBeans.length);
                    for (String dependentBean : dependentBeans) {
                        if (!removeSingletonIfCreatedForTypeCheckOnly(dependentBean)) {
                            actualDependentBeans.add(dependentBean);
                        }
                    }
                    if (!actualDependentBeans.isEmpty()) {
                        throw new BeanCurrentlyInCreationException(beanName,
                                "Bean with name '" + beanName + "' has been injected into other beans [" +
                                        StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +
                                        "] in its raw version as part of a circular reference, but has eventually been " +
                                        "wrapped. This means that said other beans do not use the final version of the " +
                                        "bean. This is often the result of over-eager type matching - consider using " +
                                        "'getBeanNamesForType' with the 'allowEagerInit' flag turned off, for example.");
                    }
                }
            }
        }
        return exposedObject;
    }

    protected Object createBean(String beanName, RootBeanDefinition mbd)
            throws BeanCreationException {
        RootBeanDefinition mbdToUse = mbd;

        Class<?> resolvedClass = resolveBeanClass(mbd, beanName);
        if (resolvedClass != null && !mbd.hasBeanClass() && mbd.getBeanClassName() != null) {
            mbdToUse = new RootBeanDefinition(mbd);
            mbdToUse.setBeanClass(resolvedClass);
        }
        // Prepare method overrides.
        try {
            mbdToUse.prepareMethodOverrides();
        } catch (BeanDefinitionValidationException ex) {
            throw new BeanDefinitionStoreException(mbdToUse.getResourceDescription(),
                    beanName, "Validation of method overrides failed", ex);
        }

        try {
            // TODO 代理
            // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
            /*Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
            if (bean != null) {
                return bean;
            }*/
        } catch (Throwable ex) {
            throw new BeanCreationException(mbdToUse.getResourceDescription(), beanName,
                    "BeanPostProcessor before instantiation of bean failed", ex);
        }

        try {
            Object beanInstance = doCreateBean(beanName, mbdToUse);
            return beanInstance;
        } catch (BeanCreationException ex) {
            // A previously detected exception with proper bean creation context already,
            // or illegal singleton state to be communicated up to DefaultSingletonBeanRegistry.
            throw ex;
        } catch (Throwable ex) {
            throw new BeanCreationException(
                    mbdToUse.getResourceDescription(), beanName, "Unexpected exception during bean creation", ex);
        }
    }

    public Object getBean(String beanName) {
        Object sharedInstance = getSingleton(beanName, true);
        if (sharedInstance != null) {
            return sharedInstance;
        }
        RootBeanDefinition mbd = beanDefinitionMap.get(beanName);
        if (mbd.isSingleton()) {
            sharedInstance = getSingleton(beanName, () -> {
                try {
                    return createBean(beanName, mbd);
                } catch (BeansException ex) {
                    // Explicitly remove instance from singleton cache: It might have been put there
                    // eagerly by the creation process, to allow for circular reference resolution.
                    // Also remove any beans that received a temporary reference to the bean.
                    //destroySingleton(beanName);
                    throw ex;
                }
            });
        }
        return sharedInstance;
    }

    public static void main(String[] args) {
        AdvancedMain main = new AdvancedMain();

        // 模拟spring加载第一步，扫描类，loadBeanDefinations
        // 如果是annotation注解，在ConfigurationClassParser#processConfigBeanDefinitions->ComponentScanAnnotationParser#parse->ClassPathBeanDefinitionScanner#doScan中解析文件
        main.loadBeanDefinations();

        // 模拟spring加载第二步，获取类对象，getBean，核心
        InstanceA instanceA = (InstanceA) main.getBean("instanceA");
        InstanceB instanceB = (InstanceB) main.getBean("instanceB");
    }

    static class BeanPostProcessorCache {

        final List<InstantiationAwareBeanPostProcessor> instantiationAware = new ArrayList<>();

        final List<SmartInstantiationAwareBeanPostProcessor> smartInstantiationAware = new ArrayList<>();

        final List<DestructionAwareBeanPostProcessor> destructionAware = new ArrayList<>();

        final List<MergedBeanDefinitionPostProcessor> mergedDefinition = new ArrayList<>();
    }

    private class BeanPostProcessorCacheAwareList extends CopyOnWriteArrayList<BeanPostProcessor> {

        @Override
        public BeanPostProcessor set(int index, BeanPostProcessor element) {
            BeanPostProcessor result = super.set(index, element);
            beanPostProcessorCache = null;
            return result;
        }

        @Override
        public boolean add(BeanPostProcessor o) {
            boolean success = super.add(o);
            beanPostProcessorCache = null;
            return success;
        }

        @Override
        public void add(int index, BeanPostProcessor element) {
            super.add(index, element);
            beanPostProcessorCache = null;
        }

        @Override
        public BeanPostProcessor remove(int index) {
            BeanPostProcessor result = super.remove(index);
            beanPostProcessorCache = null;
            return result;
        }

        @Override
        public boolean remove(Object o) {
            boolean success = super.remove(o);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean success = super.removeAll(c);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            boolean success = super.retainAll(c);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public boolean addAll(Collection<? extends BeanPostProcessor> c) {
            boolean success = super.addAll(c);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public boolean addAll(int index, Collection<? extends BeanPostProcessor> c) {
            boolean success = super.addAll(index, c);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public boolean removeIf(Predicate<? super BeanPostProcessor> filter) {
            boolean success = super.removeIf(filter);
            if (success) {
                beanPostProcessorCache = null;
            }
            return success;
        }

        @Override
        public void replaceAll(UnaryOperator<BeanPostProcessor> operator) {
            super.replaceAll(operator);
            beanPostProcessorCache = null;
        }
    }
}
