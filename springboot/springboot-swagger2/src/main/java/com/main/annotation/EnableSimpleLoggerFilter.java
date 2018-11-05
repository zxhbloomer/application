package com.main.annotation;

import com.main.SimpleLoggerFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(SimpleLoggerFilterAutoConfiguration.class) //通过Import引入AutoConfiguration
public @interface EnableSimpleLoggerFilter {

}
