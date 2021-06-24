package com.interview.api;

import org.junit.Assert;
import org.junit.Test;
import com.interview.service.currency.dto.CoinDeskManager;
import com.interview.service.currency.dto.CoinDeskResponse;

public class CoinDeskManagerTest {

	@Test
	public void testGetCurrentPrice() throws Exception {
		CoinDeskResponse response = CoinDeskManager.getCurrentPrice();
		Assert.assertTrue(response != null);
	}

}
