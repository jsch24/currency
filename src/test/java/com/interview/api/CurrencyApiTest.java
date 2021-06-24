package com.interview.api;

import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.interview.CurrencyApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CurrencyApplication.class)
@WebAppConfiguration
public class CurrencyApiTest {

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCoindesk() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/coindesk")).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertTrue(200 == response.getStatus());
		JSONObject obj = new JSONObject(response.getContentAsString());
		Assert.assertTrue("0".equals(obj.optString("status")));
		Assert.assertTrue("success".equals(obj.optString("msg")));
		Assert.assertTrue(
				obj.optString("updateTime").matches("20[0-9]{2}\\/[0-9]{2}\\/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}"));
		Assert.assertTrue(obj.optJSONArray("currencyInfos") != null);
		JSONArray array = obj.optJSONArray("currencyInfos");
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				JSONObject sub = array.getJSONObject(i);
				Assert.assertTrue(sub.has("englishName"));
				Assert.assertTrue(sub.has("chineseName"));
				Assert.assertTrue(sub.has("rate"));
			}
		}
	}

	@Test
	public void testInsert() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/insert").param("englishName", "HKD").param("chineseName", "港幣1"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertTrue(200 == response.getStatus());
	}

	@Test
	public void testUpdate() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/update_chineseName").param("englishName", "HKD")
				.param("chineseName", "港幣")).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertTrue(200 == response.getStatus());
		JSONObject obj = new JSONObject(response.getContentAsString(StandardCharsets.UTF_8));
		Assert.assertTrue("0".equals(obj.optString("status")));
		Assert.assertTrue("success".equals(obj.optString("msg")));
		Assert.assertTrue("HKD".equals(obj.optString("englishName")));
		Assert.assertTrue("港幣".equals(obj.optString("chineseName")));
	}

	@Test
	public void testSelect() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/select").param("englishName", "USD"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertTrue(200 == response.getStatus());
		JSONObject obj = new JSONObject(response.getContentAsString(StandardCharsets.UTF_8));
		Assert.assertTrue("0".equals(obj.optString("status")));
		Assert.assertTrue("success".equals(obj.optString("msg")));
		Assert.assertTrue("USD".equals(obj.optString("englishName")));
		Assert.assertTrue("美金".equals(obj.optString("chineseName")));
	}

	@Test
	public void testDelete() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/delete").param("englishName", "HKD"))
				.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		Assert.assertTrue(200 == response.getStatus());
	}
}
