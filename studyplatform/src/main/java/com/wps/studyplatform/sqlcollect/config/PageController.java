package com.wps.studyplatform.sqlcollect.config;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wps.studyplatform.exception.base.BaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Title PageController
 * @Description
 * @auther wps
 * @Date 2020/7/119:50
 */
@Configuration
public class PageController<T> extends LinkedHashMap<String,Object> {
    private static final long serialVersionUID=1L;
    private Page<T> page;
    @Value("${page.current-page}")
    private long currentPage;
    @Value("${page.page-size}")
    private long pageSize;
    public synchronized Page<T> Query(Map<String,Object> params){
        //如果分页参数不同时存在，则直接报错
        if(params.get("current")!=null && params.get("size")==null){
            throw new BaseException("分页参数不全");
        }
        if(params.get("current")==null && params.get("size")!=null){
            throw new BaseException("分页参数不全");
        }
        if(params.get("current")!=null && params.get("size")!=null){
            currentPage=Long.valueOf(String.valueOf(params.get("current")));
            pageSize=Long.valueOf(String.valueOf(params.get("size")));
        }
        this.page=new Page<T>(currentPage,pageSize);
        if(params.get("current")==null && params.get("size")==null){
            //size小于0不在查询total及分页，自动调整为列表模式
            page.setSize(-1);
        }
        try {


            if (params.get("orderAsc") != null) {
                List ascOrdes = (List) params.get("orderAsc");
                ascOrdes = (List) ascOrdes.stream().map((i) -> SQLFilter.sqlInject(String.valueOf(i))).collect(Collectors.toList());
                page.setAscs(ascOrdes);
            }
            if (params.get("orderDesc") != null) {
                List descOrdes = (List) params.get("orderDesc");
                descOrdes = (List) descOrdes.stream().map((i) -> SQLFilter.sqlInject(String.valueOf(i))).collect(Collectors.toList());
                page.setAscs(descOrdes);
            }
        }catch (Exception ignored){
            ignored.printStackTrace();
        }
        return this.page;



    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
