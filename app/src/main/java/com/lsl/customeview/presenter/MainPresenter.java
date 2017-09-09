package com.lsl.customeview.presenter;

import com.lsl.customeview.bean.MainItemBean;
import com.lsl.customeview.model.IMainItemModel;
import com.lsl.customeview.model.impl.MainItemModelImpl;
import com.lsl.customeview.view.IMainView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/9/9 12:13
 * 描述：
 * 修改人：
 * 修改时间：
 * 备注：
 */
public class MainPresenter {

    //    IMainView mainView;
    WeakReference<IMainView> mainView;
    IMainItemModel mMainItemModel;


    public MainPresenter(IMainView mainView) {
        this.mainView = new WeakReference<>(mainView);
        mMainItemModel = new MainItemModelImpl();
    }

    public void loadData() {
        if (mMainItemModel != null) {
            mMainItemModel.loadMainItem(new IMainItemModel.MainItemOnLoadListener() {
                @Override
                public void onComplete(List<MainItemBean> list) {
                    mainView.get().showData(list);
                }
            });
        }
    }
}
