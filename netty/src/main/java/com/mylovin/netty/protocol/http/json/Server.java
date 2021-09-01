package com.mylovin.netty.protocol.http.json;

public class Server {
    public void run(String host, int port) {

    }

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server();
        server.run("", port);
    }
}
