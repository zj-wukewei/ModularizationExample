package com.wkw.commonbusiness.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wukewei on 2017/8/25.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
