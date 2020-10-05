package andkudr.bean.composition.config;

import andkudr.bean.composition.annotation.EnableComposition;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"andkudr.bean.composition.singletons"})
@EnableComposition({"andkudr.bean.composition.singletons"})
public class SingletonConfig {
}