package andkudr.bean.composition.component;

import andkudr.bean.composition.exception.ComposedProxyNoTargetsException;
import andkudr.bean.composition.exception.ComposedProxyTooManyTargetsException;
import andkudr.bean.composition.proxy.ComposedProxy;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

public class ComposedProxyInvocationHandler implements InvocationHandler {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return String.format("%s@%d", proxy.getClass().getName(), proxy.hashCode());
        } else if (method.getName().equals("hashCode")) {
            return System.identityHashCode(proxy);
        }
        Object bean = getBeanByTargetClass(method.getDeclaringClass());
        return method.invoke(bean, args);
    }

    private Object getBeanByTargetClass(Class<?> clazz) {
        Map<String, Object> beanMap = getBeanNameListByClass(clazz);
        if (beanMap.isEmpty()) {
            throw new ComposedProxyNoTargetsException(beanMap.keySet());
        } else if (beanMap.size() > 1) {
            throw new ComposedProxyTooManyTargetsException(beanMap.keySet());
        }
        return beanMap.values().stream().findAny().get();
    }

    private Map<String, Object> getBeanNameListByClass(Class<?> clazz) {
        return applicationContext.getBeansOfType(clazz).entrySet().stream()
                .filter(e -> !(e.getValue() instanceof ComposedProxy))
                .filter(e -> !ScopedProxyUtils.isScopedTarget(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
