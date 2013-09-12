package guestbook;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import com.google.gson.Gson;

@Path("/guestbook")
public class RestService {

	@Context
	private ContextResolver<EntryDAO> entryDAOProvider;

	EntryDAO getEntryDAO() {
		return entryDAOProvider.getContext(null);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/entries")
	public String getEntries() {
		Gson gson = new Gson();
		String json = gson.toJson(getEntryDAO().getEntries());
		return json;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/entries")
	public void postEntry(String json) {
		Gson gson = new Gson();
		Entry entry = gson.fromJson(json, Entry.class);
		entry.setDate(new Date());
		getEntryDAO().addEntry(entry);
	}

}
