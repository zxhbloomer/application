package com.main;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainApplication {

	public static void main(String[] args) throws Exception{
		doGet("http://104.194.212.19/bbs/index.php?gid=303");
	}

	public static void doGet(String address) throws Exception{
		//创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Get对象
		HttpGet get = new HttpGet(address);
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		System.out.println("请求状态 : " + response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println("返回内容 : " + string);
		//关闭连接
		response.close();
		httpClient.close();
	}
}
