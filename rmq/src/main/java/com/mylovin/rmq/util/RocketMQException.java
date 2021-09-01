package com.mylovin.rmq.util;

public class RocketMQException extends Exception {
    public RocketMQException(String message) {
        super(message);
    }

    public RocketMQException(Throwable cause) {
        super(cause);
    }

    public RocketMQException(RocketMQErrorEnum anEnum, String groupName_is_blank, boolean b) {
    }
}
