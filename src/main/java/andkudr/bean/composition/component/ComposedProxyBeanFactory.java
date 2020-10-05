package andkudr.bean.composition.component;

import andkudr.bean.composition.proxy.ComposedProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

public class ComposedProxyBeanFactory {

    @Autowired
    private ComposedProxyInvocationHandler composedProxyInvocationHandler;

    public <T> T createComposedProxyBeanFromInterface(Class<T> clazz) {
        if (clazz == null) {
            throw new NullPointerException("ComposedProxyBeanFactory.createComposedProxyBeanFromInterface failed: clazz can't be null");
        } else if (!clazz.isInterface()) {
            throw new IllegalArgumentException(String.format("%s is not an interface", clazz.getCanonicalName()));
        }
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[] {clazz, ComposedProxy.class},
                composedProxyInvocationHandler
        );
    }

}
