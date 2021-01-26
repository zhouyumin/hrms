package com.fwwb.hrms.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 周余民
 * @Date: Created in 9:56 2021/1/26
 * 统一结果封装
 */
@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static Result succ(String mess) {
        Result m = new Result();
        m.setCode("0");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result succ(Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data) {
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode("-1");
        m.setData(data);
        m.setMsg(mess);
        return m;
    }
}

