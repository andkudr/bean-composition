package andkudr.bean.composition.registration;

import andkudr.bean.composition.annotation.EnableComposition;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.util.Map;

public class ComposedAnnotationImportBeanDefinitionRegistar implements ImportBeanDefinitionRegistrar {

    private final ClassPathScanningCandidateComposeProvider provider = new ClassPathScanningCandidateComposeProvider();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableComposition.class.getCanonicalName());
        if (annotationAttributes != null) {
            registerCompositionBeanDefinitions(importingClassMetadata, registry, annotationAttributes);
        }
    }

    private void registerCompositionBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                                    BeanDefinitionRegistry registry,
                                                    Map<String, Object> annotationAttributes) {

        String[] basePackages = (String[]) annotationAttributes.get("value");
        if (basePackages.length == 0) {
            basePackages = getDefaultBasePackage(importingClassMetadata);
        }

        for (String basePackage : basePackages) {
            registerPackage(basePackage, registry);
        }
    }

    private String[] getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        if (importingClassMetadata instanceof StandardAnnotationMetadata) {
            Class<?> clazz = ((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass();
            return new String[]{
                    clazz.getPackage().getName()
            };
        } else {
            try {
                Class<?> clazz = Class.forName(importingClassMetadata.getClassName());
                return new String[]{
                        clazz.getPackage().getName()
                };
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }

    private void registerPackage(String basePackage, BeanDefinitionRegistry registry) {
        for (BeanDefinition beanDefinition : provider.findCandidateComponents(basePackage)) {
            registerBeanDefinition(beanDefinition, registry);
        }
    }

    private void registerBeanDefinition(BeanDefinition beanDefinition, BeanDefinitionRegistry registry) {
        String className = beanDefinition.getBeanClassName();
        if (className == null) {
            throw new BeanCreationException("Bean Class Name not found");
        }

        Class<?> beanClass;
        try {
            beanClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        GenericBeanDefinition proxyBeanDefinition = new GenericBeanDefinition();

        ConstructorArgumentValues args = new ConstructorArgumentValues();
        args.addGenericArgumentValue(beanClass);

        proxyBeanDefinition.setBeanClass(beanClass);
        proxyBeanDefinition.setConstructorArgumentValues(args);
        proxyBeanDefinition.setFactoryBeanName("composedProxyBeanFactory");
        proxyBeanDefinition.setFactoryMethodName("createComposedProxyBeanFromInterface");

        registry.registerBeanDefinition(className, proxyBeanDefinition);
    }
}
