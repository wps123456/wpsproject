package com.wps.studyes.service;

import com.wps.studyes.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {
    public void add(Item item);
    Iterable<Item> findAll();
    Page<Item> findAllPage(Map<String, Integer> params);
    Iterable<Item> findAllSort(String sort);
    List<Item> findByTitle(String title);
    List<Item> findByTitleLike(String title);
    List<Item> findByBrand(String brand);
    void deleteById(String id);
}
