package com.dfwr.zhuanke.zhuanke.bean;

/**
 * Created by ckx on 2018/7/26.
 */

public class MyProfit {

        /**
         * balance : 10.0
         * createDate : 1532590532000
         * id : 4
         * mchOrderNo : 1532102020767b2ef1
         * money : 1.0
         * status : 4
         * type : 4
         * uid : 22
         * user : {"createDate":1532590532000,"num":0,"status":4,"uid":22,"unionid":"88888888888","userBalance":0,"wxId":"232222222","wxName":"777777777777777"}
         */
        private double balance;
        private long createDate;
        private int id;
        private String mchOrderNo;
        private double money;
        private int status;
        private int type;
        private int uid;
        private UserBean user;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMchOrderNo() {
            return mchOrderNo;
        }

        public void setMchOrderNo(String mchOrderNo) {
            this.mchOrderNo = mchOrderNo;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
             * createDate : 1532590532000
             * num : 0
             * status : 4
             * uid : 22
             * unionid : 88888888888
             * userBalance : 0.0
             * wxId : 232222222
             * wxName : 777777777777777
             */

            private long createDate;
            private int num;
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
    }
