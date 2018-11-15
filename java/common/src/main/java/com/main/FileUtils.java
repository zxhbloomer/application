package com.main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具
 */
public final class FileUtils {
	private FileUtils(){}

	/**
	 * 复制文件-Stream
	 */
	public static boolean copyFile(String from,String to){
		if(isNotEmpty(from) && isNotEmpty(to)){
			try(
					//使用缓存
					InputStream in = new BufferedInputStream(new FileInputStream(from));
					OutputStream out = new BufferedOutputStream(new FileOutputStream(to));
				) {
				byte[] buffer = new byte[1024];
				int len;
				while((len = in.read(buffer)) != -1){
					out.write(buffer,0,len);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * 复制文件-Char
	 */
	public static boolean copyFileWithChar(String from,String to){
		if(isNotEmpty(from) && isNotEmpty(to)){
			try (
					//使用编码(默认使用的是系统的编码)
					Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(from),"GB2312"));
					Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to),"GB2312"));
				){
				char[] buffer = new char[1024];
				int len;
				while((len = in.read(buffer)) != -1){
					out.write(buffer,0,len);
				}
				return true;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * 给出一个文件的路径,判读此文件是否包含字符串
	 * @param path
	 * @return
	 */
	public static boolean getBookWithKey(String path,String key){
		if(isNotEmpty(path) && isNotEmpty(key)){
			try(Reader in = new FileReader(path)) {
				char[] buffer = new char[1024];
				int len = 0;
				while((len = in.read(buffer)) != -1){
					if(new String(buffer).indexOf(key) != -1){
						return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}




	/**
	 * 获取系统的编码
	 */
	public static Map getSystemCoding(){
		Map map = new HashMap();
		//系统默认编码
		map.put("encoding",System.getProperty("file.encoding"));
		//系统默认的字符编码
		map.put("defaultCharset", Charset.defaultCharset());
		//系统默认语言
		map.put("language",System.getProperty("user.language"));
		//获取系统属性列表
//		System.getProperties().list(System.out);
		//设置编码
//		System.getProperties().put("file.encoding","GB2312");
		return map;
	}


	public static boolean isEmpty(String str){
		return str == null && str.length() == 0;
	}
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}


}
