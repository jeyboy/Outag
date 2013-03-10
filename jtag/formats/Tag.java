package jtag.formats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** This class is the default implementation for {@link outag.formats.Tag}
 *  and introduces some more useful functionality to be implemented. */
public class Tag {
	/** Stores the {@linkplain TagField#getId() ids} of the stored
	 * fields to the {@linkplain TagField fields} themselves. */
	protected HashMap<String, List<String>> fields = new HashMap<String, List<String>>();

	public void add(String key, String field) {
		if (field == null || field.isEmpty())
			return;

		List<String> list = (List<String>) fields.get(key);

		if (list == null) {
			list = new ArrayList<String>();
			list.add(field);
			fields.put(key, list);
		} else {
			list.add(field);
			fields.put(key, list);
		}
	}
	
	public List<String> get(String id) {
		List<String> list = (List<String>) fields.get(id);
		return (list == null) ? new ArrayList<String>() : list;
	}	

	public void addAlbum(String s) { add("album", s); }

	public void addArtist(String s) { add("artist", s); }

	public void addComment(String s) { add("comment", s); }

	public void addGenre(String s) { add("genre", s); }

	public void addTitle(String s) { add("title", s); }

	public void addTrack(String s) { add("track", s); }

	public void addYear(String s) { add("year", s); }
	
	List<String> updateLast(List<String> list, String s) {
		int index = list.size() - 1;
		list.set(index, list.get(index) + s);
		return list;
	}
	
	public void appendAlbum(String s) 	{ fields.put("album", updateLast(getAlbums(), s)); }
	public void appendArtist(String s) 	{ fields.put("artist", updateLast(getArtists(), s)); }
	public void appendTitle(String s) 	{ fields.put("artist", updateLast(getTitles(), s)); }	

	public List<String> getAlbums() { return get("album"); }

	public List<String> getArtists() { return get("artist"); }

	public List<String> getComments() { return get("comment"); }

	public List<String> getGenres() { return get("genre"); }
	
	public List<String> getTitles() { return get("title"); }

	public List<String> getTracks() { return get("track"); }

	public List<String> getYears() { return get("year"); }	

	public String getFirstAlbum() {
		List<String> l = getAlbums();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstArtist() {
		List<String> l = getArtists();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstComment() {
		List<String> l = getComments();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstGenre() {
		List<String> l = getGenres();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstTitle() {
		List<String> l = getTitles();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstTrack() {
		List<String> l = getTracks();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public String getFirstYear() {
		List<String> l = getYears();
		return (l.size() != 0) ? l.get(0) : "";
	}

	public Iterator<Object> getFields() {
		final Iterator<?> it = this.fields.entrySet().iterator();
		return new Iterator<Object>() {
			private Iterator<?> fieldsIt;

			@SuppressWarnings("unchecked")
			private void changeIt() {
				if (!it.hasNext())
					return;

				List<String> l = (List<String>) ((Map.Entry<String, List<String>>) it.next()).getValue();
				fieldsIt = l.iterator();
			}

			public boolean hasNext() {
				if (fieldsIt == null) {
					changeIt();
				}
				return it.hasNext() || (fieldsIt != null && fieldsIt.hasNext());
			}

			public Object next() {
				if (!fieldsIt.hasNext())
					changeIt();

				return fieldsIt.next();
			}

			public void remove() {
				fieldsIt.remove();
			}
		};
	}

	public boolean hasField(String id) { return get(id).size() != 0; }

	public boolean isEmpty() { return fields.size() == 0; }

//	/** Determines whether the given charset encoding may be used for the
//	 * represented tagging system.
//	 * @param enc - charset encoding.
//	 * @return <code>true</code> if the given encoding can be used. */
//	protected abstract boolean isAllowedEncoding(String enc);
	
	
//	/** @see outag.formats.Tag#setEncoding(java.lang.String) */
//	public boolean setEncoding(String enc) {
//		if (!isAllowedEncoding(enc))
//			return false;
//
//		Iterator<TagField> it = getFields();
//		while (it.hasNext()) {
//			TagField field = (TagField) it.next();
//			if (field instanceof TagTextField)
//				((TagTextField) field).setEncoding(enc);
//		}
//
//		return true;
//	}

//	/** @see java.lang.Object#toString() */
//	public String toString() {
//		StringBuffer out = new StringBuffer(100);
//		out.append("Tag content:\n");
//		Iterator<?> it = getFields();
//		while (it.hasNext()) {
//			TagField field = (TagField) it.next();
//			out.append("\t");
//			out.append(field.getId());
//			out.append(" : ");
//			out.append(field.toString());
//			out.append("\n");
//		}
//		return out.toString().substring(0, out.length() - 1);
//	}
}