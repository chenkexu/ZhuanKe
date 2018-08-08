package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

public class RankBean implements Serializable{


	private static final long serialVersionUID = 2515741704210271097L;


		/**
		 * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132
		 * num : 0
		 * status : 0
		 * uid : 1
		 * userBalance : 0.0
		 * wxId : oGDHv08Ey3edzVpCB2sAAioxmqFQ
		 * wxName : 垚垚旭
		 */

		private String imgId;
		private int num;
		private int status;
		private int uid;
		private double totalMoney;
		private String wxId;
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

		public double getTotalMoney() {
			return totalMoney;
		}

		public void setTotalMoney(double totalMoney) {
			this.totalMoney = totalMoney;
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
