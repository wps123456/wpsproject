package com.wps.studyplatform.jsonexchange.entity;

import java.util.List;

/**
 * @Title QuestionModel
 * @Description
 * @auther wps
 * @Date 2020/7/1419:47
 */
public class QuestionModel {
    private Integer id;  //标识符
    private String title;  //标题
    private Integer type;    //分享类型
    private List<Option> options;   //选项

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
