package com.dfwr.zhuanke.zhuanke.api;


import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.api.response.BaseResponse;
import com.dfwr.zhuanke.zhuanke.bean.Article;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;
import com.dfwr.zhuanke.zhuanke.bean.MyProfit;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;
import com.dfwr.zhuanke.zhuanke.bean.Propertie;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.bean.WithDrawHistory;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by huang on 2018/4/16.
 */

public interface ApiService {


    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
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
    @POST("/article/artic_type")
    Observable<ApiResponse<List<ProjectClassifyData>>> getArticType(@FieldMap HashMap<String, Object> map);



    //文章列表详情
    @FormUrlEncoded
    @POST("/article/article_details_list")
    Observable<ApiResponse<List<Article>>> getArticleDetailsList (@FieldMap HashMap<String, Object> map);




    //收徒排行榜
    @FormUrlEncoded
    @POST("/user/student_ranking")
    Observable<ApiResponse<List<RankBean>>> studentRanking (@FieldMap HashMap<String, Object> map);

    //收入排行榜
    @FormUrlEncoded
    @POST("/user/profit_ranking")
    Observable<ApiResponse<List<RankBean>>> profitRanking (@FieldMap HashMap<String, Object> map);


    //发送验证码接口
    @FormUrlEncoded
    @POST("/send/send_message")
    Observable<ApiResponse<Object>> sendMessage (@FieldMap HashMap<String, Object> map);




    //绑定手机号
    @FormUrlEncoded
    @POST("/user/binding_phone")
    Observable<ApiResponse<Object>> bindingPhone(@FieldMap HashMap<String, Object> map);


    //获取文章链接接口
    @FormUrlEncoded
    @POST("/article/article_template")
    Observable<ApiResponse<Object>> getArticleLink(@FieldMap HashMap<String, Object> map);


    // TODO: 2018/7/26  
    //微信提现校验接口
    @FormUrlEncoded
    @POST("/change/take_money_check")
    Observable<ApiResponse<CheckWithDrawBean>> takeMoneyCheck(@FieldMap HashMap<String, Object> map);


    // TODO: 2018/7/26  
    //微信提现接口
    @FormUrlEncoded
    @POST("/change/wx_take_money")
    Observable<ApiResponse<Object>> weChatTakeMoney(@FieldMap HashMap<String, Object> map);


    //支付宝提现
    @FormUrlEncoded
    @POST("/change/zhifubao_take_money")
    Observable<ApiResponse<Object>> alipayTakeMoney(@FieldMap HashMap<String, Object> map);







    //查询文章的单价
    @GET("/properties/share_price")
    Observable<ApiResponse<String>> getSharePrice(@QueryMap HashMap<String, Object> map);


    //查询各个属性单价
    @GET("/properties/all")
    Observable<ApiResponse<Propertie>> getProperties(@QueryMap HashMap<String, Object> map);


    //提现记录
    @GET("/change/get_cash_record")
    Observable<ApiResponse<List<WithDrawHistory>>> get_cash_record(@QueryMap HashMap<String, Object> map);



    //获取我的个人收益
    @GET("/change/profit")
    Observable<ApiResponse<List<MyProfit>>> getMyProfit(@QueryMap HashMap<String, Object> map);



    // TODO: 2018/7/26 获取收徒链接
    @GET("/download/get_student_share_url")
    Observable<ApiResponse<String>> get_student_share_url(@QueryMap HashMap<String, Object> map);

}
