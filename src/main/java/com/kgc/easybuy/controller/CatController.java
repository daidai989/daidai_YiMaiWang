package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Cat;
import com.kgc.easybuy.pojo.Product;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @RequestMapping("updateProducts")
    public ResponseMessage updateProducts(@RequestBody Map<String,List> ids) {
        List<Map<String, Object>> idList = ids.get("ids");
        List<Cat> catList = new ArrayList<>();

        for (Map<String, Object> idMap : idList) {
            Cat cat = new Cat();
            cat.setCount((Integer) idMap.get("count"));
            cat.setId((Integer) idMap.get("catId"));
            cat.setPrice(((Integer) idMap.get("price")).doubleValue()); // 将Integer转换为double
            catList.add(cat);
        }

        ResponseMessage responseMsg = catService.updateProducts(catList);
        return responseMsg;
    }
    @RequestMapping("getProductListByLst")
    @ResponseBody

    public ResponseMessage getProductListByLst(@RequestBody Map<String,List> ids){
        List idList = ids.get("ids");
        ResponseMessage responseMessage = catService.getProductListByLst(idList);
        return responseMessage;
    }
}
