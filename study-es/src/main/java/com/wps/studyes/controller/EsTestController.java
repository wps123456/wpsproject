package com.wps.studyes.controller;


import com.wps.studyes.entity.Item;
import com.wps.studyes.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Item")
public class EsTestController {


    @Autowired
    private ItemSearchService itemSearchService;


    @PostMapping("/add")
    public void save(@RequestBody Item item){
        itemSearchService.add(item);
    }
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        itemSearchService.deleteById(id);
        return "success";
    }

    @PostMapping("/getAll")
    public Iterable<Item> getAll(@RequestBody Map<String,Integer> params){
        //查找所有
        Iterable<Item> items= itemSearchService.findAll();

        //分页查询
        Page<Item> itemPage=itemSearchService.findAllPage(params);
        List<Item> itemPages=itemPage.getContent();

        //排序查询
        Iterable<Item> itemsSort =itemSearchService.findAllSort("id");

        return items;
    }

    /**
     * 根据tile查询,Item实体类中title使用了ik分词器
     * @param param
     * @return
     */
    @PostMapping("/find")
    public List<Item> findByTitle(@RequestBody Map<String,String> param){
        List<Item> items=itemSearchService.findByTitle(param.get("title"));
        return  items;
    }

    @PostMapping("/findBrand")
    public List<Item> findBrand(@RequestBody Map<String,String> param){

        return itemSearchService.findByBrand(param.get("brand"));
    }
    /**
     * 模糊查询
     */
    @PostMapping("/findByTitleLike")
    public List<Item> findByTitleLike(@RequestBody Map<String,String> param){
        return itemSearchService.findByTitleLike(param.get("title"));


    }

}
