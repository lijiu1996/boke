package com.lijiawei.pro.boke.bean.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {

    @NotBlank
    @Length(min = 2)
    private String account;

    @NotBlank
    @Length(min = 2)
    private String password;

    @NotBlank
    private String nickname;
}
