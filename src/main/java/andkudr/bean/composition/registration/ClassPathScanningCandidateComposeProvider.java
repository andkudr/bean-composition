package andkudr.bean.composition.registration;

import andkudr.bean.composition.annotation.Composed;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class ClassPathScanningCandidateComposeProvider extends ClassPathScanningCandidateComponentProvider {

    public ClassPathScanningCandidateComposeProvider() {
        addIncludeFilter(new AnnotationTypeFilter(Composed.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isIndependent() && metadata.isInterface();
    }
}
