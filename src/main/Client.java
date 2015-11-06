package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.AccountBean;
import util.JacksonUtil;

public class Client {

	public static void main(String[] args) {

		/**
		 * 1、bean 2 JSON
		 */
		String str = JacksonUtil.object2JSON(new AccountBean(1, "admin", "man"));
		System.out.println(str);

		System.out.println("\r\n===================================end===================================\r\n");

		/**
		 * 2、Map 2 JSON
		 */
		Map<String, AccountBean> map = new HashMap<String, AccountBean>();
		for (int i = 0; i < 100; i++) {
			AccountBean bean = new AccountBean(i, "admin_" + i, "man");
			map.put("account_" + i, bean);
		}
		System.out.println(JacksonUtil.object2JSON(map));

		System.out.println("\r\n===================================end===================================\r\n");

		/**
		 * 3、List 2 JSON
		 */
		List<AccountBean> list = new ArrayList<AccountBean>();
		for (int i = 0; i < 50; i++) {
			AccountBean bean = new AccountBean(i, "admin_" + i, "man");
			list.add(bean);
		}
		System.out.println(JacksonUtil.object2JSON(list));

		System.out.println("\r\n===================================end===================================\r\n");

		/**
		 * 4、JSON 2 Bean
		 */
		String jsonStr = "{\"id\":1,\"name\":\"admin\",\"sex\":\"man\"}";
		AccountBean bean = (AccountBean) JacksonUtil.jsonStr2Bean(jsonStr);
		System.out.println(bean);

		System.out.println("\r\n===================================end===================================\r\n");

		/**
		 * 5、JSON 2 list
		 */
		String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"
				+ "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
		List<?> list1 = JacksonUtil.json2List(json);
		System.out.println(list1);
		for (int i = 0; i < list1.size(); i++) {
			Map<?, ?> map1 = (Map<?, ?>) list1.get(i);
			Set<?> set = map1.keySet();
			for (Iterator<?> it = set.iterator(); it.hasNext();) {
				String key = (String) it.next();
				System.out.println(key + ":" + map.get(key));
			}
		}

		System.out.println("\r\n===================================end===================================\r\n");
	}
}
