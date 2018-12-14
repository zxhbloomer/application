package com.main.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JwtUtil {
	    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
	    public static final String TOKEN_PREFIX = "Bearer";
	    public static final String HEADER_AUTH = "Authorization";

	/**
	 * 生成Token
	 */
	public static String generateToken(String username) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", new Random().nextInt());
		map.put("username", username);

		String jwt = Jwts.builder()
			  .setSubject("user info").setClaims(map)
			  .signWith(SignatureAlgorithm.HS512, SECRET)
			  .compact();
		String finalJwt = TOKEN_PREFIX + " " +jwt;
		return finalJwt;
	}

	/**
	 * 验证Token,并且从Token里获取用户信息
	 */
	public static Map<String,String> validateToken(String token) {
		if (token != null) {
			HashMap<String, String> map = new HashMap<>();
			Map<String,Object> body = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();

			String id =  String.valueOf(body.get("id"));
			String username = (String) (body.get("username"));

			if(StringUtils.isEmpty(username)) {
				throw new PermissionException("username is error, please check");
			}

			map.put("id", id);
			map.put("username", username);

			return map;
		} else {
			throw new PermissionException("token is error, please check");
		}
	}

}
