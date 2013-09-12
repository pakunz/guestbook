package guestbook;

import java.util.ArrayList;
import java.util.List;

public class EntryInMemoryDAO implements EntryDAO {

	private static List<Entry> entries = new ArrayList<Entry>();

	@Override
	public List<Entry> getEntries() {
		return entries;
	}

	@Override
	public void addEntry(Entry entry) {
		entries.add(entry);
	}

}
