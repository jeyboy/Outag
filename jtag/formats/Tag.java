package jtag.formats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** This class is the default implementation for
 * {@link outag.formats.Tag} and introduces some more useful
 * functionality to be implemented. */
public class Tag {
	/** Stores the {@linkplain TagField#getId() ids} of the stored
	 * fields to the {@linkplain TagField fields} themselves. */
	protected HashMap<String, List<String>> fields = new HashMap<String, List<String>>();

	/** @see outag.formats.Tag#add(outag.audioformats.generic.TagField) */
	public void add(String key, String field) {
		if (field == null || field.isEmpty())
			return;

		List<String> list = (List<String>) fields.get(key);

		// There was no previous item
		if (list == null) {
			list = new ArrayList<String>();
			list.add(field);
			fields.put(key, list);
		} else {
			// We append to existing list
			list.add(field);
		}
	}

	/** @see outag.formats.Tag#addAlbum(java.lang.String) */
	public void addAlbum(String s) { add("album", s); }

	/** @see outag.formats.Tag#addArtist(java.lang.String) */
	public void addArtist(String s) { add("artist", s); }

	/** @see outag.formats.Tag#addComment(java.lang.String) */
	public void addComment(String s) { add("comment", s); }

	/** @see outag.formats.Tag#addGenre(java.lang.String) */
	public void addGenre(String s) { add("genre", s); }

	/** @see outag.formats.Tag#addTitle(java.lang.String) */
	public void addTitle(String s) { add("title", s); }

	/** @see outag.formats.Tag#addTrack(java.lang.String) */
	public void addTrack(String s) { add("track", s); }

	/** @see outag.formats.Tag#addYear(java.lang.String) */
	public void addYear(String s) { add("year", s); }

	/** @see outag.formats.Tag#get(java.lang.String) */
	public List<String> get(String id) {
		List<String> list = (List<String>) fields.get(id);
		return (list == null) ? new ArrayList<String>() : list;
	}

	/** @see outag.formats.Tag#getAlbum() */
	public List<String> getAlbums() { return get("album"); }

	/** @see outag.formats.Tag#getArtist() */
	public List<String> getArtists() { return get("artist"); }

	/** @see outag.formats.Tag#getComment() */
	public List<String> getComments() { return get("comment"); }

	/** @see outag.formats.Tag#getGenre() */
	public List<String> getGenres() { return get("genre"); }
	
	/** @see outag.formats.Tag#getTitle() */
	public List<String> getTitles() { return get("title"); }

	/** @see outag.formats.Tag#getTrack() */
	public List<String> getTracks() { return get("track"); }

	/** @see outag.formats.Tag#getYear() */
	public List<String> getYears() { return get("year"); }	

	/** @see outag.formats.Tag#getFirstAlbum() */
	public String getFirstAlbum() {
		List<String> l = getAlbums();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstArtist() */
	public String getFirstArtist() {
		List<String> l = getArtists();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstComment() */
	public String getFirstComment() {
		List<String> l = getComments();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstGenre() */
	public String getFirstGenre() {
		List<String> l = getGenres();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstTitle() */
	public String getFirstTitle() {
		List<String> l = getTitles();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstTrack() */
	public String getFirstTrack() {
		List<String> l = getTracks();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFirstYear() */
	public String getFirstYear() {
		List<String> l = getYears();
		return (l.size() != 0) ? l.get(0) : "";
	}

	/** @see outag.formats.Tag#getFields() */
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


	/** @see outag.formats.Tag#hasField(java.lang.String) */
	public boolean hasField(String id) { return get(id).size() != 0; }

	/** @see outag.formats.Tag#isEmpty() */
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