package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.ResponseMessage;

/**
 * @author daidai
 */
public interface CategoryService {
    public ResponseMessage getFirstCategories();
    public ResponseMessage getSecondCategories(int id);
    public ResponseMessage getThirdCategories(int id);
}
