package andkudr.bean.composition.exception;

import java.util.Collection;

public class ComposedProxyTooManyTargetsException extends RuntimeException {

    public ComposedProxyTooManyTargetsException(Collection<String> beanNameList) {
        super(String.format("Too many targets were found: %s", beanNameList.toString()));
    }
}