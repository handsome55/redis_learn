package com.yh.common;



import com.yh.common.constant.Warning;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @date 2020-3-10
 * @author YuHao
 */
@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = 7696422827876894328L;

    private Integer code;

    private String message;

    private Object data;
    
	public static BaseResult build(Warning warning) {
        return new BaseResult(warning);
    }

    public static BaseResult ok(Object data) {
    	
        return new BaseResult(data);
        
    }
    public static BaseResult ok(Object data, Object data1) {

        return new BaseResult(data);

    }
    public static BaseResult ok() {
    	
        return new BaseResult();
        
    }
    
    public BaseResult(Warning warning){
    	this.code = warning.getValue();
        this.message= warning.getName();
        this.data = null;
        
    }

    public BaseResult(Integer code, String message
            , Object data) {
        this.code = code;
        this.message= message;
        this.data = data;
    }

    public BaseResult(Object data) {
        this.code = 200;
        this.message= "成功";
        this.data = data;
    }
    public BaseResult(Object data, String s) {
        this.code = 200;
        this.message= "成功";
        this.data = data;
    }
    public BaseResult() {
        this.code = 200;
        this.message= "成功";
        this.data = null;
    }


    public BaseResult(Integer code, String message
    ) {
        this.code = code;
        this.message= message;
    }

    public BaseResult setMessage(String message) {
        if (!StringUtils.isEmpty(message)) {
            this.message = message;
        }
        return this;
    }

    public BaseResult setObject(Object object) {
        this.data = object;
        return this;
    }
	public static BaseResult build(Integer code, String message) {
		return new BaseResult(code, message);
	}

}
