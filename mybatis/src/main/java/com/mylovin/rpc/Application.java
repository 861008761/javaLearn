package com.mylovin.rpc;

import com.mylovin.rpc.annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RpcScan("com.mylovin.rpc.rpcInterfaces")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
