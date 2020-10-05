package andkudr.bean.composition.singletons;

import org.springframework.stereotype.Component;

@Component
public class SubtractorImpl implements Subtractor {

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
