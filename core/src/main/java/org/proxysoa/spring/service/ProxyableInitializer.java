package org.proxysoa.spring.service;

import org.proxysoa.spring.annotation.Proxyable;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * A Key class which gets application context, scans for all controllers interfaces annotated with
 * @link org.proxysoa.spring.annotation.Proxyable and creates a Proxy bean for each found controller
 * interface. All the created Proxy instances for the controllers are added as beans to Spring's
 * application context and available for autowiring.
 * @author stanislav.lapitsky created 4/20/2017.
 */
@Component
public class ProxyableInitializer implements ApplicationContextAware {

    @Autowired
    private ControllerFactory controllerFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null) {
            AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

            Set<Class<?>> annotatedClasses = allFoundClassesAnnotatedWithProxyable();
            for (Class<?> annotated : annotatedClasses) {
                if (annotated.isInterface()) {
                    String[] names = ((DefaultListableBeanFactory)beanFactory).getBeanNamesForType(annotated);
                    if (names == null || names.length==0) {
                        addBean(beanFactory, annotated);
                    }
                }
            }
        }
    }

    /**
     *
     * @return set of classes annotated with @Proxyable
     */
    private static Set<Class<?>> allFoundClassesAnnotatedWithProxyable(){
        Reflections reflections = new Reflections("");
        return reflections.getTypesAnnotatedWith(Proxyable.class);
    }

    /**
     * Gets Proxy for the controller and adds the bean to spring context.
     * @param beanFactory bean factory to add bean
     * @param proxyable source class to create Proxy for
     */
    private void addBean(AutowireCapableBeanFactory beanFactory, Class<?> proxyable) {
        Object instance = controllerFactory.getController(proxyable);
        beanFactory.autowireBean(instance);
        beanFactory.initializeBean(instance, proxyable.getCanonicalName());
        ((ConfigurableListableBeanFactory)beanFactory).registerSingleton(proxyable.getCanonicalName(), instance);
    }

}
