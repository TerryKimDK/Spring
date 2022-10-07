package com.appmv.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)

public @interface Component {
    String value() default "";

}