package com.lsl.customeview.model;

import com.lsl.customeview.bean.MainItemBean;

import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 11:53
 * 描述：main主页条目model
 * 修改人：
 * 修改时间：
 * 备注：
 */
public interface IMainItemModel {
    //加载条目
    void loadMainItem(MainItemOnLoadListener listener);

    //条目加载监听
    interface MainItemOnLoadListener{
        void onComplete(List<MainItemBean> list);
    }
}
