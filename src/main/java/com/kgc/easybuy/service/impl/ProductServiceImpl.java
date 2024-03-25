package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.config.ProductEsRepository;
import com.kgc.easybuy.dao.CatMapper;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.dao.FileMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.CatService;
import com.kgc.easybuy.service.CategoryService;
import com.kgc.easybuy.service.CollectService;
import com.kgc.easybuy.service.ProductService;
import com.kgc.easybuy.util.EncodingUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class  ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ProductEsRepository per;
    @Autowired
    ElasticsearchRestTemplate es;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CatService catService;
    @Override
    public ResponseMessage getProductList(int currentPageNo,int pageSize) {
        PageHelper.startPage(currentPageNo,pageSize);
        List<Product> productList = productMapper.getProductList();
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        PageInfo PageInfo = new PageInfo(encodingPro);
        return ResponseMessage.success(PageInfo);
    }

    @Override
    public ResponseMessage getHotProduct() {
        List<Product> productList = productMapper.getHotProduct();
        List<Product> encodingPro = EncodingUtil.encoding(productList);
        return ResponseMessage.success(encodingPro);
    }

    @Override
    public ResponseMessage getProductByCategoryId() {
        List<Category> firstCategories = categoryMapper.getFirstCategories();
        List<List> productList = new ArrayList<>();
        for (Category category: firstCategories) {
            PageHelper.startPage(1,6);
            List<Product> products = productMapper.getProductByCategoryId(category.getId());
            List<Product> encodingPro = EncodingUtil.encoding(products);
            PageInfo pageInfo = new PageInfo(encodingPro);
            productList.add(pageInfo.getList());
        }
        return ResponseMessage.success(productList);
    }

    @Override
    public Product getProductById(int id) {
        Product product = productMapper.getProductById(id);
        try {
            product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public ResponseMessage viewProductsList(String currentPageNo, String name) {
        Map map = new HashMap();
        List<Product> productList = new ArrayList<>();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        int current = 1;
        if (currentPageNo != null && !"".equals(currentPageNo)) {
            current = Integer.parseInt(currentPageNo);
        }
        int pageSize = 10;
        if (name == null || "".equals(name)) {
            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
            map = sameMethods(queryBuilder, current, pageSize);
            map.put("current", current);
            map.put("pageSize", pageSize);
            return ResponseMessage.success(map);
        }
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        map= sameMethods(queryBuilder, current, pageSize);
        map.put("current", current);
        map.put("pageSize", pageSize);
        return ResponseMessage.success(map);
    }


    public Map sameMethods(QueryBuilder queryBuilder,int current, int pageSize) {
        List<Product> productList = new ArrayList<>();
        Map map=new HashMap<>();
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        SortBuilder sortBuilder = SortBuilders.fieldSort("price").order(SortOrder.DESC);
        nativeSearchQueryBuilder= nativeSearchQueryBuilder.withQuery(queryBuilder)
                .withSort(sortBuilder)
                .withPageable(PageRequest.of(current - 1, pageSize));
        SearchHits<Product> searchHits = es.search(nativeSearchQueryBuilder.build(), Product.class);
        long totalHits = searchHits.getTotalHits();
        for (SearchHit<Product> searchHit : searchHits) {
            productList.add(searchHit.getContent());
        }
        map.put("list", productList);
        map.put("total",totalHits);
        return map;
    }

    @Override
    public ResponseMessage getProducts() {
        List<Product> products = productMapper.getProducts();
        ResponseMessage response = new ResponseMessage();
        Iterable<Product> products1 = per.saveAll(products);
        if (products1 != null && products1.iterator().hasNext()) {
            response = ResponseMessage.success(products1);
        }
        return response;
    }
    @Override
    public ResponseMessage delProById(Integer id) {
        boolean flag= productMapper.delProById(id);
        if (flag){
            per.deleteById(id.toString());
            return ResponseMessage.success("删除成功",flag);
        }
        return ResponseMessage.error("删除失败");
    }

    @Override
    public ResponseMessage addProduct(Product product) {
        boolean b1 = productMapper.addProduct(product);
        String filePath = product.getFilePath();
        int userId = product.getUserId();
        File file = new File();
        file.setFilePath(filePath);
        file.setUserId(userId);
        int count=fileMapper.addFile(file);
        boolean b = productMapper.updateFileId(file.getId(), product.getId());
        boolean b2 = fileMapper.updateProId(product.getId(), file.getId());
        if (b&&b2) {
            Product save = per.save(product);
            if (save == null) {
                return ResponseMessage.error("数据保存失败，对象为空");
            }
            return ResponseMessage.success("数据保存成功", save);
        }
        return ResponseMessage.error("数据库保存失败");
    }

    @Override
    public ResponseMessage updateProduct(Product products) {
        boolean b = productMapper.updateProduct(products);

        if (b) {
            Date date=new Date();
            products.setCreateTime(date);
            Product save = per.save(products);
            return ResponseMessage.success(save);
        }
        return ResponseMessage.error("修改失败");
    }

    @Override
    public ResponseMessage getProductByLogin(String loginName) {
        Product productByLogin = productMapper.getProductByLogin(loginName);
        if (productByLogin!=null){
            return ResponseMessage.error("不可以使用");
        }
        return ResponseMessage.success();
    }

    @Override
    public ResponseMessage setProductTes() {
        List<Product> productList = productMapper.setProductTes();
        per.saveAll(productList);
        return ResponseMessage.success("成功",productList);
    }

    @Override
    public ResponseMessage getProFromEs(EsSelect esSelect) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        Page page = new Page();
        page.setCurrentPageNo(esSelect.getCurrentPageNo());
        page.setPageSize(esSelect.getPageSize());
        //匹配查询
        if(esSelect.getName() != null && !"".equals(esSelect.getName())){
            boolQueryBuilder.must(QueryBuilders.matchQuery("name",esSelect.getName()));
        }
        if (esSelect.getEnd() > 1){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").from(esSelect.getBegin()).to(esSelect.getEnd()));
        }
        if (esSelect.getEnd() == -1){
            boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(esSelect.getBegin()));
        }
        if (esSelect.getBrandId() > 0){
            boolQueryBuilder.must(QueryBuilders.matchQuery("brandId",esSelect.getBrandId()));
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        if (esSelect.isSortPrice()){
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        }else {
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        }
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page.getCurrentPageNo() -1, page.getPageSize()));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");

        nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
        SearchHits<Product> search = es.search(nativeSearchQueryBuilder.build(), Product.class);
        List<Product> productList = new ArrayList<>();
        long totalHits = search.getTotalHits();
        int totalPage = (int) totalHits;
        page.setTotalPage(totalPage);
        for(SearchHit<Product> hits: search) {
            Product prod = hits.getContent();
            Map<String, List<String>> highlightFields = hits.getHighlightFields();
            List<String> hightList = highlightFields.get("name");
            if (hightList != null && !hightList.isEmpty()){
                prod.setName(hightList.get(0));
            }
            if (esSelect.getUserId() != 0){
                //设置查找是否被收藏
                Collect collect = new Collect();
                collect.setProductId(prod.getId());
                collect.setUserId(esSelect.getUserId());
                ResponseMessage msg = collectService.getCollectionByproductIdAndUserId(collect);
                if (msg.getCode() == 200){
                    prod.setCollection(true);
                }
            }
            productList.add(prod);
        }
        page.setData(productList);
        return ResponseMessage.success(page);
    }

    @Override
    public ResponseMessage getCollectProduct(Collect collect,Page page) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Product> collectProduct = productMapper.getCollectProduct(collect);
        PageInfo pageInfo = new PageInfo(collectProduct);
        List<Product> encoding = EncodingUtil.encoding(collectProduct);
        for (Product p : encoding) {
            collect.setProductId(p.getId());
            ResponseMessage responseMessage = catService.checkProductExits(collect);
            if (responseMessage.getCode() == 200) {
                p.setShoppingCat(true);
            }
        }
        pageInfo.setList(encoding);
        return ResponseMessage.success(pageInfo);
    }

    @Override
    public ResponseMessage getRecommendProduct(int parentId, int id) {
        List<Product> recommendProduct = productMapper.getRecommendProduct(parentId,id);
        List<Product> productList = EncodingUtil.encoding(recommendProduct);
        return ResponseMessage.success(productList);
    }

    @Override
    public ResponseMessage getHistoryProduct(int userId) {
        List<Product> products= productMapper.getHistoryProduct(userId);
        List<Product> productList = EncodingUtil.encoding(products);
        return ResponseMessage.success(productList);
    }
}
