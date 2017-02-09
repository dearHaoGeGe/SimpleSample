package com.my.simplesampletest.loading_and_annotation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 性别注解
 * <p>
 * Created by YJH on 2017/2/4 9:42.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Gender {

    GenderType gender() default GenderType.Male;

    public enum GenderType {
        Male("男"),
        Female("女"),
        Other("其他");

        private String genderStr;

        private GenderType(String gender){
            this.genderStr=gender;
        }

        @Override
        public String toString() {
            return "GenderType{" +
                    "genderStr='" + genderStr + '\'' +
                    '}';
        }
    }

}
