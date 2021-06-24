package com.interview.service.currency.dto;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class CoinDeskManager {

	public static CoinDeskResponse getCurrentPrice() throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("https://api.coindesk.com/v1/bpi/currentprice.json");
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return CoinDeskResponse.getInstance(httpResponse);
	}

}
