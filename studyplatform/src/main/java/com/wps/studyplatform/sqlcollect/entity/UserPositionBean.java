package com.wps.studyplatform.sqlcollect.entity;

/**
 * @Title UserPositionBean
 * @Description
 * @auther wps
 * @Date 2020/7/320:02
 */
public class UserPositionBean {
    private Long postId;
    private String postName;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
