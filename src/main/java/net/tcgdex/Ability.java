package net.tcgdex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes a single ability of a pokemon
 *
 */
public class Ability {
	
	public static JSONArray toJSON(Collection<Ability> abilities) {
		JSONArray array = new JSONArray();
		for(Ability attack : abilities) {
			array.put(attack.toJSON());
		}
		return array;
	}

	static List<Ability> parse(JSONArray array) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<Ability> result = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			result.add(new Ability(array.getJSONObject(i)));
		}
		return result;
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("type", type);
		json.put("name", name);
		json.put("effect", effect);
		return json;
	}

	private final String type;
	private final String name;
	private final String effect;

	Ability(JSONObject json) {
		this(json.getString("type"), json.getString("name"), json.getString("effect"));
	}

	Ability(String type, String name, String effect) {
		this.type = type;
		this.name = name;
		this.effect = effect;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Ability)) {
			return false;
		}
		Ability other = (Ability) o;
		return Objects.deepEquals(new Object[] { this.type, this.name, this.effect },
				new Object[] { other.type, other.name, other.effect });
	}

	/**
	 * 
	 * @return Description/Effect of the ability
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @return Name of the ability
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Type of the ability, for example 'Poke-POWER'
	 */
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.type, this.name, this.effect);
	}

}
