package com.interview.service.currency.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class CoinDeskResponse {

	private Time time;
	private String disclaimer;
	private String chartName;
	private Bpi bpi;

	private CoinDeskResponse(Time time, String disclaimer, String chartName, Bpi bpi) {
		this.time = time;
		this.disclaimer = disclaimer;
		this.chartName = chartName;
		this.bpi = bpi;
	}

	public static CoinDeskResponse getInstance(HttpResponse response) throws Exception {
		try {
			String responseStr = EntityUtils.toString(response.getEntity());
			JSONObject obj = new JSONObject(responseStr);

			JSONObject timeObj = obj.getJSONObject("time");
			String updated = timeObj.optString("updated");
			String updatedISO = timeObj.optString("updatedISO");
			String updateduk = timeObj.optString("updateduk");
			Time time = new Time(updated, updatedISO, updateduk);

			String disclaimer = obj.optString("disclaimer");
			String chartName = obj.optString("chartName");

			List<CurrencyData> list = new ArrayList<>();
			JSONObject bpiObj = obj.getJSONObject("bpi");
			JSONObject usd = bpiObj.getJSONObject("USD");
			JSONObject gbp = bpiObj.getJSONObject("GBP");
			JSONObject eur = bpiObj.getJSONObject("EUR");
			list.add(CurrencyData.parseCurrencyData(usd));
			list.add(CurrencyData.parseCurrencyData(gbp));
			list.add(CurrencyData.parseCurrencyData(eur));
			Bpi bpi = new Bpi(list);

			return new CoinDeskResponse(time, disclaimer, chartName, bpi);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("parse response json error");
		}
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Bpi getBpi() {
		return bpi;
	}

	public void setBpi(Bpi bpi) {
		this.bpi = bpi;
	}

	public static class Time {

		private String updated;
		private String updatedISO;
		private String updateduk;

		public Time(String updated, String updatedISO, String updateduk) {
			this.updated = updated;
			this.updatedISO = updatedISO;
			this.updateduk = updateduk;
		}

		public String getUpdated() {
			return updated;
		}

		public void setUpdated(String updated) {
			this.updated = updated;
		}

		public String getUpdatedISO() {
			return updatedISO;
		}

		public void setUpdatedISO(String updatedISO) {
			this.updatedISO = updatedISO;
		}

		public String getUpdateduk() {
			return updateduk;
		}

		public void setUpdateduk(String updateduk) {
			this.updateduk = updateduk;
		}
	}

	public static class Bpi {

		private List<CurrencyData> currencyDataList;

		public Bpi(List<CurrencyData> currencyDataList) {
			this.currencyDataList = currencyDataList;
		}

		public List<CurrencyData> getCurrencyDataList() {
			return currencyDataList;
		}

		public void setCurrencyDataList(List<CurrencyData> currencyDataList) {
			this.currencyDataList = currencyDataList;
		}
	}

	public static class CurrencyData {

		private String code;
		private String symbol;
		private String rate;
		private String description;
		private Double rate_float;

		private CurrencyData(String code, String symbol, String rate, String description, Double rate_float) {
			this.code = code;
			this.symbol = symbol;
			this.rate = rate;
			this.description = description;
			this.rate_float = rate_float;
		}

		public static CurrencyData parseCurrencyData(JSONObject obj) {
			String code = obj.optString("code");
			String symbol = obj.optString("symbol");
			String rate = obj.optString("rate");
			String description = obj.optString("description");
			Double rate_float = obj.optDouble("rate_float");
			return new CurrencyData(code, symbol, rate, description, rate_float);
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public String getRate() {
			return rate;
		}

		public void setRate(String rate) {
			this.rate = rate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getRate_float() {
			return rate_float;
		}

		public void setRate_float(Double rate_float) {
			this.rate_float = rate_float;
		}
	}
}
