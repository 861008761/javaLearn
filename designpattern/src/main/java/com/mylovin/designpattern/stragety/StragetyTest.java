package com.mylovin.designpattern.stragety;

/**
 * 运行时更改类的行为或算法，从而达到修改其功能的目的；
 * <p>
 * 使用场景： 一个系统需要动态地在几种算法中选择一种，而这些算法之间仅仅是他们的行为不同。 此外决策过程中过多的出现if else，也可以考虑使用该模式。
 * <p>
 * 实现：将这些算法封装成可单独运行的类，由使用者根据需要进行替换。
 * <p>
 * 优点： 较为灵活，扩展性好，避免大量的if else结构。
 * <p>
 * 缺点： 对外暴露了类所有的行为和算法，行为过多导致策略类膨胀。
 * <p>
 * 框架的应用
 * 策略模式在框架中也在一个很常见的地方体现出来了，而且大家肯定都有使用过。
 * <p>
 * 那就是JDK中的线程池ThreadPoolExecutor
 */
public class StragetyTest {
    public static void main(String[] args) {
        Winrar winrar = new Winrar(new Rapid());
        winrar.compression();

        winrar.setCompression(new Efficient());
        winrar.compression();

        winrar.setCompression(new Encrypt());
        winrar.compression();
    }
}
