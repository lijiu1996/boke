package com.lijiawei.pro.boke.bean;

import com.lijiawei.pro.boke.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Li JiaWei
 * @ClassName: Result
 * @Description: 自定义前端返回实体类
 * @Date: 2023/2/23 16:48
 * @Version: 1.0
 */
@Builder
@Data
public class Result<T> {

    private boolean success;
    private int code;
    private String msg;
    private T data;

    public static <T> Result ok(ResultEnum res, T data) {
        return Result.builder()
                .success(res.isSuccess())
                .code(res.getCode())
                .msg(res.getMsg())
                .data(data)
                .build();
    }
}
