package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

/**
 * Created by ckx on 2018/7/19.
 */

public class UserBaseInfo implements Serializable {
    private static final long serialVersionUID = -5373148577991900913L;


        /**
         * todayProfit : 0.0
         * todayStudentPofit : 0.0
         * allWithDrawMoney : 120.0
         * todayStudentNum : 0
         * studentPofit : 0.0
         * user : {"createDate":1533202583394,"imgId":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132","num":0,"phone":"18401512374","sex":"m","status":0,"uid":22,"unionid":"ospLiwjbEZe50hjJE5RS0TBz4wnw","userBalance":0,"wxId":"oGDHv08Ey3edzVpCB2sAAioxmqFQ","wxName":"垚垚旭"}
         * studentNum : 1
         * account : {"articleMoney":0,"balance":9640,"frozenAmount":138,"id":12,"shareMoney":0,"todayMoney":0,"uid":22}
         */


        private double todayProfit;        //今日收益
        private double allWithDrawMoney;     //累计提现


        private double todayStudentPofit;    //今日徒弟提成
        private double studentPofit;  //累计徒弟提成



        private int todayStudentNum;   //今日收徒数量
        private int studentNum;     //累计收徒数量






        private UserBean user;
        private AccountBean account;



        public double getTodayProfit() {
            return todayProfit;
        }

        public void setTodayProfit(double todayProfit) {
            this.todayProfit = todayProfit;
        }

        public double getTodayStudentPofit() {
            return todayStudentPofit;
        }

        public void setTodayStudentPofit(double todayStudentPofit) {
            this.todayStudentPofit = todayStudentPofit;
        }

        public double getAllWithDrawMoney() {
            return allWithDrawMoney;
        }

        public void setAllWithDrawMoney(double allWithDrawMoney) {
            this.allWithDrawMoney = allWithDrawMoney;
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
             * createDate : 1533202583394
             * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132
             * num : 0
             * phone : 18401512374
             * sex : m
             * status : 0
             * uid : 22
             * unionid : ospLiwjbEZe50hjJE5RS0TBz4wnw
             * userBalance : 0.0
             * wxId : oGDHv08Ey3edzVpCB2sAAioxmqFQ
             * wxName : 垚垚旭
             */

            private long createDate;
            private String imgId;
            private int num;
            private String phone;
            private String sex;
            private int status;
            private int uid;
            private String unionid;
            private double userBalance;
            private String wxId;
            private String wxName;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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

            public String getUnionid() {
                return unionid;
            }

            public void setUnionid(String unionid) {
                this.unionid = unionid;
            }

            public double getUserBalance() {
                return userBalance;
            }

            public void setUserBalance(double userBalance) {
                this.userBalance = userBalance;
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


        public static class AccountBean {
            /**
             * articleMoney : 0.0
             * balance : 9640.0
             * frozenAmount : 138.0
             * id : 12
             * shareMoney : 0.0
             * todayMoney : 0.0
             * uid : 22
             */

            private double articleMoney;
            private double balance; //余额
            private double frozenAmount;
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

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getFrozenAmount() {
                return frozenAmount;
            }

            public void setFrozenAmount(double frozenAmount) {
                this.frozenAmount = frozenAmount;
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
