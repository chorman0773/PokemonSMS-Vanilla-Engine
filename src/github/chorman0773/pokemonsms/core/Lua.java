package github.chorman0773.pokemonsms.core;

import java.util.Map;
import java.util.TreeMap;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;


public class Lua {
	private Map<String,LuaValue> modules = new TreeMap<>();
	private Map<String,LuaValue> clientModules = new TreeMap<>();
	private static final Globals globals= JsePlatform.standardGlobals();
	static {
		
	}
	private static LuaValue require(String name) {
		return globals.get("require").call(name);
	}
	private boolean client;
	
	private Lua(boolean client) {
		modules.put("pokemon", require("Pokemon"));
		modules.put("items", require("Items"));
		modules.put("moves", require("Moves"));
		modules.put("abilities", require("Abilities"));
		this.client = client;
		if(client) {
			clientModules.put("locations", require("Locations"));
			clientModules.put("trainers", require("Trainers"));
			clientModules.put("sprites", require("Sprites"));
			clientModules.put("npcs", require("Npcs"));
		}
	}
	
}
