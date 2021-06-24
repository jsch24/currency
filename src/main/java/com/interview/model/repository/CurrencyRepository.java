package com.interview.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.interview.model.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

	@Modifying
	@Transactional
	@Query(value = "update currency set chinese_name = :chineseName where english_name = :englishName", nativeQuery = true)
	public Integer updateChineseNameByEnglishName(@Param("chineseName") String chineseName,
			@Param("englishName") String englishName);

	@Modifying
	@Transactional
	@Query(value = "delete from currency where english_name = :englishName", nativeQuery = true)
	public Integer deleteByEnglishName(@Param("englishName") String englishName);

	@Query(value = "select * from currency where english_name = :englishName", nativeQuery = true)
	public Currency findByEnglishName(@Param("englishName") String englishName);

}
