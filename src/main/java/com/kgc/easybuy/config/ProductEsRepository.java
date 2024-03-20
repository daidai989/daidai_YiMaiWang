package com.kgc.easybuy.config;

import com.kgc.easybuy.pojo.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

public interface ProductEsRepository extends ElasticsearchRepository<Product,String> {

}
