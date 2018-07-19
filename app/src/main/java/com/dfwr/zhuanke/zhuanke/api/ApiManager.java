package com.dfwr.zhuanke.zhuanke.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huang on 2018/4/16.
 */

public class ApiManager {



    private static ApiManager mRetrofitManager;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private ApiManager() {
        initRetrofit();
    }



    public static ApiManager getInstence(){
        if (mRetrofitManager==null){
            synchronized (ApiManager.class) {
                if (mRetrofitManager == null)
                    mRetrofitManager = new ApiManager();
            }
        }
        return mRetrofitManager;
    }

    private final Gson mGson  = new GsonBuilder().setLenient().create();  // 设置GSON的非严格模式setLenient()


    private void initRetrofit() {

//        File cacheFile = new File(Systems.PATH_CACHE);
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//        Interceptor cacheInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                if (!NetworkUtils.isConnected()) {
//                    request = request.newBuilder()
//                            .cacheControl(CacheControl.FORCE_CACHE)
//                            .build();
//                }
//                Response response = chain.proceed(request);
//                if (NetworkUtils.isConnected()) {
//                    int maxAge = 0;
//                    // 有网络时, 不缓存, 最大保存时长为0
//                    response.newBuilder()
//                            .header("Cache-Control", "public, max-age=" + maxAge)
//                            .removeHeader("Pragma")
//                            .build();
//                } else {
//                    // 无网络时，设置超时为4周
//                    int maxStale = 60 * 60 * 24 * 28;
//                    response.newBuilder()
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                            .removeHeader("Pragma")
//                            .build();
//                }
//                return response;
//            }
//        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(getHttpLoggingInterceptor());
        builder.retryOnConnectionFailure(true);
        //设置缓存
//        builder.addNetworkInterceptor(cacheInterceptor);
//        builder.addInterceptor(cacheInterceptor);
//        builder.cache(cache);


        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpContants.HOST_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .client(client)
                .build() ;
        mApiService = mRetrofit.create(ApiService.class);
    }

    /**
     * 日志输出
     * 自行判定是否添加
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d(message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    public ApiService getApiService() {
        return mApiService;
    }

}
