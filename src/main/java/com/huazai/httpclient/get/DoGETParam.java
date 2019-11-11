package com.huazai.httpclient.get;

import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试带参数的GET请求
 *              </ul>
 * @className DoGETParam
 * @package com.huazai.httpclient.get
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class DoGETParam
{

	public static void main(String[] args) throws Exception
	{

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 定义请求的参数
		URI uri = new URIBuilder("https://xiaoyuan.zhaopin.com/api/sou").setParameter("pageNumber", "1")
				.setParameter("keyWord", "java").build();

		System.out.println(uri);

		// 创建http GET请求
		HttpGet httpGet = new HttpGet(uri);

		CloseableHttpResponse response = null;
		try
		{
			// 执行请求
			response = httpclient.execute(httpGet);
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
