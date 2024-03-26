package com.kgc.easybuy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;


    public static <E> ResponseMessage<E> success(E data) {
        return new ResponseMessage<>(200,"操作成功",data);
    }

    public static  ResponseMessage success() {
        return new ResponseMessage<>(200,"操作成功",null);
    }

    public static <E> ResponseMessage<E> success(String message,E data) {
        return new ResponseMessage<>(200,message,data);
    }

    public static  ResponseMessage error(String message) {
        return new ResponseMessage<>(400,message,null);
    }
    public static  <E>ResponseMessage <E>error(String message,E data) {
        return new ResponseMessage<>(400,message,data);
    }
}
