package com.dfwr.zhuanke.zhuanke.api;


import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.api.response.BaseResponse;
import com.dfwr.zhuanke.zhuanke.bean.Article;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by huang on 2018/4/16.
 */

public interface ApiService {


    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData();

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProjectListData>> getProjectListData(@Path("page") int page, @Query("cid") int cid);


    //登录接口
    @FormUrlEncoded
    @POST("user/user_login")
    Observable<ApiResponse<UserBean>> login(@FieldMap HashMap<String, Object> map);


    //获取个人信息
    @FormUrlEncoded
    @POST("/user/user_info")
    Observable<ApiResponse<UserBaseInfo>> getUserInfo(@FieldMap HashMap<String, Object> map);

    //获取文章类型
    @FormUrlEncoded
    @POST("/article/articType")
    Observable<ApiResponse<List<ProjectClassifyData>>> getArticType(@FieldMap HashMap<String, Object> map);



    //文章列表详情
    @FormUrlEncoded
    @POST("/article/articleDetailsList")
    Observable<ApiResponse<List<Article>>> getArticleDetailsList (@FieldMap HashMap<String, Object> map);




    //收徒排行榜
    @FormUrlEncoded
    @POST("/user/studentRanking")
    Observable<ApiResponse<List<RankBean>>> studentRanking (@FieldMap HashMap<String, Object> map);

    //收入排行榜
    @FormUrlEncoded
    @POST("/user/profitRanking ")
    Observable<ApiResponse<List<RankBean>>> profitRanking (@FieldMap HashMap<String, Object> map);


    //发送验证码接口
    @FormUrlEncoded
    @POST("/send/sendMessage")
    Observable<ApiResponse<Object>> sendMessage (@FieldMap HashMap<String, Object> map);




    //绑定手机号
    @FormUrlEncoded
    @POST("/user/bindingPhone")
    Observable<ApiResponse<Object>> bindingPhone(@FieldMap HashMap<String, Object> map);


    //获取文章链接接口
    @FormUrlEncoded
    @POST("/article/articleTemplate")
    Observable<ApiResponse<Object>> getArticleLink(@FieldMap HashMap<String, Object> map);




    //提现接口
    @FormUrlEncoded
    @POST("/change/takeMoney")
    Observable<ApiResponse<Object>> weChatTakeMoney(@FieldMap HashMap<String, Object> map);




    //提现校验接口
    @FormUrlEncoded
    @POST("/change/takeMoneyCheck")
    Observable<ApiResponse<CheckWithDrawBean>> takeMoneyCheck(@FieldMap HashMap<String, Object> map);


}
