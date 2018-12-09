package com.cqshop.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mateusz Brycki on 05/11/2016.
 */
@Slf4j
@Component
public class ConverterRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    private final String CONVERSION_SERVICE_NAME = "mvcConversionService";

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //registry.registerBeanDefinition(CONVERSION_SERVICE_NAME, BeanDefinitionBuilder.rootBeanDefinition(ConversionServiceFactoryBean.class).getBeanDefinition());
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (CONVERSION_SERVICE_NAME.equals(beanName)) {
            log.info("ConversionService bean detected");

            Map<String, Converter> beansOfType = appCtx.getBeansOfType(Converter.class);

            DefaultFormattingConversionService conversionFactoryBean = (DefaultFormattingConversionService) bean;
            Set converters = new HashSet(beansOfType.values());

            converters.forEach(c -> conversionFactoryBean.addConverter((Converter)c));

        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    ApplicationContext appCtx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtx = applicationContext;
    }

}