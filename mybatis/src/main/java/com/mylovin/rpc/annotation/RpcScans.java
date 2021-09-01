package com.mylovin.rpc.annotation;

import org.springframework.context.annotation.Import;
import com.mylovin.rpc.annotation.RpcScannerRegistrar.RepeatingRegistrar;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RepeatingRegistrar.class})
public @interface RpcScans {
    RpcScan[] value();
}
