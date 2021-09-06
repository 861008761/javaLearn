package com.mylovin.designpattern.proxy.staticproxy;

public class ProxyServer implements Server {
    private Server server;

    /**
     * 通过传值的形式，把实列化对象传过来。（理解为装饰器模式了）
     * 要区别一下，代理模式是提供完全相同的接口(如这里的visit)，而装饰器模式是为了增强接口。
     *
     * @param server
     */
    public ProxyServer(Server server) {
        this.server = server;
    }

    /**
     * 采用类加载器形式，去加载实列对象，这样我们就不同关心到底什么时候需要真实的实列化对象
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ProxyServer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.server = (Server) this.getClass().getClassLoader().loadClass("com.mylovin.designpattern.proxy.staticproxy.RealServer").newInstance();
    }

    @Override
    public void visit(String url) {
        System.out.println("代理转发请求");
        this.server.visit(url);
    }
}
