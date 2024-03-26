package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import com.kgc.easybuy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author daidai
 */
@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getFirstCategory")
    public ResponseMessage getFirstCategory() {
        ResponseMessage responeseMsg = categoryService.getFirstCategories();
        return responeseMsg;
    }
    @RequestMapping("getSecondCategory")
    public ResponseMessage getSecondCategory(Integer id) {
        ResponseMessage responeseMsg = categoryService.getSecondCategories(id);
        return responeseMsg;
    }
    @RequestMapping("getThirdCategory")
    public ResponseMessage getThirdCategory(Integer id) {
        ResponseMessage responeseMsg = categoryService.getThirdCategories(id);
        return responeseMsg;
    }

    @RequestMapping("getSecondAllCategories")
    public ResponseMessage getSecondAllCategories() {
        ResponseMessage responeseMsg = categoryService.getSecondAllCategories();
        return responeseMsg;
    }

    @RequestMapping("getAllCategory")
    public ResponseMessage getAllCategory() {
        ResponseMessage responeseMsg = categoryService.getAllCategory();
        return responeseMsg;
    }

}
