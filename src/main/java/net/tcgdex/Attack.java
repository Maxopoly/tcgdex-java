package net.tcgdex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Describes a single attack of a pokemon, for example 'Confuse Ray'
 *
 */
public class Attack {
	
	
	public static JSONArray toJSON(Collection<Attack> attacks) {
		JSONArray array = new JSONArray();
		for(Attack attack : attacks) {
			array.put(attack.toJSON());
		}
		return array;
	}

	static List<Attack> parse(JSONArray array) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<Attack> result = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			result.add(new Attack(array.getJSONObject(i)));
		}
		return result;
	}

	private final List<Types> cost;
	private final String name;
	private final String effect;
	private final String damage;

	Attack(JSONObject json) {
		this(Types.parse(json.optJSONArray("cost")), json.getString("name"), json.optString("effect", null),
				json.optString("damage", null));
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		if (cost != null) {
			json.put("cost", Types.toJSON(cost));
		}
		if (damage != null) {
			json.put("damage", damage);
		}
		json.put("name", name);
		if (effect != null) {
			json.put("effect", effect);
		}
		return json;
	}

	Attack(List<Types> cost, String name, String effect, String damage) {
		super();
		this.cost = cost;
		this.name = name;
		this.effect = effect;
		this.damage = damage;
	}
	
	public String toString() {
		return String.format("%s (%s %s) %s", name, damage, cost.toString(), effect);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Attack)) {
			return false;
		}
		Attack other = (Attack) o;
		return Objects.deepEquals(new Object[] { this.cost, this.name, this.effect, this.damage },
				new Object[] { other.cost, other.name, other.effect, other.damage });
	}

	/**
	 * @return Cost of the attack in the same order as listed on the card
	 */
	public List<Types> getCost() {
		return cost;
	}

	/**
	 * @return Damage the attack deals. May just be a number like '30', but can also
	 *         be a multiplier like 'x20'
	 */
	public String getDamage() {
		return damage;
	}

	/**
	 * @return Effect/Description of the attack, may be null for attacks without
	 *         text
	 */
	public String getEffect() {
		return effect;
	}

	/**
	 * @return Name of the attack
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.cost, this.name, this.effect, this.damage);
	}

}
