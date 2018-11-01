package com.main;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {
	/**
	 *  GET/无参
	 */
	@Test
	public void doGet() throws Exception{
		//创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Get对象
		HttpGet get = new HttpGet("http://localhost:8080/student/doGetInfo");
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("请求状态 : " + statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println("返回内容 : " + string);
		//关闭连接
		response.close();
		httpClient.close();
	}

	/**
	 * GET/有参
	 * @throws Exception
	 */
	@Test
	public void doGetWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个URI对象
		URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/student/doGetWithParamInfo");
		uriBuilder.addParameter("username","刘亦菲");
		HttpGet get = new HttpGet(uriBuilder.build());
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("请求状态 : " + statusCode);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity,"utf-8");
		System.out.println("返回内容 : " + str);
		//关闭连接
		response.close();
		httpClient.close();
	}


	/**
	 * POST/无参
	 * @throws Exception
	 */
	@Test
	public void doPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Post对象
		HttpPost post = new HttpPost("http://localhost:8080/student/doPostInfo");
		//执行Post请求
		CloseableHttpResponse response = httpClient.execute(post);
		//取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("请求状态 : " + statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println("返回内容 : " + string);
		//关闭连接
		response.close();
		httpClient.close();
	}

	/**
	 * POST/有参
	 * @throws Exception
	 */
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Post对象
		HttpPost post = new HttpPost("http://localhost:8080/student/doPostWithParamInfo");
		//创建一个Entity,模拟表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username","刘亦菲"));
		StringEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
		//设置请求的内容
		post.setEntity(entity);
		//执行Post请求
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println("返回内容 : " + string);
		//关闭连接
		response.close();
		httpClient.close();
	}


}
