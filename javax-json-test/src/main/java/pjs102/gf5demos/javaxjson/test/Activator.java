package pjs102.gf5demos.javaxjson.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import pjs102.gf5demos.javaxjson.test.model.Employee;


/**
 * Example based on https://www.journaldev.com/2315/java-json-example
 */
public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		// stop(context);

		EmployeeJSONReader reader = new EmployeeJSONReader();
		try (InputStream in = Activator.class.getResourceAsStream("/employee.json")) {
			System.out.println("Object Reader Example");
			Employee employee = reader.read(in);
			System.out.println(employee);
		}

		EmployeeJSONParser parser = new EmployeeJSONParser();
		try (InputStream in = Activator.class.getResourceAsStream("/employee.json")) {
			System.out.println("Object Parser Example");
			Employee employee = parser.parse(in);
			System.out.println(employee);
		}

		System.out.println("Object generator Example");
		EmployeeJSONGenerator generator = new EmployeeJSONGenerator();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		generator.generate(out);
		Employee employee = reader.read(new ByteArrayInputStream(out.toByteArray()));
		System.out.println(employee);

		System.out.println("Object writer Example");
		EmployeeJSONWriter writer = new EmployeeJSONWriter();
		out = new ByteArrayOutputStream();
		writer.write(out);
		employee = parser.parse(new ByteArrayInputStream(out.toByteArray()));
		System.out.println(employee);


	}

	@Override
	public void stop(BundleContext context) throws Exception {}


}
