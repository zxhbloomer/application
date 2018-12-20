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

	private static Integer SUM = 0 ;
	private static final String ADDRESS = "http://localhost:9501/service/indent/order/receiverGoods?goodsName=iPhone&token=123456789";

	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newFixedThreadPool(20);

		for(int i=0;i<20;i++){
			exec.execute(()->{
				try {
					doGet();
				}catch (Exception e){
					e.printStackTrace();
				}
			});
		}


	}

	public static void doGet() throws Exception{
		//创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建一个Get对象
		HttpGet get = new HttpGet(ADDRESS);

		for(int i=0;i<1000000;i++){
			//执行请求
			CloseableHttpResponse response = httpClient.execute(get);
			//取响应结果
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("请求状态 : " + statusCode);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			System.out.println(SUM + "返回内容 : " + string);
			SUM ++;
			//关闭连接
			response.close();
		}

		httpClient.close();
	}
}
