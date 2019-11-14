package com.huazai.httpclient.post;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description 测试不带参数的Post请求
 *              </ul>
 * @className DoPOST
 * @package com.huazai.httpclient.post
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class DoPOST
{

	public static void main(String[] args) throws Exception
	{

		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// 创建HttpPost请求对象
		HttpPost httpPost = new HttpPost("http://www.oschina.net/");
		
		// 设置请求头
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
		CloseableHttpResponse response = null;
		try
		{
			// 执行请求
			response = httpclient.execute(httpPost);
			// 是否请求成功
			if (response.getStatusLine().getStatusCode() == 200)
			{
				// 输出请求内容
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
