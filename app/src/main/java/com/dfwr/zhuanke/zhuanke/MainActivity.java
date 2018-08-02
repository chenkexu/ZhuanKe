package com.dfwr.zhuanke.zhuanke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.bean.Propertie;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IMsgView;
import com.dfwr.zhuanke.zhuanke.mvp.event.ChooseFragmentEvent;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.MsgPresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MasterFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MeFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.NewsFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.WithDrawFragment;
import com.dfwr.zhuanke.zhuanke.util.AppManager;
import com.dfwr.zhuanke.zhuanke.util.CProgressDialogUtils;
import com.dfwr.zhuanke.zhuanke.util.OkGoUpdateHttpUtil;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;
import com.dfwr.zhuanke.zhuanke.widget.Dialog.AdvertisementDialog;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.vector.update_app.listener.IUpdateDialogFragmentListener;
import com.vector.update_app.utils.AppUpdateUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMsgView, MsgPresent<IMsgView>> implements IMsgView {


    private static final String TAG = MainActivity.class.getSimpleName();
    private String mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt";
    private String mUpdateUrl1 = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json1.txt";
    private boolean isShowDownloadProgress;
    private String mApkFileUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk";


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        switch (resultCode) {
            case Activity.RESULT_CANCELED:
                switch (requestCode){
                    // 得到通过UpdateDialogFragment默认dialog方式安装，用户取消安装的回调通知，以便用户自己去判断，比如这个更新如果是强制的，但是用户下载之后取消了，在这里发起相应的操作
                    case AppUpdateUtils.REQ_CODE_INSTALL_APP:
                        Toast.makeText(this,"用户取消了安装包的更新", Toast.LENGTH_LONG).show();
                        break;
                }
                break;
            default:
        }
    }
    /**
     * 最简方式
     *
     * @param
     */
    public void updateApp() {
        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(this)
                //更新地址
                .setUpdateUrl(mUpdateUrl)
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                //实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                .build()
                .update();
    }


    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;


    private NewsFragment newsFragment;
    private MasterFragment masterFragment;
    private WithDrawFragment withDrawFragment;
    private MeFragment meFragment;


    private Propertie propertie;


    /**
     * 自定义接口协议
     *
     * @param
     */
    public void updateDiy() {

//        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        Map<String, String> params = new HashMap<String, String>();

        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
        params.put("appVersion", AppUpdateUtils.getVersionName(this));
        params.put("key1", "value2");
        params.put("key2", "value3");


        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(mUpdateUrl)
                //全局异常捕获
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(false)
                //不显示通知栏进度条
//                .dismissNotificationProgress()
                //是否忽略版本
//                .showIgnoreVersion()
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度，如果是强制更新，则设置无效
//                .hideDialogOnDownloading()
                //设置头部，不设置显示默认的图片，设置图片后自动识别主色调，然后为按钮，进度条设置颜色
                .setTopPic(R.mipmap.top_8)
                //为按钮，进度条设置颜色。
                .setThemeColor(0xffffac5d)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
//                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .setUpdateDialogFragmentListener(new IUpdateDialogFragmentListener() {
                    @Override
                    public void onUpdateNotifyDialogCancel(UpdateAppBean updateApp) {
                        //用户点击关闭按钮，取消了更新，如果是下载完，用户取消了安装，则可以在 onActivityResult 监听到。

                    }
                })
                //不自动，获取
//                .setIgnoreDefParams(true)
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            final String newVersion = jsonObject.optString("new_version");
//                            double aDouble = Double.parseDouble(newVersion);
//                            Logger.d("aDouble:"+aDouble);
                            if (AppUtils.getAppVersionName().equals(newVersion))  {
                                updateAppBean.setUpdate("no");
                            }else{
                                updateAppBean.setUpdate("Yes");
                            }
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    //（必须）新版本号，
                                    .setNewVersion(newVersion)
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject.optString("apk_file_url"))
                                    //测试下载路径是重定向路径
//                                    .setApkFileUrl("http://openbox.mobilem.360.cn/index/d/sid/3282847")
//                                    .setUpdateDefDialogTitle(String.format("AppUpdate 是否升级到%s版本？", newVersion))
                                    //（必须）更新内容
