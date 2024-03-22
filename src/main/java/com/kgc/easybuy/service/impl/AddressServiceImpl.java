package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.AddressMapper;
import com.kgc.easybuy.pojo.Address;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daidai
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseMessage getDefaultAdd(Integer id) {
        Address defaultAdd = addressMapper.getDefaultAdd(id);
        return ResponseMessage.success(defaultAdd);
    }
}
