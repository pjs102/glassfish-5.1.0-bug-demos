package pjs102.gf5demos.jersey.client;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		runTest();
	}

	@Override
	public void stop(BundleContext context) throws Exception {}

	public void runTest() {
		String url = "https://jsonplaceholder.typicode.com/todos/1";
		System.out.println("Running the jersey client to retrieve: " + url);
		try {
			Client client = ClientBuilder.newBuilder().build();

			WebTarget target = client.target(new URI(url));
			Response res = target.request().get();
			System.out.println("Got response: " + res.getStatus());
		} catch (Exception e) {
			System.err.println("Oops: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}
