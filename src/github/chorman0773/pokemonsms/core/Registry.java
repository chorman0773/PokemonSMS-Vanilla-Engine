package github.chorman0773.pokemonsms.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import github.chorman0773.pokemonsms.core.pokemon.Ability;
import github.chorman0773.pokemonsms.core.pokemon.Move;
import github.chorman0773.pokemonsms.core.pokemon.Species;

public class Registry<T extends IRegistryEntry<T>> {
	public static interface Reserved{
		public Set<String> domains = Set.of("pokemon","client","impl","internal","test","mod","server","system");
	}
	public static interface Registries{
		public static Registry<Ability> abilities = new Registry<>();
		public static Registry<Move> moves = new Registry<>();
		public static Registry<Species> species = new Registry<>();
	}
	private Map<ResourceLocation,T> map = new TreeMap<>();
	public Registry() {
		// TODO Auto-generated constructor stub
	}
	private void register(ResourceLocation name,T value) {
		if(map.putIfAbsent(name, value)!=null)
			throw new IllegalArgumentException(name+" is already in the table");
	}
	public void register(T entry) {
		register(entry.getRegistryName(),entry);
	}
	public T get(ResourceLocation name) {
		return map.get(name);
	}
	public ResourceLocation getName(T entry) {
		if(!map.containsValue(entry))
			return null;
		else
			return entry.getRegistryName();
	}
	public T get(String name) {
		return map.get(new ResourceLocation(name));
	}
}
