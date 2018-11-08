package com.main.demo.util;

import com.main.demo.annotation.EqualCondition;
import com.main.demo.annotation.GreaterValue;
import com.main.demo.annotation.LessValue;
import com.main.demo.annotation.LikeCondition;
import com.main.demo.vqo.BasicVqo;
import com.main.demo.vqo.SysResourceVqo;
import com.main.entity.SysResource;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertWrapperUtil {

	/**
	 * 根据Vqo转化Example
	 * @param v
	 * @return
	 */
	public static <V extends BasicVqo> Example convertExample(V v) throws Exception{
		if(v == null){
			return null;
		}

		Class<SysResourceVqo> vc = (Class<SysResourceVqo>) v.getClass();
		Example example = new Example(SysResource.class);
		Field[] vfs = vc.getDeclaredFields();

		//条件
		for(Field f : vfs){
			f.setAccessible(true);
			String name = f.getName();
			Object value = f.get(v);

			GreaterValue greaterValue = f.getAnnotation(GreaterValue.class);
			LessValue lessValue = f.getAnnotation(LessValue.class);
			EqualCondition equalCondition = f.getAnnotation(EqualCondition.class);
			LikeCondition likeCondition = f.getAnnotation(LikeCondition.class);


			if(value != null){
				if(greaterValue != null){
					example.and().andGreaterThanOrEqualTo(greaterValue.value()[0],value);
					continue;
				}
				if(lessValue != null){
					example.and().andLessThanOrEqualTo(lessValue.value()[0],value);
					continue;
				}

				if(likeCondition != null || (equalCondition == null && value instanceof String)){
					example.and().andLike(name,value.toString());
					continue;
				}
				example.and().andEqualTo(name,value.toString());
			}
		}

		//排序
		if(isNotEmpty(v.getField()) && isNotEmpty(v.getSort())){
			String str = upperCharToUnderLine(v.getField());
			if("desc".equalsIgnoreCase(v.getSort())){
				example.setOrderByClause(str + " desc");
			}else{
				example.setOrderByClause(str + " asc");
			}
		}else{
			example.setOrderByClause("create_time desc");
		}

		return example;
	}


	/**
	 * 大写转下划线
	 */
	public static String upperCharToUnderLine(String param) {
		Pattern p=Pattern.compile("[A-Z]");
		if(param==null ||param.equals("")){
			return "";
		}
		StringBuilder builder=new StringBuilder(param);
		Matcher mc=p.matcher(param);
		int i=0;
		while (mc.find()) {
			builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
			i++;
		}
		if('_' == builder.charAt(0)){
			builder.deleteCharAt(0);
		}
		return builder.toString();
	}

	public static boolean isNotEmpty(String str){
		return str != null && str.length() !=0;
	}

	public static boolean isEmpty(String str){
		return !isNotEmpty(str);
	}

}
