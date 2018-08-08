package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

/**
 * Created by ckx on 2018/8/8.
 */

public class MyStudentBean implements Serializable {

        /**
         * createDate : 1533630630000
         * id : 2
         * studentId : 6
         * uid : 3
         * user : {"imgId":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLGqibUOngLiboEYDLAb7ISlPLHDxACvrDmpy4s6uM0TQFhTK0kc3DVfzq6u85AwNNIavfoBgOzLyhg/132","num":0,"status":0,"uid":3,"userBalance":0,"wxName":"田野"}
         */

        private long createDate;
        private int id;
        private int studentId;
        private int uid;
        private UserBean user;

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLGqibUOngLiboEYDLAb7ISlPLHDxACvrDmpy4s6uM0TQFhTK0kc3DVfzq6u85AwNNIavfoBgOzLyhg/132
             * num : 0
             * status : 0
             * uid : 3
             * userBalance : 0.0
             * wxName : 田野
             */

            private String imgId;
            private int num;
            private int status;
            private int uid;
            private double userBalance;
            private String wxName;

            public String getImgId() {
                return imgId;
            }

            public void setImgId(String imgId) {
                this.imgId = imgId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
            }

            public String getWxName() {
                return wxName;
            }

            public void setWxName(String wxName) {
                this.wxName = wxName;
            }
        }
    }
