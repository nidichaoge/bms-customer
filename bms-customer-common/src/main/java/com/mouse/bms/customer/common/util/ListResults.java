package com.mouse.bms.customer.common.util;

import com.mouse.bms.customer.common.response.ListResult;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ListResults
 * @date : 2019/3/18 11:38
 * @description :
 */
public class ListResults {

    static int SUCCESS = 200;

    public static <T> ListResult<T> success(List<T> data) {
        return success(data, (String) null);
    }

    public static <T> ListResult<T> success(List<T> data, String message) {
        return success(data, SUCCESS, message);
    }

    public static <T> ListResult<T> success(List<T> data, int code, String message) {
        return get(true, data, message, code);
    }

    public static <T> ListResult<T> get(boolean success, List<T> data, String msg, int code) {
        if (!success && code == SUCCESS) {
            throw new IllegalArgumentException("ListResults.error() method's code must not be 200");
        } else {
            ListResult<T> result = new ListResult<>();
            result.setSuccess(success);
            result.setData(data);
            result.setMessage(msg == null ? "" : msg);
            result.setCode(code);
            result.setCount(CollectionUtils.isEmpty(data) ? 0 : data.size());
            return result;
        }
    }

//    static <T> ListResult<T> error(Errors errors) {
//        return error(errors.getValue(), errors.getDesc());
//    }

    public static <T> ListResult<T> error(int code, String msg) {
        return error(new ArrayList<>(), code, msg);
    }

    public static <T> ListResult<T> error(List<T> data, int code, String msg) {
        return get(false, data, msg, code);
    }

}
