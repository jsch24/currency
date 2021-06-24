package com.interview.api.response;

public class CurrencyResponse {

	private Integer status;
	private String msg;
	private String englishName;
	private String chineseName;

	public CurrencyResponse(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public CurrencyResponse(Integer status, String msg, String englishName, String chineseName) {
		this.status = status;
		this.msg = msg;
		this.englishName = englishName;
		this.chineseName = chineseName;
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
}
