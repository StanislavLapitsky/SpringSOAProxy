package org.proxysoa.spring.service;

import org.proxysoa.spring.annotation.Proxyable;
import org.proxysoa.spring.annotation.ProxyableScan;
import org.proxysoa.spring.exception.SOAControllerCreationException;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

/**
 * @author stanislav.lapitsky created 4/27/2017.
 */
public class ProxyableScanRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // Get the ProxyableScan annotation attributes
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ProxyableScan.class.getCanonicalName());

        if (annotationAttributes != null) {
            String[] basePackages = (String[]) annotationAttributes.get("value");

            if (basePackages.length == 0) {
                // If value attribute is not set, fallback to the package of the annotated class
                basePackages = new String[]{((StandardAnnotationMetadata) metadata).getIntrospectedClass().getPackage().getName()};
            }

            // using these packages, scan for interface annotated with Proxyable
            ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false, environment) {
                // Override isCandidateComponent to only scan for interface
                @Override
                protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                    AnnotationMetadata metadata = beanDefinition.getMetadata();
                    return metadata.isIndependent() && metadata.isInterface();
                }
            };
            provider.addIncludeFilter(new AnnotationTypeFilter(Proxyable.class));

            ControllerFactory factory = getControllerFactory();
            // Scan all packages
            for (String basePackage : basePackages) {
                for (BeanDefinition beanDefinition : provider.findCandidateComponents(basePackage)) {
                    try {
                        Class c = this.getClass().getClassLoader().loadClass(beanDefinition.getBeanClassName());
                        if (!hasImplementingClass(c, basePackages)) {
                            Object instance = factory.getOrCreateProxy(c);
                            ((DefaultListableBeanFactory) registry).registerSingleton(beanDefinition.getBeanClassName(), instance);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new SOAControllerCreationException("cannot create proxy for " + beanDefinition.getBeanClassName());
                    }
                }
            }
        }
    }

    private ControllerFactory getControllerFactory() {
        ControllerFactory factory = new ControllerFactory();
        PropertiesControllerURLResolver urlResolver = new PropertiesControllerURLResolver();
        urlResolver.setEnvironment(environment);
        factory.setControllerURLResolver(urlResolver);

        return factory;
    }
    /**
     * Checks whether the interface has implementing class
     *
     * @param proxyableInterface interface
     * @param basePackages       packages to scan
     * @return true if there is a class implementing the interface
     */
    private boolean hasImplementingClass(Class proxyableInterface, String[] basePackages) {
        for (String pName : basePackages) {
            Reflections reflections = new Reflections(pName);
            Set<Class> classes = reflections.getSubTypesOf(proxyableInterface);
            if (classes.size() > 0) {
                return true;
            }
        }
        return false;
    }

}
