package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Address;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import org.springframework.stereotype.Service;

public interface AddressService {

    public ResponseMessage getDefaultAdd(Integer id);
    public ResponseMessage getAddressById(int userId,Page page);
    public ResponseMessage setDefaultAddress(Address address);
    public ResponseMessage addAddress(Address address);
    public ResponseMessage getAddressByIdAndUserId(Address address);
    public ResponseMessage updateAddress(Address address);
    public ResponseMessage deleteAddress(Address address);
}
