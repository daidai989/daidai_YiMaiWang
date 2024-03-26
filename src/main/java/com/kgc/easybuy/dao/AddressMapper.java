package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AddressMapper {
    public Address getDefaultAdd(Integer id);
    public List<Address> getAddressById(int userId);
    public int setDefaultOne(Address address);
    public int setDefaultTwo(Address address);
    public int addAddress(Address address);
    public Address getAddressByIdAndUserId(Address address);
    public int updateAddress(Address address);
}
