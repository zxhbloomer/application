//package com.main;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Hex;
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.security.InvalidKeyException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import static org.springframework.util.StringUtils.isEmpty;
//
//@Slf4j
//public class WebHooksUtil {
//
//    private static final String SIGNATURE_PREFIX = "sha1=";
//    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
//    private static final String SECRET = "123456";
//    private static final String HEAD_NAME = "X-Hub-Signature";
//    private WebHooksUtil(){}
//
//    /**
//     * 验证请求是否来自Github
//     */
//    public static boolean validateSignature(HttpServletRequest request){
//        //正常方式获取
//        String body = getRequestBody(request);
//        String signatureHeader = request.getHeader(HEAD_NAME);
//
////        //通过路由获取
////        String body = request.getHeader("x-gateway-body");
////        String signatureHeader = request.getHeader("x-gateway-signature");
//
//        if(isEmpty(body) || isEmpty(signatureHeader)){
//            return false;
//        }
//
//        try {
//            byte[] payload = body.getBytes("UTF-8");
//            byte[] signature = Hex.decodeHex(signatureHeader.substring(SIGNATURE_PREFIX.length()).toCharArray());
//            byte[] validPayload = getExpectedSignature(payload);
//            return MessageDigest.isEqual(signature,validPayload);
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 计算有效负载的预期签名
//     */
//    private static byte[] getExpectedSignature(byte[] payload) {
//        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), HMAC_SHA1_ALGORITHM);
//        Mac hmac;
//        try {
//            hmac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
//            hmac.init(key);
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException("Hmac SHA1 must be supported", e);
//        } catch (InvalidKeyException e) {
//            throw new IllegalStateException("Hmac SHA1 must be compatible to Hmac SHA1 Secret Key", e);
//        }
//        return hmac.doFinal(payload);
//    }
//
//    /**
//     * 从Request获取Payload的内容
//     */
//    private static String getRequestBody(HttpServletRequest request){
//        StringBuilder sb = new StringBuilder();
//        BufferedReader bufferedReader;
//        try(InputStream inputStream = request.getInputStream()){
//            if(inputStream != null){
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[128];
//                int bytesRead = -1;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    sb.append(charBuffer, 0, bytesRead);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "";
//        }
//        return sb.toString();
//    }
//
//
//}
