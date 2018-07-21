package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

/**
 * Created by ckx on 2018/7/19.
 */

public class UserBaseInfo implements Serializable{
    private static final long serialVersionUID = -5373148577991900913L;


        /**
         * todayStudentPofit : 0.0
         * todayStudentNum : 0
         * studentPofit : 0.0
         * user : {"balance":0,"createDate":1531967352942,"imgId":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132","num":0,"sex":"m","status":0,"uid":22,"wxId":"oGDHv08Ey3edzVpCB2sAAioxmqFQ","wxName":"垚垚旭"}
         * studentNum : 0
         * account : {"articleMoney":0,"balance":0,"id":12,"shareMoney":0,"todayMoney":0,"uid":22}
         */

        private double todayStudentPofit;

        private double allWithDrawMoney = 0.0;

    public double getAllWithDrawMoney() {
        return allWithDrawMoney;
    }

    public void setAllWithDrawMoney(double allWithDrawMoney) {
        this.allWithDrawMoney = allWithDrawMoney;
    }

    private int todayStudentNum;
        private double studentPofit;
        private UserBean user;
        private int studentNum;
        private AccountBean account;


        public double getTodayStudentPofit() {
            return todayStudentPofit;
        }

        public void setTodayStudentPofit(double todayStudentPofit) {
            this.todayStudentPofit = todayStudentPofit;
        }

        public int getTodayStudentNum() {
            return todayStudentNum;
        }

        public void setTodayStudentNum(int todayStudentNum) {
            this.todayStudentNum = todayStudentNum;
        }

        public double getStudentPofit() {
            return studentPofit;
        }

        public void setStudentPofit(double studentPofit) {
            this.studentPofit = studentPofit;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(int studentNum) {
            this.studentNum = studentNum;
        }

        public AccountBean getAccount() {
            return account;
        }

        public void setAccount(AccountBean account) {
            this.account = account;
        }

        public static class UserBean {
            /**
             * balance : 0.0
             * createDate : 1531967352942
             * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132
             * num : 0
             * sex : m
             * status : 0
             * uid : 22
             * wxId : oGDHv08Ey3edzVpCB2sAAioxmqFQ
             * wxName : 垚垚旭
             */

            private double balance = 0.0;
            private long createDate;
            private String imgId;
            private int num;
            private String sex;
            private int status;
            private int uid;
            private String wxId;
            private String wxName;


            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
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
        }

        public static class AccountBean implements Serializable{

            private static final long serialVersionUID = 4276452603812547113L;

            /**
             * articleMoney : 0.0
             * balance : 0
             * id : 12
             * shareMoney : 0.0
             * todayMoney : 0.0
             * uid : 22
             */

            private double articleMoney;
            private double balance;
            private int id;
            private double shareMoney;
            private double todayMoney;
            private int uid;

            public double getArticleMoney() {
                return articleMoney;
            }

            public void setArticleMoney(double articleMoney) {
                this.articleMoney = articleMoney;
            }

            public Double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getShareMoney() {
                return shareMoney;
            }

            public void setShareMoney(double shareMoney) {
                this.shareMoney = shareMoney;
            }

            public double getTodayMoney() {
                return todayMoney;
            }

            public void setTodayMoney(double todayMoney) {
                this.todayMoney = todayMoney;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }
