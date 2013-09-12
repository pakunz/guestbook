package guestbook;

import java.util.List;

public interface EntryDAO {

	List<Entry> getEntries();

	void addEntry(Entry entry);

}
