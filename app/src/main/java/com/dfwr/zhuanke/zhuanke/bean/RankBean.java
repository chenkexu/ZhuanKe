package com.dfwr.zhuanke.zhuanke.bean;

import java.io.Serializable;

public class RankBean implements Serializable{


	private static final long serialVersionUID = 2515741704210271097L;


		/**
		 * balance : 3.0 金额
		 * num : 0   收徒多少人
		 * status : 0
		 * uid : 17
		 * wxId : 1
		 * wxName : 22
		 * imgId : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLMR8PF1W7ITA88viaxicgjRFTicy5jEAdhpSYIZgd5NI9ibboetJfnl9aKibYvfM7zqopBa53FoJMFLWg/132
		 * rank : 2
		 */

		private double balance;
		private int num;
		private int status;
		private int uid;
		private String wxId;
		private String wxName;
		private String imgId;
		private String rank;

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
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

		public String getImgId() {
			return imgId;
		}

		public void setImgId(String imgId) {
			this.imgId = imgId;
		}

		public String getRank() {
			return rank;
		}

		public void setRank(String rank) {
			this.rank = rank;
		}
	}
