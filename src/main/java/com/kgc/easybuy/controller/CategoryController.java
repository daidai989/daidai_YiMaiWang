package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.Category;
import com.kgc.easybuy.pojo.Page;
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

    @RequestMapping("getAllCategories")
    public ResponseMessage getAllCategories(Page page, Category category) {
        ResponseMessage responeseMsg = categoryService.getAllCategories(page,category);
        return responeseMsg;
    }

    @RequestMapping("getAllCategory")
    public ResponseMessage getAllCategory() {
        ResponseMessage responeseMsg = categoryService.getAllCategory();
        return responeseMsg;
    }

    @RequestMapping("checkCategoryName")
    public ResponseMessage checkCategoryName(String name) {
        ResponseMessage responeseMsg = categoryService.checkCategoryName(name);
        return responeseMsg;
    }

    @RequestMapping("addCategory")
    public ResponseMessage addCategory(@RequestBody Category category) {
        ResponseMessage responeseMsg = categoryService.addCategory(category);
        return responeseMsg;
    }

    @RequestMapping("deleteCategory")
    public ResponseMessage deleteCategory(int id) {
        ResponseMessage responeseMsg = categoryService.deleteCategory(id);
        return responeseMsg;
    }

    @RequestMapping("getCategory")
    public ResponseMessage getCategory(int id) {
        ResponseMessage responeseMsg = categoryService.getCategory(id);
        return responeseMsg;
    }

    @RequestMapping("updateCategory")
    public ResponseMessage updateCategory(@RequestBody Category category) {
        ResponseMessage responeseMsg = categoryService.updateCategory(category);
        return responeseMsg;
    }
}
