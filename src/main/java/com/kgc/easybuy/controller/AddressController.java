package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Address;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping("getDefaultAdd")
    public ResponseMessage getDefaultAdd(Integer id) {
        ResponseMessage defaultAdd = addressService.getDefaultAdd(id);
        return defaultAdd;
    }

    @RequestMapping("getAddressById")
    public Object getAddressById(int userId, Page page){
        ResponseMessage msg = addressService.getAddressById(userId,page);
        return msg;
    }
    @RequestMapping("setDefault")
    public Object setDefaultAddress(Address address){
        ResponseMessage msg = addressService.setDefaultAddress(address);
        return msg;
    }

    @RequestMapping("addAddress")
    public Object addAddress(Address address){
        ResponseMessage msg = addressService.addAddress(address);
        return msg;
    }

    @RequestMapping("getAddressByIdAndUserId")
    public Object getAddressByIdAndUserId(Address address){
        ResponseMessage msg = addressService.getAddressByIdAndUserId(address);
        return msg;
    }
}
