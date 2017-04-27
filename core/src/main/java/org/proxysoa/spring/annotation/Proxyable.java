package org.proxysoa.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation is marker interface to allow proxy creation and autowiring.
 * If value is specified it is used as bean name in spring application context.
 * @author stanislav.lapitsky created 4/20/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Proxyable {

    String value() default "";
}
