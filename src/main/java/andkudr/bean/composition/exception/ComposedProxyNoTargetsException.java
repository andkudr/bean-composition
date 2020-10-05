package andkudr.bean.composition.exception;

import java.util.Collection;

public class ComposedProxyNoTargetsException extends RuntimeException {

    public ComposedProxyNoTargetsException(Collection<String> beanNameList) {
        super(String.format("No targets were found for bean names: %s", beanNameList.toString()));
    }
}