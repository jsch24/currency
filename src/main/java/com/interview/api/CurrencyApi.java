package com.interview.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.interview.api.response.CoindeskApiResponse;
import com.interview.api.response.CurrencyResponse;
import com.interview.service.currency.CurrencyService;

@RestController
public class CurrencyApi {

	@Autowired
	private CurrencyService currencyService;

	@PostMapping("/coindesk")
	public CoindeskApiResponse coindesk() {
		return currencyService.coindesk();
	}

	@PostMapping("/insert")
	public void insert(@RequestParam String englishName, @RequestParam String chineseName) {
		currencyService.insert(englishName, chineseName);
	}

	@PostMapping("/update_chineseName")
	public CurrencyResponse updateChineseName(@RequestParam String englishName, @RequestParam String chineseName) {
		return currencyService.updateChineseName(englishName, chineseName);
	}

	@PostMapping("/delete")
	public void delete(@RequestParam("englishName") String englishName) {
		currencyService.delete(englishName);
	}

	@PostMapping("/select")
	public CurrencyResponse select(@RequestParam String englishName) {
		return currencyService.select(englishName);
	}

}
