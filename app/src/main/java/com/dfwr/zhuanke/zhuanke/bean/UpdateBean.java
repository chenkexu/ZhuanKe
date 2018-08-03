package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

/**
 * Created by ckx on 2018/8/2.
 */

public class UpdateBean implements Serializable {

    private static final long serialVersionUID = -8071680159443097041L;


    /**
     * code : 200
     * message : success
     * result : {"desc":"1、11111\\n2、222\\n3、333","forceUpdate":0,"id":1,"link":"http://www.baidu.com","packageSize":"5","status":1,"updateDate":1533191575000,"versionNum":"1.0.0"}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * desc : 1、11111\n2、222\n3、333
         * forceUpdate : 0
         * id : 1
         * link : http://www.baidu.com
         * packageSize : 5
         * status : 1
         * updateDate : 1533191575000
         * versionNum : 1.0.0
         */

        private String desc;
        private int forceUpdate;
        private int id;
        private String link;
        private String packageSize;
        private int status;
        private long updateDate;
        private String versionNum;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(int forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPackageSize() {
            return packageSize;
        }

        public void setPackageSize(String packageSize) {
            this.packageSize = packageSize;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getVersionNum() {
            return versionNum;
        }

        public void setVersionNum(String versionNum) {
            this.versionNum = versionNum;
        }
    }
}
