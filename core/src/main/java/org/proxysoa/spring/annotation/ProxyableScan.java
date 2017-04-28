package org.proxysoa.spring.annotation;

import org.proxysoa.spring.service.ProxyableScanRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation specifies base package(s) where the Proxy candidate interfaces
 * (interfaces which are marked as Proxyable) are located.
 * The ProxyableScanRegistrar does the scan and register Proxy beans created
 * on fly for the found interfaces
 *
 * @author stanislav.lapitsky created 4/27/2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ProxyableScanRegistrar.class})
public @interface ProxyableScan {

    String[] value() default {};
}