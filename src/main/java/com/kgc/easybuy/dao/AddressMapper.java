package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author daidai
 */
@Mapper
public interface AddressMapper {
    public Address getDefaultAdd(Integer id);
}
