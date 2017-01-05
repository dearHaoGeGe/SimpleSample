package com.my.simplesampletest.base;

import java.util.List;

/**
 * 6.0权限回调接口,在BaseActivity中使用
 * <p>
 * Created by YJH on 2017/1/4 9:24.
 */

public interface MPermissionListener {

    /**
     * 表示全部授权
     */
    void onGranted();

    /**
     * 表示未授权
     *
     * @param deniedPermission 未授权的List
     */
    void onDenied(List<String> deniedPermission);
}
