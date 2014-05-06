package net.ambre.spring.annotation;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.LookupOverride;

/**
 * {@link BeanFactoryPostProcessor} that will add the method overrides to handle the {@link LookupMethod} annotation.
 */
public class LookupMathodBeanFactoryPostProcessors implements BeanFactoryPostProcessor {

    /**
     * Logger used by this class.
     **/
    private final Log LOGGER = LogFactory.getLog(getClass());

    /**
     * {@inheritDoc}
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
                for (Method method : beanClass.getDeclaredMethods()) {
                    // Check if the method is annotated by LookupMethod, has no argument and a return value.
                    if (method.isAnnotationPresent(LookupMethod.class) && method.getParameterTypes().length == 0
                                    && method.getReturnType() != void.class) {
                        // If the bean definition is not an instance of AbstractBeanDefinition, one cannot access the method overrides property.
                        if (beanDefinition instanceof AbstractBeanDefinition) {
                            AbstractBeanDefinition abstractBeanDefinition = (AbstractBeanDefinition) beanDefinition;
                            LookupMethod lookupMethod = method.getAnnotation(LookupMethod.class);
                            // The name of the bean that will be returned by the injected method.
                            String injectedBeanName = null;
                            if ("".equals(lookupMethod.beanRef())) {
                                // First case: One did not give us the name of the bean to inject.
                                String qualifier = lookupMethod.qualifier();
                                Class<?> returnType = method.getReturnType();
                                // One retrieves all the names of the beans that have the correct return type, but one does not want to instantiate
                                // factories, as their dependencies might not be ready yet.
                                String[] targetBeanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, returnType, true, false);
                                if (targetBeanNames.length > 0) {
                                    injectedBeanName = targetBeanNames[0];
                                    if (!"".equals(qualifier) && targetBeanNames.length > 1) {
                                        // The user specified a qualifier and more than one bean has the correct type. One must look for the better
                                        // match.
                                        // To Be Noted: This factory only honor the Qualifier annotation for qualifier.
                                        LOOP: for (int i = 0; i < targetBeanNames.length; i++) {
                                            try {
                                                BeanDefinition targetBeanDefinitoin = beanFactory.getBeanDefinition(targetBeanNames[i]);
                                                Class<?> targetBeanClass = Class.forName(targetBeanDefinitoin.getBeanClassName());
                                                if (targetBeanClass.isAnnotationPresent(Qualifier.class)) {
                                                    String currentQualifier = targetBeanClass.getAnnotation(Qualifier.class).value();
                                                    if (currentQualifier.equals(qualifier)) {
                                                        injectedBeanName = targetBeanNames[i];
                                                        break LOOP;
                                                    }
                                                }
                                            } catch (ClassNotFoundException e) {
                                                // Just log error, as it is not a fatal one.
                                                LOGGER.warn(e.getMessage(), e);
                                            }
                                        }
                                    }
                                }
                            } else {
                                // Second case: One did give us the name of the bean to inject.
                                injectedBeanName = lookupMethod.beanRef();
                            }
                            if (injectedBeanName != null) {
                                abstractBeanDefinition.getMethodOverrides().addOverride(new LookupOverride(method.getName(), injectedBeanName));
                            } else {
                                throw new BeanCreationException("Unable to instruments bean as no compatible bean is defined.");
                            }
                        } else {
                            throw new BeanCreationException("Unable to instruments bean as the definition cannot handle method overrides.");
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new BeanCreationException(e.getMessage(), e);
            }
        }
    }
}
