package com.main;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlibabaOssApplication.class)
@Slf4j
public class AlibabaOssApplication {


	@Test
	public void test001() throws Exception{


		Long begin = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			//1.把所需要上传的文件转化成流(InputStream)的形式
			File file = new File("C:\\Users\\Administrator\\Desktop\\咕泡学院JAVA高级课程大纲v4.0.jpg");
			InputStream in = new FileInputStream(file);

			//2.设置你上传到服务器之后文件的名称(和你本地的文件名称不同!)
			String key = UUID.randomUUID().toString().replaceAll("-","") + file.getName().substring(file.getName().lastIndexOf("."));
			System.out.println(key);
//			//3.传入到方法
			String tag = OssUploadUtil.uploadFile(in,key);
		}
		System.out.println("耗时 : " + (System.currentTimeMillis()-begin));

	}
}
