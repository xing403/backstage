package com.lxn.backstage.resault;

// 返回数据格式
public class Result<T> {
    private int code;   // 返回状态码
    private String msg; // 返回消息
    private T data;     // 返回数据

    /**
     * 成功时候的调用 仅有数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功时候的调用 无数据
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return new Result<T>();
    }

    /**
     * 异常时候的调用（有msg参数）
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return new Result<T>(msg);
    }

    /**
     * 异常时候的调用（无msg参数）
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error() {
        return new Result<T>("error");
    }

    private Result(T data) {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
    }

    public Result() {
        this.code = 200;
        this.msg = "请求成功";
    }

    private Result(String msg) {
        this.code = 400;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
