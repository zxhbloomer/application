package com.main;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
	 * 给出一个文件的路径,判读此文件是否包含字符串(默认不区分大小写)
	 */
	public static boolean getFileWithKeys(String path,String group,String[] keys , Boolean capital){
		if(keys != null && keys.length != 0){
			try(Reader in = new FileReader(path)) {
				char[] buffer = new char[1024];
				int len = 0;
				while((len = in.read(buffer)) != -1){
					for (String key : keys) {
						if(isNotEmpty(key)){
							List<String> names = Arrays.asList(key.split(",")).stream().filter(u->isNotEmpty(u)).collect(Collectors.toList());
							String bufferStr = new String(buffer);
							for(String name:names){
								if(bufferStr.toUpperCase().indexOf(group.toUpperCase()) != -1){
									if(capital){
										if(bufferStr.toUpperCase().indexOf(name.toUpperCase()) == -1){
											return false;
										}
									}else{
										if(bufferStr.indexOf(name) == -1){
											return false;
										}
									}
									return true;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean getFileWithKeys(String path,String group,String[] keys){
		return getFileWithKeys(path,group,keys,true);
	}

	/**
	 * 根据路径获取目录下所有的文件(包括子目录下的)
	 * @param path
	 * @return
	 */
	public static File[] listAllFile(String path){
		List<File> list = new ArrayList<>();
		if(new File(path).isDirectory()){
			iterationFile(new File(path),list);
			File[] fs = new File[list.size()];
			list.toArray(fs);
			return fs;
		}
		return new File[]{};
	}
	public static File[] listAllFile(File path){
		return listAllFile(path.toString());
	}
	private static void iterationFile(File path,List<File> list){
		File[] fs = path.listFiles();
		for(File file : fs){
			if(file.isDirectory()){
				iterationFile(file,list);
			}else{
				list.add(file);
			}
		}
	}

	public static boolean isEmpty(String str){
		return str == null || str.length() == 0;
	}
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

	public static void main(String[] args){
		File contentDir = new File("F:\\FileSave\\InnerSave\\textFile");
		for(File f : listAllFile(contentDir)){
			if(getFileWithKeys(f.toString(),"",new String[]{})){
				System.out.println(f);
				copyFile(f.toString(),"F:\\Data\\"+f.getName());
			}
		}

	}

}
