package andkudr.bean.composition;

import andkudr.bean.composition.config.PrototypeConfig;
import andkudr.bean.composition.prototypes.PrototypeComposer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = {PrototypeConfig.class})
public class PrototypeTest {

    @Autowired
    private PrototypeComposer prototypeComposer;

    @Test
    public void testPrototype() throws InterruptedException {
        long firstInit1 = prototypeComposer.getFirstInitTime();
        long secondInit1 = prototypeComposer.getSecondInitTime();
        Thread.sleep(100);
        long firstInit2 = prototypeComposer.getFirstInitTime();
        long secondInit2 = prototypeComposer.getSecondInitTime();
        assertNotEquals(firstInit1, firstInit2);
        assertNotEquals(secondInit1, secondInit2);
    }
}
