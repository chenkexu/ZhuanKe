package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 */

public interface NewsView extends BaseView {


    void getProjectClassifyDataSuccess(List<ProjectClassifyData> projectClassifyDatas);

    void getProjectClassifyDataError(String error);


}
