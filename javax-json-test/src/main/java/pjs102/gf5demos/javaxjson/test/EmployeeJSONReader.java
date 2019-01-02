package pjs102.gf5demos.javaxjson.test;

import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import pjs102.gf5demos.javaxjson.test.model.Address;
import pjs102.gf5demos.javaxjson.test.model.Employee;

public class EmployeeJSONReader {

	public Employee read(InputStream in) {

		// create JsonReader object
		JsonReader jsonReader = Json.createReader(in);

		/**
		 * We can create JsonReader from Factory also
		 * JsonReaderFactory factory = Json.createReaderFactory(null);
		 * jsonReader = factory.createReader(fis);
		 */

		// get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();

		// we can close IO resource and JsonReader now
		jsonReader.close();

		// Retrieve data from JsonObject and create Employee bean
		Employee employee = new Employee();

		employee.setId(jsonObject.getInt("id"));
		employee.setName(jsonObject.getString("name"));
		employee.setPermanent(jsonObject.getBoolean("permanent"));
		employee.setRole(jsonObject.getString("role"));

		// reading arrays from json
		JsonArray jsonArray = jsonObject.getJsonArray("phoneNumbers");
		long[] numbers = new long[jsonArray.size()];
		int index = 0;
		for (JsonValue value : jsonArray) {
			numbers[index++] = Long.parseLong(value.toString());
		}
		employee.setPhoneNumbers(numbers);

		// reading inner object from json object
		JsonObject innerJsonObject = jsonObject.getJsonObject("address");
		Address address = new Address();
		address.setStreet(innerJsonObject.getString("street"));
		address.setCity(innerJsonObject.getString("city"));
		address.setZipcode(innerJsonObject.getInt("zipcode"));
		employee.setAddress(address);

		return employee;
	}

}
