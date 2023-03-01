package com.lijiawei.pro.boke.constant;

import lombok.AllArgsConstructor;
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

    OK(true,200,"success"),
    InvalidArgument(false,-1,"参数校验失败"),
    Unknown(false,-999,"程序运行时未知异常,待排查"),
    NotLogin(false,-2,"未登录"),
    LackPermission(false,-3,"没有权限访问"),
    NotFound(false, -4, "查询资源不存在");
    private boolean success;
    private int code;
    private String msg;
}
