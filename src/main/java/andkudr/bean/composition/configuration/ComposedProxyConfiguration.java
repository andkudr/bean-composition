package andkudr.bean.composition.configuration;

import andkudr.bean.composition.component.ComposedProxyBeanFactory;
import andkudr.bean.composition.component.ComposedProxyInvocationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComposedProxyConfiguration {

    @Bean
    public ComposedProxyInvocationHandler composedProxyInvocationHandler() {
        return new ComposedProxyInvocationHandler();
    }

    @Bean
    public ComposedProxyBeanFactory composedProxyBeanFactory() {
        return new ComposedProxyBeanFactory();
    }
}
