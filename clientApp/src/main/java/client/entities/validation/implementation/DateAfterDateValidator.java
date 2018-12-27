package client.entities.validation.implementation;

import client.entities.validation.ValidDateAfterDate;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.stream.Stream;

public class DateAfterDateValidator implements ConstraintValidator<ValidDateAfterDate, Object> {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;

    @Override
    public void initialize(ValidDateAfterDate constraintAnnotation) {
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(fields.length != 2) { return false; }
        Object[] fieldValuesFromParent = Stream.of(fields).map(field -> PARSER.parseExpression(field).getValue(value)).toArray();
        Date after = (fieldValuesFromParent[0] instanceof Date) ? (Date) fieldValuesFromParent[0] : null;
        Date before = (fieldValuesFromParent[1] instanceof Date) ? (Date) fieldValuesFromParent[1] : null;
        if(after == null || before == null) { return false; }
        return after.after(before);
    }
}

