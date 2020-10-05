package andkudr.bean.composition.prototypes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(scopeName = "prototype")
public class FirstPrototypeImpl implements FirstPrototype {

    private long initTime;

    @PostConstruct
    public void init() {
        initTime = System.currentTimeMillis();
    }

    @Override
    public long getFirstInitTime() {
        return initTime;
    }
}
