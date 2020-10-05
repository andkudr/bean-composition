package andkudr.bean.composition.annotation;

import andkudr.bean.composition.configuration.ComposedProxyConfiguration;
import andkudr.bean.composition.registration.ComposedAnnotationImportBeanDefinitionRegistar;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        ComposedProxyConfiguration.class,
        ComposedAnnotationImportBeanDefinitionRegistar.class
})
public @interface EnableComposition {

    @AliasFor("packages")
    String[] value() default {};

    @AliasFor("value")
    String[] packages() default {};

}
