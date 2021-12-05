package net.tcgdex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes the weakness of a single pokemon, for example: 2x to Fire
 *
 */
public class Weakness {
	
	public static JSONArray toJSON(Collection<Weakness> weakness) {
		JSONArray array = new JSONArray();
		for(Weakness attack : weakness) {
			array.put(attack.toJSON());
		}
		return array;
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("type", type.name());
		if (value != null) {
			json.put("value", value);
		}
		return json;
	}

	static List<Weakness> parse(JSONArray array) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<Weakness> result = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			result.add(new Weakness(array.getJSONObject(i)));
		}
		return result;
	}

	private final Types type;
	private final String value;

	Weakness(JSONObject json) {
		this(Types.parse(json.getString("type")), json.optString("value"));
	}

	Weakness(Types type, String value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Weakness)) {
			return false;
		}
		Weakness other = (Weakness) o;
		return Objects.deepEquals(new Object[] { this.type, this.value }, new Object[] { other.type, other.value });
	}

	/**
	 * @return Type the weakness is to
	 */
	public Types getType() {
		return type;
	}

	/**
	 * @return Descriptor of the weakness multiplier, including a leading x, for
	 *         example 'x2'. May be null
	 */
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.type, this.value);
	}

	@Override
	public String toString() {
		return String.format("%s %s", this.type, this.value);
	}

}
