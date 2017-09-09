package com.lsl.customeview.view;

import com.lsl.customeview.bean.MainItemBean;

import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 12:10
 * 描述：主页视图层接口
 * 修改人：
 * 修改时间：
 * 备注：
 */
public interface IMainView {

    /**
     * 显示数据
     * @param dataList
     */
    void showData(List<MainItemBean> dataList);
}
