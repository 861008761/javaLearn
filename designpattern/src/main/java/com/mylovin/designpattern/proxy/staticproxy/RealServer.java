package com.mylovin.designpattern.proxy.staticproxy;

public class RealServer implements Server {
    @Override
    public void visit(String url) {
        response(url);
    }

    public void response(String url) {
        System.out.println("实际服务器收到url：" + url + ", 返回数据");
    }
}
