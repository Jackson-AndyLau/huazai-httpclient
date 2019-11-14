package com.huazai.httpclient.common;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description HttpClient 响应信息数据载体
 *              </ul>
 * @className HttpResult
 * @package com.huazai.httpclient.common
 * @createdTime 2017年06月17日
 *
 * @version V1.0.0
 */
public class HttpResult
{

	/**
	 * 响应状态码
	 */
	private Integer code;

	/**
	 * 响应内容
	 */
	private String body;

	public HttpResult(Integer code, String body)
	{
		super();
		this.code = code;
		this.body = body;
	}

	public HttpResult()
	{
		super();
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

}
