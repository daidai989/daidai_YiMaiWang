package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.History;
import com.kgc.easybuy.pojo.ResponseMessage;

public interface HistoryService {
    public ResponseMessage addProductHistory(History history);
    public ResponseMessage cleanHistory(int userId);
}
