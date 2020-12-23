# huazai-httpclient

> # 概 述

在系统研发过程中，系统架构分为服务端、PC端、移动端、IOS端等其它端（业务场景比较复杂），在数据交互的中使用到了HttpClient，比如我们的移动端通过HttpClient到服务端的这样一个数据交互模式，当然还涉及了一些高可用等，就不在这儿讲了，那么，本篇博文主要是针对HttpClient的一些介绍及实际应用示例。

 

> # HttpClient 的应用详解

## 一、HttpClient 概述

HttpClient 是 Apache Jakarta Common 下的子项目，可以用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包，并且它支持 HTTP 协议最新的版本。

HttpClient 通过 http 协议来访问网络资源，它可以提供有效的、最新的、功能丰富 http 客户端。为了更好的拓展，HttpClient 即支持基本的 http 协议，还支持了 http-aware 客户端程序，如 web 浏览器，Webservice 客户端，以及利用或者拓展 http 协议的分布式系统等。

 

## 二、HttpClient 主要功能

1）、实现了所有 HTTP 的方法，包括常用的有：GET、POST、PUT、PATCH、DELETE等）；

2）、支持自动转发；

3）、支持 HTTPS 协议；

4）、支持代理服务器；

 

## 三、HttpClient 主要特性

1）、HttpClient 是一个基于 HttpCore 的客户端 Http 传输类库；

2）、基于传统的 IO；

3）、与内容无关的；

 

## 四、HttpClient 常见状态码

|状态码|说明|
|:---|:---|
|200 OK|请求已成功，请求所希望的响应头或数据体将随此响应返回；|
|201 Created|请求已经被实现，而且有一个新的资源已经依据请求的需要而建立，且其 URI 已经随Location 头信息返回；|
|202 Accepted|服务器已接受请求，但尚未处理；|
|400 Bad Request|语义有误，当前请求无法被服务器理解；请求参数有误；|
|403 Forbidden|服务器已经理解请求，但是拒绝执行它；|
|404 Not Found|请求失败，请求所希望得到的资源未被在服务器上发现；|
|405 Method Not Allowed|请求行中指定的请求方法不能被用于请求相应的资源；|
|415 Unsupported Media Type|对于当前请求的方法和所请求的资源，请求中提交的实体并不是服务器中所支持的格式，因此请求被拒绝；|
|500 Internal Server Error|服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理；|
|501 Not Implemented|服务器不支持当前请求所需要的某个功能；|
|502 Bad Gateway|作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应；|
|503 Service Unavailable|由于临时的服务器维护或者过载，服务器当前无法处理请求；|

HTTP状态码：【[HTTP状态码详解，这个比较全的](https://blog.csdn.net/Hello_World_QWP/article/details/78587328)】

 

## 五、HttpClient 一般使用步骤

1）、创建 HttpClient 对象；

2）、创建请求方法的实例，并指定请求URL。例如：需要发送 GET 请求，创建 HttpGet 对象；如果需要发送 POST 请求，创建HttpPost 对象等；

3）、配置请求参数。例如：需要发送 GET 请求，可调用 HttpGet 的 setParams() 方法添加参数并通过 URIBuilder 对象的 setParameter 方法来构建请求参数；如果需要发送 POST 请求，则调用 setEntity(HttpEntity entity) 方法来设置请求参数；

4）、通过调用 HttpClient 对象的 execute(final HttpUriRequest request) 发送请求，该方法返回一个 HttpResponse 对象；

5）、通过调用 HttpResponse 的 getStatusLine() 方法的 getStatusCode() 获取响应状态码；调用 HttpResponse 的 getAllHeaders()、getHeaders(String name) 方法可获取消息的响应头；调用 HttpResponse 的 getEntity() 方法可获取 HttpEntity对象，该对象包装了服务器的响应内容，程序可通过该对象获取服务器的响应内容，并通过 EntityUtils 对象的 toString(HttpEntity entity, String defaultCharset) 来转换响应内容的字符集；

6）、释放资源，关闭连接。无论请求是否执行成功，都必须释放连接，避免出现资源异常占用、挂起等现象；

 

## 六、HttpClient 使用示例
**doGet无参示例：**

```Java
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
```

**doGet有参示例：**

```Java
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
public class DoGETWithParam
{
 
	public static void main(String[] args) throws Exception
	{
 
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
 
		// 定义请求的地址及参数
		URI uri = new URIBuilder("https://xiaoyuan.zhaopin.com/api/sou").setParameter("pageNumber", "1")
				.setParameter("keyWord", "java").build();
 
		System.out.println(uri);
 
		// 创建HttpGet请求对象
		HttpGet httpGet = new HttpGet(uri);
 
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
```

**doPost无参示例：**

```Java
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
```

**doPost有参示例：**

```Java
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
public class DoPOSTWithParam
{
 
	public static void main(String[] args) throws Exception
	{
 
		// 创建HttpClient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
 
		// 创建HttpPost请求对象
		HttpPost httpPost = new HttpPost("http://39.106.243.121/eto/add");
 
		// 封装请求参数列表
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(0);
		pairs.add(new BasicNameValuePair("CityId", "530"));
		pairs.add(new BasicNameValuePair("CityName", "北京"));
		pairs.add(new BasicNameValuePair("SubCompanyNumber", "CC000773458D90000002000"));
		pairs.add(new BasicNameValuePair("CompanyName", "Oracle"));
		pairs.add(new BasicNameValuePair("CompanyTypeId", "5"));
		pairs.add(new BasicNameValuePair("IndustryName", "软件"));
		pairs.add(new BasicNameValuePair("CompanySize", "10000人以上"));
		pairs.add(new BasicNameValuePair("CompanyNumber", "DC000773458"));
		// 构造一个Form表单
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairs);
		// 设置请求体
		httpPost.setEntity(formEntity);
 
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
```
 

### 七、HttpClient 示例源代码地址【GitHub】

依赖的父级项目：[huazai-parent](https://github.com/Jackson-AndyLau/huazai-parent)

HttpClient 示例项目：[huazai-httpclient](https://github.com/Jackson-AndyLau/huazai-httpclient)

如下图：
![HttpClient 示例项目](https://raw.githubusercontent.com/Jackson-AndyLau/pictures-storage/master/002/202012/20191121115009216.png)



 

## 八、HttpClientUtils 工具类封装【HttpClient的实际应用】

【 [HttpClientUtils工具类封装](https://blog.csdn.net/Hello_World_QWP/article/details/102912797) 】

 

## 九、参考文献

【[HTTPCLIENT_APACHE官网](http://hc.apache.org/)】

【[HTTPCLIENT_百度百科](https://baike.baidu.com/item/httpclient/5766483)】
