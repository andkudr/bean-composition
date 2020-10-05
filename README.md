# Spring Bean Composition

[Tests](./src/test/java/andkudr/bean/composition/)

**First bean**
```java
public interface Adder {

    int add(int a, int b);
}
```

```java
@Component
public class AdderImpl implements Adder {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
```

**Second bean**
```java
public interface Subtractor {
    int subtract(int a, int b);
}
```

```java
@Component
public class SubtractorImpl implements Subtractor {

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
```

**Composed bean**
```java
@Composed
public interface Calculator extends Adder, Subtractor {
}
```

**Usage**
```java
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
```