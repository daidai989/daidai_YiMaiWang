package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Collect;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CollectionController {

    @Autowired
    private CollectService collectionService;

    /**
     * 添加用户收藏
     * @param collect
     * @return
     */
    @RequestMapping("collectProduct")
    public ResponseMessage collectProduct(Collect collect){
        ResponseMessage responseMessage = collectionService.collectProduct(collect);
        return responseMessage;
    }

    /**
     * 取消用户收藏
     * @param collect
     * @return
     */
    @RequestMapping("deleteCollection")
    public ResponseMessage deleteCollection(Collect collect){
        ResponseMessage responseMessage = collectionService.deleteCollection(collect);
        return responseMessage;
    }
}
