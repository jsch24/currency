package com.interview.service.currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.interview.api.response.CoindeskApiResponse;
import com.interview.api.response.CoindeskApiResponse.CurrencyInfo;
import com.interview.api.response.CurrencyResponse;
import com.interview.model.dao.CurrencyDao;
import com.interview.model.entity.Currency;
import com.interview.service.currency.dto.CoinDeskManager;
import com.interview.service.currency.dto.CoinDeskResponse;
import com.interview.service.currency.dto.CoinDeskResponse.CurrencyData;
import com.interview.service.currency.dto.CoinDeskResponse.Time;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyDao currencyDao;

	public CoindeskApiResponse coindesk() {
		CoinDeskResponse response;
		try {
			response = CoinDeskManager.getCurrentPrice();
		} catch (Exception e) {
			e.printStackTrace();
			return new CoindeskApiResponse(-1, "error");
		}
		String updateTime = null;
		Time time = response.getTime();
		if (!time.getUpdated().isEmpty()) {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss z");
			updateTime = LocalDateTime.parse(time.getUpdated(), inputFormatter)
					.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		}

		List<CurrencyInfo> currencyInfos = new ArrayList<>();
		for (CurrencyData currencyData : response.getBpi().getCurrencyDataList()) {
			Currency currency = currencyDao.findByEnglishName(currencyData.getCode());
			if (Optional.ofNullable(currency).isPresent()) {
				currencyInfos.add(new CurrencyInfo(currency.getEnglishName(), currency.getChineseName(),
						currencyData.getRate_float()));
			}
		}

		return new CoindeskApiResponse(0, "success", updateTime, currencyInfos);
	}

	public void insert(String englishName, String chineseName) {
		Currency currency = new Currency(englishName, chineseName);
		currencyDao.save(currency);
	}

	public CurrencyResponse updateChineseName(String englishName, String chineseName) {
		int updateCount = currencyDao.updateChineseNameByEnglishName(chineseName, englishName);

		if (updateCount == 0) {
			System.out.println("update failed due to englishName not found with englishName: " + englishName);
			return new CurrencyResponse(-1, "error");
		}

		return new CurrencyResponse(0, "success", englishName, chineseName);
	}

	public void delete(String englishName) {
		int updateCount = currencyDao.deleteByEnglishName(englishName);

		if (updateCount == 0) {
			System.out.println("delete failed due to englishName not found with englishName: " + englishName);
		}
	}

	public CurrencyResponse select(String englishName) {
		Currency currency = currencyDao.findByEnglishName(englishName);
		if (Optional.ofNullable(currency).isPresent()) {
			return new CurrencyResponse(0, "success", currency.getEnglishName(), currency.getChineseName());
		} else {
			return new CurrencyResponse(-1, "error");
		}
	}

}
