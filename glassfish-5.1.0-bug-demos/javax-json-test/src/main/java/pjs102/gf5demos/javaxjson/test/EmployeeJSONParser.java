package pjs102.gf5demos.javaxjson.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import pjs102.gf5demos.javaxjson.test.model.Address;
import pjs102.gf5demos.javaxjson.test.model.Employee;

public class EmployeeJSONParser {

	public Employee parse(InputStream in) {

		try (JsonParser jsonParser = Json.createParser(in)) {

			/**
			 * We can create JsonParser from JsonParserFactory also with below code
			 * JsonParserFactory factory = Json.createParserFactory(null);
			 * jsonParser = factory.createParser(fis);
			 */

			Employee employee = new Employee();
			Address address = new Address();
			String keyName = null;
			List<Long> phoneNums = new ArrayList<Long>();

			while (jsonParser.hasNext()) {
				Event event = jsonParser.next();
				switch (event) {
					case KEY_NAME:
						keyName = jsonParser.getString();
						break;
					case VALUE_STRING:
						setStringValues(employee, address, keyName, jsonParser.getString());
						break;
					case VALUE_NUMBER:
						setNumberValues(employee, address, keyName, jsonParser.getLong(), phoneNums);
						break;
					case VALUE_FALSE:
						setBooleanValues(employee, address, keyName, false);
						break;
					case VALUE_TRUE:
						setBooleanValues(employee, address, keyName, true);
						break;
					case VALUE_NULL:
						// don't set anything
						break;
					default:
						// we are not looking for other events
				}
			}
			employee.setAddress(address);
			long[] nums = new long[phoneNums.size()];
			int index = 0;
			for (Long l : phoneNums) {
				nums[index++] = l;
			}
			employee.setPhoneNumbers(nums);
			// close resources
			return employee;
		}
	}

	private static void setNumberValues(Employee emp, Address address, String keyName, long value,
		List<Long> phoneNums) {
		switch (keyName) {
			case "zipcode":
				address.setZipcode((int) value);
				break;
			case "id":
				emp.setId((int) value);
				break;
			case "phoneNumbers":
				phoneNums.add(value);
				break;
			default:
				System.out.println("Unknown element with key=" + keyName);
		}
	}

	private static void setBooleanValues(Employee emp, Address address, String key, boolean value) {
		if ("permanent".equals(key)) {
			emp.setPermanent(value);
		} else {
			System.out.println("Unknown element with key=" + key);
		}
	}

	private static void setStringValues(Employee emp, Address address, String key, String value) {
		switch (key) {
			case "name":
				emp.setName(value);
				break;
			case "role":
				emp.setRole(value);
				break;
			case "city":
				address.setCity(value);
				break;
			case "street":
				address.setStreet(value);
				break;
			default:
				System.out.println("Unknown Key=" + key);

		}
	}


}
