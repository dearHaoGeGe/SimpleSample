package com.my.simplesampletest.loading_and_annotation.annotation;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Gender、Name、Profile注解处理器
 * 参考：http://blog.csdn.net/u013045971/article/details/53433874
 * <p>
 * Created by YJH on 2017/2/4 9:55.
 */

public class CustomUtils {

    private static final String TAG = "自定义Annotation--->";

    public static void getInfo(Class<?> clazz) {
        String name = "";
        String gender = "";
        String profile = "";

        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Name.class)) {
                Name nameAnn = field.getAnnotation(Name.class);
                name = name + nameAnn.value();
                Log.i(TAG, "Name = " + name);
            }

            if (field.isAnnotationPresent(Gender.class)) {
                Gender genderAnn = field.getAnnotation(Gender.class);
                gender = gender + genderAnn.gender().toString();
                Log.i(TAG, "Gender = " + gender);
            }

            if (field.isAnnotationPresent(Profile.class)) {
                Profile profileAnn = field.getAnnotation(Profile.class);
                profile = "[id = " + profileAnn.id() + ", height = " + profileAnn.height() + ", nativePlace = " + profileAnn.nativePlace() + "]";
                Log.i(TAG, "Profile = " + profile);
            }
        }
    }

}
