package thiagodnf.sootparserweb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import thiagodnf.sootparserweb.annotation.validator.NotEmptyFileValidator;

@Documented
@Constraint(validatedBy = NotEmptyFileValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyFile {

	String message() default "The file cannot be empty";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String[] value() default {};
}
