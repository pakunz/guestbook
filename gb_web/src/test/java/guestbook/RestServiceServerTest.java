package guestbook;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.local.LocalConduit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RestServiceServerTest {

	private final static String ENDPOINT_ADDRESS = "local://server";

	private static Server server;

	@BeforeClass
	public static void startUpClass() throws Exception {
		JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
		serverFactory.setAddress(ENDPOINT_ADDRESS);
		serverFactory.setServiceClass(RestService.class);
		serverFactory.setProvider(new EntryDAOProvider(new EntryInMemoryDAO()));
		server = serverFactory.create();
	}

	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}

	@Test
	public void getEntriesReturns200() {
		WebClient client = createClientWithDirectDispatch();
		client.path("/guestbook/entries");
		Response response = client.get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}

	private WebClient createClientWithDirectDispatch() {
		WebClient client = WebClient.create(ENDPOINT_ADDRESS);
		WebClient.getConfig(client).getRequestContext()
				.put(LocalConduit.DIRECT_DISPATCH, Boolean.TRUE);
		return client;
	}

}
