package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.config.ProductEsRepository;
import com.kgc.easybuy.dao.CategoryMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.ProductService;
import com.kgc.easybuy.util.EncodingUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductEsRepository repository;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Override
    public ResponseMessage getProductList(int currentPageNo, int pageSize) {
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
    public ResponseMessage getProductById(int id) {
        Product product = productMapper.getProductById(id);
        try {
            product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ResponseMessage.success(product);
    }

    @Override
    public ResponseMessage getRecommendProduct(Product product) {
        List<Product> recommendProduct = productMapper.getRecommendProduct(product);
        List<Product> productList = EncodingUtil.encoding(recommendProduct);
        return ResponseMessage.success(productList);
    }

    @Override
    public ResponseMessage setProductTes() {
        List<Product> productList = productMapper.setProductTes();
        repository.saveAll(productList);
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
        SearchHits<Product> search = restTemplate.search(nativeSearchQueryBuilder.build(), Product.class);
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
            productList.add(prod);
        }
        page.setData(productList);
        return ResponseMessage.success(page);
    }
}
