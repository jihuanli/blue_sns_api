/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-18<p>
//-------------------------------------------------------
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Message {

    String value() default "";
}
