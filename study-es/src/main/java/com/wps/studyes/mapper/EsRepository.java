package com.wps.studyes.mapper;


import com.wps.studyes.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsRepository extends ElasticsearchRepository<Item,String> {
     List<Item> findByTitle(String title);
     List<Item> findByTitleLike(String title);
     List<Item> findByBrand(String brand);

    @Override
    void deleteById(String s);
}
