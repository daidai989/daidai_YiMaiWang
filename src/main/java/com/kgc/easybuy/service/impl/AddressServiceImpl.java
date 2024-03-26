package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.AddressMapper;
import com.kgc.easybuy.pojo.Address;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ResponseMessage getDefaultAdd(Integer id) {
        Address defaultAdd = addressMapper.getDefaultAdd(id);
        return ResponseMessage.success(defaultAdd);
    }

    @Override
    public ResponseMessage getAddressById(int userId, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Address> addressById = addressMapper.getAddressById(userId);
        PageInfo pageInfo = new PageInfo(addressById);
        if (addressById != null){
            return ResponseMessage.success(pageInfo);
        }
        return ResponseMessage.error("没有数据！");
    }

    @Override
    public ResponseMessage setDefaultAddress(Address address) {
        int countOne = addressMapper.setDefaultOne(address);
        if (countOne > 0){
            int countTwo = addressMapper.setDefaultTwo(address);
            if (countTwo > 0){
                return ResponseMessage.success("设置成功！");
            }else {
                throw new RuntimeException("修改失败！");
            }
        }
        return ResponseMessage.error("不成功！置0失败");
    }

    @Override
    public ResponseMessage addAddress(Address address) {
        int count = addressMapper.addAddress(address);
        if (count > 0){
            if (address.getIsSure() == 1){
                ResponseMessage responseMessage = this.setDefaultAddress(address);
                if (responseMessage.getCode() == 200){
                    return ResponseMessage.success();
                }
                return ResponseMessage.error("设置默认地址失败！");
            }
        }
        return ResponseMessage.error("添加地址失败！");
    }

    @Override
    public ResponseMessage getAddressByIdAndUserId(Address address) {
        Address add = addressMapper.getAddressByIdAndUserId(address);
        if (add.getIsDefault() == 1){
            add.setIsSure(1);
        }
        if (add != null){
            return ResponseMessage.success(add);
        }
        return ResponseMessage.error("没有数据！");
    }

    @Override
    public ResponseMessage updateAddress(Address address) {
        int count = addressMapper.updateAddress(address);
        if (count > 0){
            if (address.getIsSure() == 1){
                ResponseMessage responseMessage = this.setDefaultAddress(address);
                if (responseMessage.getCode() == 200){
                    return ResponseMessage.success();
                }
                return ResponseMessage.error("设置默认地址失败！");
            }
            return ResponseMessage.success("添加成功，但是不是默认地址！");
        }
        return ResponseMessage.error("更新失败！");
    }

    @Override
    public ResponseMessage deleteAddress(Address address) {
        int count = addressMapper.deleteAddress(address);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("更新失败！");
    }
}
