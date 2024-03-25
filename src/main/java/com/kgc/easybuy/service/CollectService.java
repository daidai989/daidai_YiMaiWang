package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Collect;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;

import java.util.List;


public interface CollectService {
    public ResponseMessage collectProduct(Collect collect);
    public ResponseMessage getCollectionByproductIdAndUserId(Collect collect);
    public ResponseMessage deleteCollection(Collect collect);
}
