package com.example.battleships.validations.passwordMatcher;

import com.example.battleships.model.binding.UserRegisterModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext constraintValidatorContext) {

        if (userRegisterModel.getPassword() != null && userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterModel.getPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
