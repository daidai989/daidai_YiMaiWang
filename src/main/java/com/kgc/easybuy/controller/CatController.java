package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Collect;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author daidai
 */
@RestController
@CrossOrigin(origins = "*")
public class CatController {
    @Autowired
    private CatService catService;
    @RequestMapping("getAllCats")
    public ResponseMessage getAllCats(String token) {
        ResponseMessage responeseMsg = catService.getProductsById(token);
        return responeseMsg;
    }
    @RequestMapping("addCats")
    public ResponseMessage addCats(@RequestBody Cat cat) {
        ResponseMessage responeseMsg = catService.addProduct(cat);
        return responeseMsg;
    }
    @RequestMapping("delCats")
    public ResponseMessage delCats(Integer catId) {
        ResponseMessage responeseMsg = catService.delProduct(catId);
        return responeseMsg;
    }
    @RequestMapping("delCatsList")
    public ResponseMessage delCatsList(@RequestBody Map<String,List> ids) {
        List idList = ids.get("ids");

        ResponseMessage responeseMsg = catService.delProductList(idList);
        return responeseMsg;
    }
    @RequestMapping("checkProductExits")
    public ResponseMessage checkProductExits(Collect collect) {
        ResponseMessage responeseMsg = catService.checkProductExits(collect);
        return responeseMsg;
    }
}
