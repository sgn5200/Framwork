package com.cannan.framwork.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Cannan on 2017/9/26 0026.
 * 不知道为什么，听说管理生命周期，
 * 看着别人写了我也写上
 * 不写会报错
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
