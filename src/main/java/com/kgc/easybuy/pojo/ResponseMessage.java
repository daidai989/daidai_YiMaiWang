package com.kgc.easybuy.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage<T> {
<<<<<<< HEAD
<<<<<<< HEAD
    private Integer code;
    private String message;
    private T data;

    public static <E> ResponseMessage<E> success(E data) {
        return new ResponseMessage<>(200,"操作成功",data);
    }
=======
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
   private Integer code;
   private String message;
   private T data;

   public static <E> ResponseMessage<E> success(E data) {
       return new ResponseMessage<>(200,"操作成功",data);
   }
<<<<<<< HEAD
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323
=======
>>>>>>> 478cdf7045a7b655be12098bdee6592429f68323

    public static  ResponseMessage success() {
        return new ResponseMessage<>(200,"操作成功",null);
    }

    public static <E> ResponseMessage<E> success(String message,E data) {
        return new ResponseMessage<>(200,message,data);
    }

    public static  ResponseMessage error(String message) {
        return new ResponseMessage<>(400,message,null);
    }
}
