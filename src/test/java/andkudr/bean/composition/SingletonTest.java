package andkudr.bean.composition;

import andkudr.bean.composition.config.SingletonConfig;
import andkudr.bean.composition.singletons.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {SingletonConfig.class})
public class SingletonTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void adderTest() {
        assertEquals(calculator.add(1, 2), 3);
    }

    @Test
    public void subtractTest() {
        assertEquals(calculator.subtract(10, 3), 7);
    }
}
