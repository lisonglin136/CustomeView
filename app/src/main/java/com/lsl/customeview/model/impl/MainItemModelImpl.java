package com.lsl.customeview.model.impl;

import com.lsl.customeview.R;
import com.lsl.customeview.bean.MainItemBean;
import com.lsl.customeview.model.IMainItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 11:57
 * 描述：主页条目实现类
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class MainItemModelImpl implements IMainItemModel {
    @Override
    public void loadMainItem(MainItemOnLoadListener listener) {
        //加载数据
        List<MainItemBean> dataList = new ArrayList<>();
        dataList.add(new MainItemBean("水面上升", R.mipmap.water));
        dataList.add(new MainItemBean("漏斗图", R.mipmap.funnel));
        dataList.add(new MainItemBean("点赞飘心", R.mipmap.funnel));

        //返回数据
        listener.onComplete(dataList);
    }
}
