package pjs102.gf5demos.javaxjson.test;

import java.io.OutputStream;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import pjs102.gf5demos.javaxjson.test.model.Employee;


public class EmployeeJSONGenerator {

	public void generate(OutputStream out) {

		try (JsonGenerator jsonGenerator = Json.createGenerator(out)) {
			/**
			 * We can get JsonGenerator from Factory class also
			 * JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
			 * jsonGenerator = factory.createGenerator(fos);
			 */

			Employee emp = EmployeeJSONWriter.createEmployee();
			jsonGenerator.writeStartObject(); // {
			jsonGenerator.write("id", emp.getId()); // "id":123
			jsonGenerator.write("name", emp.getName());
			jsonGenerator.write("role", emp.getRole());
			jsonGenerator.write("permanent", emp.isPermanent());

			jsonGenerator.writeStartObject("address") // start of address object
				.write("street", emp.getAddress().getStreet()).write("city", emp.getAddress().getCity())
				.write("zipcode", emp.getAddress().getZipcode()).writeEnd(); // end of address object

			jsonGenerator.writeStartArray("phoneNumbers"); // start of phone num array
			for (long num : emp.getPhoneNumbers()) {
				jsonGenerator.write(num);
			}
			jsonGenerator.writeEnd(); // end of phone num array
			jsonGenerator.writeEnd(); // }

		}

	}

}
