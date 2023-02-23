package com.lijiawei.pro.boke.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author Li JiaWei
 * @ClassName: ResultEnum
 * @Description: 返回码枚举定义
 * @Date: 2023/2/23 16:52
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    OK(true,200,"success");
    private boolean success;
    private int code;
    private String msg;
}
