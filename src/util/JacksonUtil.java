package util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.AccountBean;

/**
 * 1、JSON字符串——》Java对象
 * 
 * 2、Java对象——》JSON字符串
 * 
 */

public class JacksonUtil {

	@SuppressWarnings("unused")
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;

	static {
		try {
			objectMapper = new ObjectMapper();
			jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1、bean 2 JSON
	public static String beanToJSON(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Bean、List、Map对象都可以使用该方法转换成JSON字符串
	public static String object2JSON(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// JSONStr 2 bean object
	public static Object jsonStr2Bean(String jsonStr) {
		try {
			// 需要无参的构造函数——》所以Bean对象尽量都提供无参的构造函数
			return objectMapper.readValue(jsonStr, AccountBean.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static List json2List(String json) {
		try {
			return objectMapper.readValue(json, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
