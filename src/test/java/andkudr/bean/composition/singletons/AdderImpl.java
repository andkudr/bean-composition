package andkudr.bean.composition.singletons;

import org.springframework.stereotype.Component;

@Component
public class AdderImpl implements Adder {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
