package com.my.simplesampletest.loading_and_annotation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 个人资料注解
 * <p>
 * Created by YJH on 2017/2/4 9:51.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Profile {

    public int id() default -1;     //ID

    public int height() default 0;  //身高

    public String nativePlace() default ""; //籍贯
}
