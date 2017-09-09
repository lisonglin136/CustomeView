package com.lsl.customeview.bean;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 11:51
 * 描述：首页条目bean
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class MainItemBean {

    private String name;
    private int imageId;

    public MainItemBean() {
    }

    public MainItemBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
