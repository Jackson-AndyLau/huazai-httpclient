package com.huazai.httpclient.post;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试带参数的POST请求
 *              </ul>
 * @className DoPOSTParam
 * @package com.huazai.httpclient.post
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class DoPOSTParam
{

	public static void main(String[] args) throws Exception
	{

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 添加信息
		HttpPost httpPost = new HttpPost("http://39.106.243.121/eto/add");

		// 设置2个post参数，一个是scope、一个是q
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(0);
		pairs.add(new BasicNameValuePair("CityId", "530"));
		pairs.add(new BasicNameValuePair("CityName", "北京"));
		pairs.add(new BasicNameValuePair("SubCompanyNumber", "CC000773458D90000002000"));
		pairs.add(new BasicNameValuePair("CompanyName", "Oracle"));
		pairs.add(new BasicNameValuePair("CompanyTypeId", "5"));
		pairs.add(new BasicNameValuePair("IndustryName", "软件"));
		pairs.add(new BasicNameValuePair("CompanySize", "10000人以上"));
		pairs.add(new BasicNameValuePair("CompanyNumber", "DC000773458"));
		// 构造一个form表单式的实体
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
		// 将请求实体设置到httpPost对象中
		httpPost.setEntity(formEntity);

		CloseableHttpResponse response = null;
		try
		{
			// 执行请求
			response = httpclient.execute(httpPost);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200)
			{
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content);
			}
		} finally
		{
			if (response != null)
			{
				response.close();
			}
			httpclient.close();
		}

	}

}
