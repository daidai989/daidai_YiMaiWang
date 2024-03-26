package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.HistoryMapper;
import com.kgc.easybuy.pojo.History;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public ResponseMessage addProductHistory(History history) {
        int count = historyMapper.addProductHistory(history);
        if (count > 0){
           return ResponseMessage.success();
        }
        return ResponseMessage.error("添加历史记录失败！");
    }

    @Override
    public ResponseMessage cleanHistory(int userId) {
        int count = historyMapper.cleanHistory(userId);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("清空历史记录失败！");
    }
}
