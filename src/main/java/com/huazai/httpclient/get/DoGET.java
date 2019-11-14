package com.huazai.httpclient.get;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试不带参数的GET请求
 *              </ul>
 * @className DoGET
 * @package com.huazai.httpclient.get
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class DoGET
{

	public static void main(String[] args) throws Exception
	{

		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建HttpGet请求对象
		HttpGet httpGet = new HttpGet("https://xiaoyuan.zhaopin.com/api/sou?pageNumber=2");

		CloseableHttpResponse response = null;
		try
		{
			// 执行请求
			response = httpclient.execute(httpGet);
			// 是否请求成功
			if (response.getStatusLine().getStatusCode() == 200)
			{
				// 输出响应内容
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println("内容长度：" + content.length());
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
