package com.huazai.httpclient.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.huazai.httpclient.common.HttpResult;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description HttpClient API
 *              </ul>
 * @className APIService
 * @package com.huazai.httpclient.api
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class HttpClientAPI
{

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title doGet
	 *        <ul>
	 * @description 带参数的GET请求
	 *              </ul>
	 * @createdTime 2017年06月17日
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 * @return HttpResult
	 *
	 * @version : V1.0.0
	 */
	public HttpResult doGet(String url, Map<String, Object> map)
	{
		// 1、创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try
		{
			// 2、创建URIBuilder对象，封装请求参数
			URIBuilder uriBuilder = new URIBuilder(url);

			// 循环遍历参数集合，设置请求参数
			if (map != null)
			{
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
				}
			}
			// 3、创建HttpGet对象
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			// 4、执行请求（发送请求）
			response = httpClient.execute(httpGet);
			// 5、获取响应内容，解析封装到httpresult
			// 获取响应状态码
			Integer code = response.getStatusLine().getStatusCode();
			if (response.getEntity() != null)
			{
				// 获取响应的内容，转换成字符串
				String body = EntityUtils.toString(response.getEntity(), "utf-8");
				HttpResult result = new HttpResult(code, body);
				return result;
			} else
			{
				HttpResult result = new HttpResult(code, null);
				return result;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (response != null)
				{
					response.close();
				}
				httpClient.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title doget
	 *        <ul>
	 * @description 不带参数的GET请求
	 *              </ul>
	 * @createdTime 2017年06月17日
	 * @param url
	 * @return
	 * @throws Exception
	 * @return HttpResult
	 *
	 * @version : V1.0.0
	 */
	public HttpResult doGet(String url) throws Exception
	{

		return doGet(url, null);
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title doPost
	 *        <ul>
	 * @description 带参数的POST请求
	 *              </ul>
	 * @createdTime 2017年06月17日
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 * @return HttpResult
	 *
	 * @version : V1.0.0
	 */
	public HttpResult doPost(String url, Map<String, Object> map)
	{
		// 1、创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try
		{
			// 2、创建HttpPost对象
			HttpPost httpPost = new HttpPost(url);
			// 3、封装请求参数列表
			if (map != null)
			{
				List<NameValuePair> params = new ArrayList<>();
				// 遍历集合构建参数列表
				for (Map.Entry<String, Object> entry : map.entrySet())
				{
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				}
				// 4、创建Form表单传递参数的实体对象
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");

				httpPost.setEntity(entity);
			}
			// 5、执行请求
			response = httpClient.execute(httpPost);
			// 6、获取响应 封装到HttpResult对象中
			// 响应的状态码
			Integer code = response.getStatusLine().getStatusCode();
			if (response.getEntity() != null)
			{
				// 获取响应的内容，转换成字符串
				String body = EntityUtils.toString(response.getEntity(), "utf-8");
				HttpResult result = new HttpResult(code, body);
				return result;
			} else
			{
				HttpResult result = new HttpResult(code, null);
				return result;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (response != null)
				{
					response.close();
				}
				httpClient.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title doPost
	 *        <ul>
	 * @description 不带参数的POST请求
	 *              </ul>
	 * @createdTime 2017年06月17日
	 * @param url
	 * @return
	 * @throws Exception
	 * @return HttpResult
	 *
	 * @version : V1.0.0
	 */
	public HttpResult doPost(String url) throws Exception
	{

		return doPost(url, null);
	}

}
