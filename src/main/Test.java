package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) {
		String data = "{\"type\":2,\"range\":1,\"start\":1368417600,\"end\":1368547140,"
				+ "\"cityName\":\"���\",\"companyIds\":[\"12000001\"],\"companyNames\":[\"���\"],"
				+ "\"12000001\":{\"data\":[47947,48328,48573,48520],"
				+ "\"timestamps\":[1368417600,1368417900,1368418200,1368418500]}}";
		String data2 = parseJson(data);
		System.out.println(data2);
	}

	public static String parseJson(String data) {
		// ����չ�ֽ���Json�õ���ֵ
		StringBuffer buf = new StringBuffer();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(data); // ��ȡJson
			// rootNode.path("xx")���صĻ���һ��JsonNode���󣬵��ø�JsonNode����Ӧ�������õ�����Ӧ��ֵ
			int type = rootNode.path("type").asInt();
			int range = rootNode.path("range").asInt();
			long start = rootNode.path("start").asLong();
			long end = rootNode.path("end").asLong();
			String cityName = rootNode.path("cityName").asText();

			// ת��ʱ���ʽ
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

			String str = "����(type):" + type + "\r\n" + "��Χ(range):" + range
					+ "\r\n" + "��ʼʱ��(start):"
					+ sdf.format(new Date(start * 1000)) + "\r\n"
					+ "����ʱ��(end):" + sdf.format(new Date(end * 1000)) + "\r\n"
					+ "��������(cityName):" + cityName;
			buf.append(str);
			// �õ�companyIds��JsonNode����
			JsonNode companyIds = rootNode.path("companyIds");
			JsonNode companyNames = rootNode.path("companyNames");

			// ����companyIds�е�����
			for (int i = 0; i < companyIds.size(); i++) {
				String companyId = companyIds.get(i).asText();
				// ����������Json�ַ�����companyIds��companyNames�ĳ�������ͬ�ģ�����ֱ�ӱ���companyNames
				String companyName = companyNames.get(i).asText();
				// companyId��ֵ��12000001����ӦJson���е�
				// "12000001":{"data":[...],"timestamps":[....]}
				JsonNode infoNode = rootNode.path(companyId);
				// �õ�"12000001":{"data":[...],"timestamps":[....]}�е�data��timestamps��JsonNode����
				JsonNode dataNode = infoNode.path("data");
				JsonNode timestampsNode = infoNode.path("timestamps");
				// ����data��timestamps ������data.size��timestamps.size����ȵ�

				buf.append("\r\n{\r\n  ��˾ID(companyId):" + companyId
						+ "\r\n  ��˾����(companyName):" + companyName + "\r\n"
						+ " data:");
				for (int j = 0; j < dataNode.size(); j++) {
					long dataValue = dataNode.get(j).asLong();
					buf.append(dataValue + ",");
				}
				buf.append("\r\n time:");
				for (int k = 0; k < timestampsNode.size(); k++) {
					long timeValue = timestampsNode.get(k).asLong();
					buf.append(sdf.format(new Date(timeValue * 1000)) + ",");
				}
				buf.append("\r\n}\r\n");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

}
