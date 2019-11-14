package com.huazai.test.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.huazai.httpclient.api.HttpClientAPI;
import com.huazai.httpclient.common.HttpResult;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试HttpClientAPI
 *              </ul>
 * @className TestAPIServcie
 * @package com.huazai.test.httpclient
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class TestAPIServcie
{

	@Test
	public void testdogetParam() throws Exception
	{
		// 创建HttpClientAPI对象
		HttpClientAPI service = new HttpClientAPI();
		// 设置请求参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNumber", "1");
		// 执行请求并输出请求内容
		HttpResult doGet = service.doGet("http://39.107.197.193/api/list", map);
		System.out.println(doGet.getCode());
		System.out.println(doGet.getBody());
	}

	@Test
	public void testdoPostParam() throws Exception
	{
		// 创建HttpClientAPI对象
		HttpClientAPI service = new HttpClientAPI();
		// 封装参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CityName", "上海");
		map.put("JobTitle", "物联网工程师");
		map.put("SubCompanyNumber", "CC000957796D90000028011");
		map.put("IndustryId", "990000");
		// 执行请求
		HttpResult doGet = service.doGet("http://39.107.197.193/api/save", map);
		// 输出请求内容
		System.out.println(doGet.getCode());
		System.out.println(doGet.getBody());
	}
}
