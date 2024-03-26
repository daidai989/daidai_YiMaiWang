package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AddressMapper {

    public Address getDefaultAdd(Integer id);
    public List<Address> getAddressById(int userId);

    /**
     * 修改默认地址，先将默认地址置0
     * @param address
     * @return
     */
    public int setDefaultOne(Address address);

    /**
     * 修改默认地址，后将该id设为默认地址
     * @param address
     * @return
     */
    public int setDefaultTwo(Address address);
    public int addAddress(Address address);
    public Address getAddressByIdAndUserId(Address address);
    public int updateAddress(Address address);
    public int deleteAddress(Address address);
}
