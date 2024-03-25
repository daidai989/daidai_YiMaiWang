package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.CollectionMapper;
import com.kgc.easybuy.pojo.Collect;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public ResponseMessage collectProduct(Collect collect) {
        int count = collectionMapper.collectProduct(collect);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("收藏失败！");
    }

    @Override
    public ResponseMessage getCollectionByproductIdAndUserId(Collect collect) {
        int count = collectionMapper.getCollectionByproductIdAndUserId(collect);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("该用户没有收藏");
    }
    @Override
    public ResponseMessage deleteCollection(Collect collect) {
        int count = collectionMapper.deleteCollection(collect);
        if (count > 0){
            return ResponseMessage.success("取消收藏成功！");
        }
        return ResponseMessage.error("收藏取消失败！");
    }
}
