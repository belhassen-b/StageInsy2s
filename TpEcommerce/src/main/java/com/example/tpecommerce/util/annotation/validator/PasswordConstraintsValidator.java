package com.example.tpecommerce.util.annotation.validator;


import com.example.tpecommerce.util.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.Optional;

public class PasswordConstraintsValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(8, 128),
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit, 1),
                        new CharacterRule(EnglishCharacterData.Special, 1),
                        new WhitespaceRule()
                )
        );
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;

        }
        Optional<String> stringOptional = validator.getMessages(result).stream().findFirst();
        if (stringOptional.isPresent()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(stringOptional.get())
                    .addConstraintViolation();
        }
        return false;
    }
}