//                                    .setUpdateLog(jsonObject.optString("update_log"))
                                    //测试内容过度
//                                    .setUpdateLog("测试")
                                    .setUpdateLog("更新了新的版本，解决了若干个bug")
//                                    .setUpdateLog("今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说相对于其他行业来说今天我们来聊一聊程序员枯燥的编程生活，相对于其他行业来说\r\n")
                                    //大小，不设置不显示大小，可以不设置
                                    .setTargetSize(jsonObject.optString("target_size"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(false)
                                    //设置md5，可以不设置
                                    .setNewMd5(jsonObject.optString("new_md5"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    @Override
                    protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        CProgressDialogUtils.showProgressDialog(MainActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        CProgressDialogUtils.cancelProgressDialog(MainActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
                        Toast.makeText(MainActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取全局属性
        getProperties();
        showAdDialog();
        updateDiy();
//        updateApp();
    }


    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void chooseFragment(ChooseFragmentEvent chooseStr){
        selectedFragment(0);
        tabSelected(llHome);
    }




    //获取各个单价
    public void getProperties(){
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getProperties(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<Propertie>>rxSchedulerHelper())
                .subscribe(new BaseObserver<Propertie>() {
                    @Override
                    protected void onSuccees(ApiResponse<Propertie> t) {
                        if (t!=null) {
                            Propertie result = t.getResult();
                            MainActivity.this.propertie = result;
                            selectedFragment(0);
                            tabSelected(llHome);
                        }
                    }
                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }



    //显示广告弹窗
    private void showAdDialog() {
        AdvertisementDialog advertisementDialog = new AdvertisementDialog(this);
        advertisementDialog.showDialog();
    }



    @Override
    public void onResume() {
        super.onResume();
        if (getIntent()!=null){
            Intent intent = getIntent();
            String stringExtra = intent.getStringExtra(Systems.from_withdraw);
            if (stringExtra!=null) {
                switch (stringExtra){
                    case "0":
                        selectedFragment(0);
                        tabSelected(llHome);
                        break;
                    case "1":
                        break;
                }
            }

        }
    }




    @OnClick({R.id.ll_home, R.id.ll_category, R.id.ll_service, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(llHome);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(llCategory);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(llService);
                break;
            case R.id.ll_mine:
                selectedFragment(3);
                tabSelected(llMine);
                break;
        }
    }


    public void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Systems.propertie,propertie);
                    newsFragment.setArguments(bundle);
                    transaction.add(R.id.content, newsFragment);
                } else
                    transaction.show(newsFragment);
                break;
            case 1:
                if (masterFragment == null) {
                    masterFragment = new MasterFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Systems.propertie,propertie);
                    masterFragment.setArguments(bundle);
                    transaction.add(R.id.content, masterFragment);
                } else
                    transaction.show(masterFragment);
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.content, meFragment);
                } else
                    transaction.show(meFragment);
                break;
            case 3:
                if (withDrawFragment == null) {
                    withDrawFragment = new WithDrawFragment();
                    transaction.add(R.id.content, withDrawFragment);
                } else
                    transaction.show(withDrawFragment);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (newsFragment != null)
            transaction.hide(newsFragment);
        if (masterFragment != null)
            transaction.hide(masterFragment);
        if (withDrawFragment != null)
            transaction.hide(withDrawFragment);
        if (meFragment != null)
            transaction.hide(meFragment);
    }

    public void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llMine.setSelected(false);
        linearLayout.setSelected(true);
    }


    private long exitTime;
    @Override
    public void onBackPressed() {
        //显示在：发现Fragment
        long nowTime = System.currentTimeMillis();
        if((nowTime - exitTime) <= 2000){
            AppManager.getAppManager().AppExit(this);
        }else{
            ToastUtils.showShort("再按一次退出APP！");
            exitTime = nowTime;
        }
    }



    @Override
    protected MsgPresent<IMsgView> createPresent() {
        return new MsgPresent<>(this);
    }


    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }
}
