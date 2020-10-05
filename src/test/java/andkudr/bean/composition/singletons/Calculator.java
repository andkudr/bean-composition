package andkudr.bean.composition.singletons;

import andkudr.bean.composition.annotation.Composed;

@Composed
public interface Calculator extends Adder, Subtractor {
}
