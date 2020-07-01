package com.wps.studyplatform.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * @Title PageUtils
 * @Description
 * @auther wps
 * @Date 2020/7/119:25
 */
public class PageUtils implements Serializable {
    private static final long serialVersionUID=1L;
    //总记录数
    private Long totalCount;
    //每页记录数
    private Long pageSize;
    //总页数
    private Long totalPage;
    //当前页数
    private Long currPage;
    //列表数据
    private List<?> list;

    /**
     * 分页
     * @param list         列表数据
     * @param totalCount   总记录数
     * @param pageSize     每页记录数
     * @param currPage     当前页数
     */
    public PageUtils(List<?> list,Long totalCount,Long pageSize,Long currPage){
        this.list=list;
        this.totalCount=totalCount;
        this.pageSize=pageSize;
        this.currPage=currPage;
        this.totalPage=(long)Math.ceil((double)totalCount/pageSize);
    }

    /**
     * f分页
     * @param page
     */
    public PageUtils(IPage<?> page){
        this.list=page.getRecords();
        this.totalCount=page.getTotal();
        this.pageSize=page.getSize();
        this.currPage=page.getCurrent();
        this.totalPage=page.getPages();
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
