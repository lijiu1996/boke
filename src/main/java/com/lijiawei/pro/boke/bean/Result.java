package com.lijiawei.pro.boke.bean;

import com.lijiawei.pro.boke.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Li JiaWei
 * @ClassName: Result
 * @Description: 自定义前端返回实体类
 * @Date: 2023/2/23 16:48
 * @Version: 1.0
 */
@NoArgsConstructor
@Data
public class Result<T> {

    private boolean success;
    private int code;
    private String msg;
    private T data;
    private Result(ResultEnum resultEnum) {
        this.success = resultEnum.isSuccess();
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    private Result(boolean success, int code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }
    public static Result ok() {
        return new Result(ResultEnum.OK);
    }

    public static Result fail(int code, String msg) {
        return new Result(false,code,msg);
    }

}
