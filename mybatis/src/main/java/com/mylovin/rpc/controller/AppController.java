package com.mylovin.rpc.controller;

import com.mylovin.rpc.rpcInterfaces.OneRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Autowired
    private OneRpc oneRpc;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(oneRpc.print());;
        return "hello, world!";
    }
}
