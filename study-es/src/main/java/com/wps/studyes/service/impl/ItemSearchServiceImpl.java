package com.wps.studyes.service.impl;


import com.wps.studyes.entity.Item;
import com.wps.studyes.mapper.EsRepository;
import com.wps.studyes.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private EsRepository esRepository;
    @Override
    public void add(Item item) {
        esRepository.save(item);

    }

    @Override
    public Iterable<Item> findAll() {
        return esRepository.findAll();
    }

    @Override
    public Page<Item> findAllPage(Map<String, Integer> params) {
        return esRepository.findAll(PageRequest.of(params.get("page"),params.get("size")));
    }

    @Override
    public Iterable<Item> findAllSort(String sort) {
        return esRepository.findAll(Sort.by(sort).descending());
    }

    @Override
    public List<Item> findByTitle(String title) {
        return esRepository.findByTitle(title);
    }

    @Override
    public List<Item> findByTitleLike(String title) {
        return esRepository.findByTitleLike(title);
    }

    @Override
    public List<Item> findByBrand(String brand) {
        return esRepository.findByBrand(brand);
    }

    @Override
    public void deleteById(String id) {
        esRepository.deleteById(id);
    }
}

