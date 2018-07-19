package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

/**
 * Created by ckx on 2018/7/14.
 */

public class UserBean implements Serializable{


    private static final long serialVersionUID = -7891204557187899113L;


        /**
         * user : {"uid":22,"phone":null,"imgId":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132","wxId":"oGDHv08Ey3edzVpCB2sAAioxmqFQ","wxName":"垚垚旭","sex":"m","createDate":"2018-07-17T10:50:38.159+0000","status":0,"unionid":"ospLiwjbEZe50hjJE5RS0TBz4wnw"}
         * token : aa5e7c58c876a36b4c31ccb3afe80223
         */

        private User user;
        private String token;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class User implements Serializable{
            private static final long serialVersionUID = -6916205525267667362L;
            /**
             * uid : 22
             * phone : null
             * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132
             * wxId : oGDHv08Ey3edzVpCB2sAAioxmqFQ
             * wxName : 垚垚旭
             * sex : m
             * createDate : 2018-07-17T10:50:38.159+0000
             * status : 0
             * unionid : ospLiwjbEZe50hjJE5RS0TBz4wnw
             */

            private int uid;
            private Object phone;
            private String imgId;
            private String wxId;
            private String wxName;
            private String sex;
            private String createDate;
            private int status;
            private String unionid;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public String getImgId() {
                return imgId;
            }

            public void setImgId(String imgId) {
                this.imgId = imgId;
            }

            public String getWxId() {
                return wxId;
            }

            public void setWxId(String wxId) {
                this.wxId = wxId;
            }

            public String getWxName() {
                return wxName;
            }

            public void setWxName(String wxName) {
                this.wxName = wxName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }
        }
}

