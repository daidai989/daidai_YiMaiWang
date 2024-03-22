package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Address;

import java.util.List;

public interface AddressMapper {
    public List<Address> getAddressById(int userId);
    public int setDefaultOne(Address address);
    public int setDefaultTwo(Address address);
    public int addAddress(Address address);
    public Address getAddressByIdAndUserId(Address address);
    public int updateAddress(Address address);
}
