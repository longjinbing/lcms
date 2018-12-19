package com.ljb.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MenuDescription {

	public String group() default "";

	public String name() default "";

	public String action() default "#";

	public SafetyGrade safetyGrade() default SafetyGrade.SECURITY;

}
