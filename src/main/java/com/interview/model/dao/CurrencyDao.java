package com.interview.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.interview.model.entity.Currency;
import com.interview.model.repository.CurrencyRepository;

@Component
public class CurrencyDao {

	@Autowired
	private CurrencyRepository repo;

	public Currency save(Currency currency) {
		return repo.save(currency);
	}

	public Integer updateChineseNameByEnglishName(String chineseName, String englishName) {
		return repo.updateChineseNameByEnglishName(chineseName, englishName);
	}

	public Integer deleteByEnglishName(String englishName) {
		return repo.deleteByEnglishName(englishName);
	}

	public Currency findByEnglishName(String englishName) {
		return repo.findByEnglishName(englishName);
	}
}
