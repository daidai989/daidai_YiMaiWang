package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Collect;

public interface CollectionMapper {

    /**
     * 添加用户收藏
     * @param collect
     * @return
     */
    public int collectProduct(Collect collect);

    /**
     * 判断当前用户是否收藏了当前商品
     * @param collect
     * @return
     */
    public int getCollectionByproductIdAndUserId(Collect collect);
    /**
     * 取消收藏
     * @param collect
     * @return
     */
    public int deleteCollection(Collect collect);
}
