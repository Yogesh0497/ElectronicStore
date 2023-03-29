package com.yogesh.electronicStore.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValidation {
    //error message
String message()default "{javax.validation.constraints.NotBlank message}";
//represent groups of constrints
        Class<?>[] groups()default  {};
        //Additional Information about annotation
        Class<? extends Payload>[] payload() default {} ;

}
