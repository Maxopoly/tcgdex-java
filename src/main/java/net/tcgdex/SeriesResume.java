package net.tcgdex;

import org.json.JSONObject;

/**
 * Contains all information describing a series, an overarching group of sets, for example XY
 *
 */
public class SeriesResume {
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		return json;
	}

	private final String id;
	private final String name;

	SeriesResume(JSONObject json) {
		this(json.getString("id"), json.getString("name"));
	}

	SeriesResume(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return Serie unique ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Serie name
	 */
	public String getName() {
		return name;
	}

}
