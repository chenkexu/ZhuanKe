package com.dfwr.zhuanke.zhuanke.api;


import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by huang on 2018/4/16.
 */

public interface ApiService {


    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 导航数据
     */
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目分类数据
     */
    Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid);
}
