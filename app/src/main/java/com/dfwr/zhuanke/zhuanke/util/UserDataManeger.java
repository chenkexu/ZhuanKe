package com.dfwr.zhuanke.zhuanke.util;


import com.dfwr.zhuanke.zhuanke.bean.UserBean;

/**
 * Created by Mr.Z on 16/4/19.
 */
public class UserDataManeger {
    private static final String TOKEN = "usertoken";
    private static final String USERINFO = "userInfo";

    private static UserDataManeger manager;

    private UserDataManeger() {

    }

    public static UserDataManeger getInstance() {
        if (manager == null) {
            synchronized (UserDataManeger.class) {
                if (manager == null) {
                    manager = new UserDataManeger();
                }
            }
        }
        return manager;
    }

    public UserBean getUserBean(){
        UserBean userBean = (UserBean) SharedPreferencesTool.getInstance().getObjectFromShare(SharedPreferencesTool.user);
        if (userBean == null) {
            return null;
        }else{
            return userBean;
        }
    }

}
