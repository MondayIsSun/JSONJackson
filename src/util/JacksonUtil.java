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
 * 1��JSON�ַ���������Java����
 * 
 * 2��Java���󡪡���JSON�ַ���
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

	// 1��bean 2 JSON
	public static String beanToJSON(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Bean��List��Map���󶼿���ʹ�ø÷���ת����JSON�ַ���
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
			// ��Ҫ�޲εĹ��캯������������Bean���������ṩ�޲εĹ��캯��
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
