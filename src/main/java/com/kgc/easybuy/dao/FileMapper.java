package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.File;
import com.kgc.easybuy.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author daidai
 */
@Mapper
public interface FileMapper {
    public int addFile(File file);

    public boolean updateProId(@Param("proId") Integer proId, @Param("id")Integer id);

}
