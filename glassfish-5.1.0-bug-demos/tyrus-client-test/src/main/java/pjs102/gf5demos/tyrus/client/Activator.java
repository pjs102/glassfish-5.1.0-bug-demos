package pjs102.gf5demos.tyrus.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.tyrus.client.ClientManager;
import org.glassfish.tyrus.client.ClientProperties;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
	private static String url = "wss://echo.websocket.org";
	private SSLEngineConfigurator sslEngineConfigurator;

	@Override
	public void start(BundleContext context) throws Exception {
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

		SSLContextConfigurator defaultConfig = new SSLContextConfigurator() {
			@Override
			public SSLContext createSSLContext(final boolean throwException) {
				return TLSUtils.createTrustAllSSLContext();
			}
		};

		sslEngineConfigurator = new SSLEngineConfigurator(defaultConfig, true, false, false);

		runTest();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

	public void runTest() {

		System.out.println("Running the tyrus client to retrieve: " + url);
		try {
			final ClientManager client = ClientManager.createClient();
			client.getProperties().put(ClientProperties.SSL_ENGINE_CONFIGURATOR, sslEngineConfigurator);
			Session session = client.connectToServer(new WebSocketEndpoint(), new URI(url));
			if (session != null) {
				if (session.isOpen() == true) {
					System.out.println("Connected to: " + url);
					Thread.sleep(2000); // pause for long enough to get a response from the server.
					session.close();
				}
			}
		} catch (Exception e) {
			System.err.println("Oops: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public class WebSocketEndpoint extends Endpoint implements MessageHandler.Whole<String> {

		@Override
		public void onOpen(Session session, EndpointConfig config) {
			System.out.println("On Open - Sending Message");
			session.addMessageHandler(this);
			try {
				session.getBasicRemote().sendText("Hello World");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onMessage(String s) {
			System.out.println("> " + s.replace("\n", "\n> "));
		}

		@Override
		public void onClose(Session session, CloseReason closeReason) {
			System.out.println("On Close : " + closeReason);
		}

		@Override
		public void onError(Session session, Throwable th) {
			System.err.println("On Error");
			th.printStackTrace(System.err);
		}
	}

}
