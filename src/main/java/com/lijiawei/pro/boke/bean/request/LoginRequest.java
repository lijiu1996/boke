package com.lijiawei.pro.boke.bean.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    @Length(min = 2)
    private String account;

    @NotBlank
    @Length(min = 2)
    private String password;
}
