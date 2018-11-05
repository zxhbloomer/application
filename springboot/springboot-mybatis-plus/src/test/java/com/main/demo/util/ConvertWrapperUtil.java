package com.main.demo.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.main.entity.SysResource;
import com.main.demo.annotation.EqualCondition;
import com.main.demo.annotation.GreaterValue;
import com.main.demo.annotation.LessValue;
import com.main.demo.annotation.LikeCondition;
import com.main.demo.vqo.BasicVqo;
import com.main.demo.vqo.SysResourceVqo;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertWrapperUtil {

	/**
	 * 根据Vqo转化QueryWrapper
	 * @param v
	 * @return
	 */
	public static <V extends BasicVqo> QueryWrapper<SysResource> convert(V v) throws Exception{
		if(v == null){
			return null;
		}
		QueryWrapper<SysResource> query = new QueryWrapper<>();
		Class<SysResourceVqo> vc = (Class<SysResourceVqo>) v.getClass();

		Field[] vfs = vc.getDeclaredFields();

		//条件
		for(Field f : vfs){
			f.setAccessible(true);
			String name = upperCharToUnderLine(f.getName());
			Object value = f.get(v);

			GreaterValue greaterValue = f.getAnnotation(GreaterValue.class);
			LessValue lessValue = f.getAnnotation(LessValue.class);
			EqualCondition equalCondition = f.getAnnotation(EqualCondition.class);
			LikeCondition likeCondition = f.getAnnotation(LikeCondition.class);


			if(value != null){
				if(greaterValue != null){
					query.gt(greaterValue.value()[0],value);
					continue;
				}
				if(lessValue != null){
					query.lt(lessValue.value()[0],value);
					continue;
				}

				if(likeCondition != null || (equalCondition == null && value instanceof String)){
					query.like(name,value);
					continue;
				}
				query.eq(name,value);
			}
		}

		//排序
		if(isNotEmpty(v.getField()) && isNotEmpty(v.getSort())){
			if("desc".equalsIgnoreCase(v.getSort())){
				query.orderByDesc(upperCharToUnderLine(v.getField()));
			}else{
				query.orderByAsc(upperCharToUnderLine(v.getField()));
			}
		}else{
			query.orderByDesc("create_time");
		}

		return query;
	}

	public static boolean isNotEmpty(String str){
		return str != null && str.length() !=0;
	}

	public static boolean isEmpty(String str){
		return !isNotEmpty(str);
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

}
