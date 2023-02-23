package com.example.battleships.model.binding;

import com.example.battleships.validations.passwordMatcher.PasswordMatch;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@PasswordMatch
public class UserRegisterModel {

    @Size(min = 3, max = 10)
    @NotNull
    private String username;

    @Size(min = 5, max = 20)
    @NotNull
    private String fullName;

    @Size(min = 3)
    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @Email
    @NotNull
    private String email;

}