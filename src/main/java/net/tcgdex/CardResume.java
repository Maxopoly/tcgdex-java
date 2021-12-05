package net.tcgdex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Core information to describe a single card
 *
 */
public class CardResume implements Comparable<CardResume> {
	
	static List<CardResume> parse(JSONArray array) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<CardResume> result = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			result.add(new CardResume(array.getJSONObject(i)));
		}
		return result;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("localId", localId);
		json.put("name", name);
		if (image != null) {
			json.put("image", image);
		}
		return json;
	}
	
	private final String id;
	private final String localId;
	private final String name;
	private final String image;
	
	CardResume(JSONObject json) {
		this(json.getString("id"), json.getString("localId"), json.getString("name"), json.optString("image", null));
	}
	
	CardResume(String id, String localId, String name, String image) {
		this.id = id;
		this.localId = localId;
		this.name = name;
		this.image = image;
	}
	
	public String toString() {
		return String.format("%s (%s)", name, id);
	}
	
	/**
	 * @return Globally unique card ID based on the set ID and the cards ID within the set
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Card image, can be null
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * @return ID indexing this card within its set, usually just its number
	 */
	public String getLocalId() {
		return localId;
	}
	
	/**
	 * 
	 * @return Card name
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CardResume)) {
			return false;
		}
		CardResume other = (CardResume) o;
		return this.id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public int compareTo(CardResume arg0) {
		return this.id.compareTo(arg0.id);
	}
}
