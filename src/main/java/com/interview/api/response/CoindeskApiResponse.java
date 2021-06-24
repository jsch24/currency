package com.interview.api.response;

import java.util.List;

public class CoindeskApiResponse {

	private Integer status;
	private String msg;
	private String updateTime;
	private List<CurrencyInfo> currencyInfos;

	public CoindeskApiResponse(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public CoindeskApiResponse(Integer status, String msg, String updateTime, List<CurrencyInfo> currencyInfos) {
		this.status = status;
		this.msg = msg;
		this.updateTime = updateTime;
		this.currencyInfos = currencyInfos;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<CurrencyInfo> getCurrencyInfos() {
		return currencyInfos;
	}

	public void setCurrencyInfos(List<CurrencyInfo> currencyInfos) {
		this.currencyInfos = currencyInfos;
	}

	public static class CurrencyInfo {

		private String englishName;
		private String chineseName;
		private Double rate;

		public CurrencyInfo(String englishName, String chineseName, Double rate) {
			this.englishName = englishName;
			this.chineseName = chineseName;
			this.rate = rate;
		}

		public String getEnglishName() {
			return englishName;
		}

		public void setEnglishName(String englishName) {
			this.englishName = englishName;
		}

		public String getChineseName() {
			return chineseName;
		}

		public void setChineseName(String chineseName) {
			this.chineseName = chineseName;
		}

		public Double getRate() {
			return rate;
		}

		public void setRate(Double rate) {
			this.rate = rate;
		}
	}

}
