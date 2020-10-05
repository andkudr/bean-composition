package andkudr.bean.composition.config;

import andkudr.bean.composition.annotation.EnableComposition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"andkudr.bean.composition.prototypes"})
@EnableComposition({"andkudr.bean.composition.prototypes"})
public class PrototypeConfig {
}