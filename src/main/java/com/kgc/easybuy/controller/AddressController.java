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
    public ResponseMessage getAddressById(int userId, Page page){
        ResponseMessage msg = addressService.getAddressById(userId,page);
        return msg;
    }

    @RequestMapping("setDefault")
    public ResponseMessage setDefaultAddress(Address address){
        ResponseMessage msg = addressService.setDefaultAddress(address);
        return msg;
    }

    @RequestMapping("addAddress")
    public ResponseMessage addAddress(Address address){
        ResponseMessage msg = addressService.addAddress(address);
        return msg;
    }

    @RequestMapping("getAddressByIdAndUserId")
    public ResponseMessage getAddressByIdAndUserId(Address address){
        ResponseMessage msg = addressService.getAddressByIdAndUserId(address);
        return msg;
    }
    @RequestMapping("updateAddress")
    public ResponseMessage updateAddress(Address address) {
        ResponseMessage responseMessage = addressService.updateAddress(address);
        return responseMessage;
    }
    @RequestMapping("deleteAddress")
    public ResponseMessage deleteAddress(Address address) {
        ResponseMessage responseMessage = addressService.deleteAddress(address);
        return  responseMessage;
    }
}
