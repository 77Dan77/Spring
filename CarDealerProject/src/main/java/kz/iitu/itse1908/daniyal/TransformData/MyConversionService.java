package kz.iitu.itse1908.daniyal.TransformData;

import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MyConversionService extends DefaultConversionService implements ConversionService {
}
