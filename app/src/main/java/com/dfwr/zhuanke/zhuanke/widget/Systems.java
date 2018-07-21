package com.dfwr.zhuanke.zhuanke.widget;

import com.dfwr.zhuanke.zhuanke.util.UIUtils;

import java.io.File;

/**
 * Created by ckx on 2018/7/12.
 */

public final class Systems {
    public static final String VERSION = "7.2.2";

    public static final String UTF_8 = "utf-8";

    public static final String title = "title";
    public static final String url = "url";
    public static final String feedArticleData = "feedArticleData";

    public static final String USER_INFO = "USER_INFO";



    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String CurrentCircleRadius = "CurrentCircleRadius";
    public static final String mCityName = "mCityName";

    public static final String from_withdraw = "from_withdraw";


    public static final String address = "address";
    public static final String newsType = "newsType";

    public static final String payAccount = "payAccount";
    public static final String payName = "payName";

    public static final String isFirstWithDraw = "isFirstWithDraw";


    /**
     * Path
     */
    public static final String PATH_DATA = UIUtils.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";    /**
     * Bottom Navigation tab classify
     */
    public static final int TAB_ONE = 0;

}
