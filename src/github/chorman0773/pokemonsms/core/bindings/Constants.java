package github.chorman0773.pokemonsms.core.bindings;

import org.luaj.vm2.LuaUserdata;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import github.chorman0773.pokemonsms.core.EnumAttackCategory;
import github.chorman0773.pokemonsms.core.EnumStat;
import github.chorman0773.pokemonsms.core.EnumStatus;
import github.chorman0773.pokemonsms.core.EnumType;
import github.chorman0773.pokemonsms.lua.EnumValue;

public class Constants extends TwoArgFunction {
	public static class Stats extends LuaUserdata{
		
		public Stats(Object obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}

		@Override
		public LuaValue get(String key) {
			switch(key) {
			case "Attack": return EnumValue.of(EnumStat.ATTACK);
			case "Defense": return EnumValue.of(EnumStat.DEFENSE);
			case "Special": return EnumValue.of(EnumStat.SPECIAL);
			case "SpecDef": return EnumValue.of(EnumStat.SPEC_DEF);
			case "Speed": return EnumValue.of(EnumStat.SPEED);
			case "HP": return EnumValue.of(EnumStat.HP);
			case "Accuracy": return EnumValue.of(EnumStat.ACCURACY);
			case "Evasion": return EnumValue.of(EnumStat.EVASION);
			case "CritRatio": return EnumValue.of(EnumStat.CRITRATIO);
			}
			return NIL;
		}
		
		public String typename() {
			return "Stats";
		}
	}
	public static class Categories extends LuaUserdata{

		public Categories(Object obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		@Override
		public LuaValue get(String key) {
			switch(key) {
			case "Physical": return EnumValue.of(EnumAttackCategory.PHYSICAL);
			case "Special": return EnumValue.of(EnumAttackCategory.SPECIAL);
			case "Status": return EnumValue.of(EnumAttackCategory.STATUS);
			}
			return NIL;
		}
		
		public String typename() {
			return "Categories";
		}
		
	}
	public static class Types extends LuaUserdata{

		public Types(Object obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		@Override
		public LuaValue get(String key) {
			switch(key) {
			case "Normal": return EnumValue.of(EnumType.NORMAL);
			case "Grass": return EnumValue.of(EnumType.GRASS);
			case "Fire": return EnumValue.of(EnumType.FIRE);
			case "Water": return EnumValue.of(EnumType.WATER);
			case "Electric": return EnumValue.of(EnumType.ELECTRIC);
			case "Flying": return EnumValue.of(EnumType.FLYING);
			case "Fighting": return EnumValue.of(EnumType.FIGHTING);
			case "Rock": return EnumValue.of(EnumType.ROCK);
			case "Ground": return EnumValue.of(EnumType.GROUND);
			case "Ice": return EnumValue.of(EnumType.ICE);
			case "Psychic": return EnumValue.of(EnumType.PSYCHIC);
			case "Poison": return EnumValue.of(EnumType.POISON);
			case "Ghost": return EnumValue.of(EnumType.GHOST);
			case "Dark": return EnumValue.of(EnumType.DARK);
			case "Dragon": return EnumValue.of(EnumType.DRAGON);
			case "Steel": return EnumValue.of(EnumType.STEEL);
			case "Fairy": return EnumValue.of(EnumType.FAIRY);
			case "Solar": return EnumValue.of(EnumType.SOLAR);
			case "Lunar": return EnumValue.of(EnumType.LUNAR);
			case "Celestial": return EnumValue.of(EnumType.CELESTIAL);
			case "Typeless": return EnumValue.of(EnumType.TYPELESS);
			case "Chaotic": return EnumValue.of(EnumType.CHAOTIC);
			case "Catastrophe": return EnumValue.of(EnumType.CATASTROPHE);
			}
			return NIL;
		}
		
		public String typename() {
			return "Types";
		}
		
	}
	public static class Status extends LuaUserdata{

		public Status(Object obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		@Override
		public LuaValue get(String key) {
			switch(key) {
			case "Poison": return EnumValue.of(EnumStatus.POISON);
			case "Burn": return EnumValue.of(EnumStatus.BURN);
			case "Paralysis": return EnumValue.of(EnumStatus.PARALYZE);
			case "Paralyze": return EnumValue.of(EnumStatus.PARALYZE);
			case "Sleep": return EnumValue.of(EnumStatus.SLEEP);
			case "Freeze": return EnumValue.of(EnumStatus.FREEZE);
			}
			return NIL;
		}
		
		public String typename() {
			return "Status";
		}
	}
	private Stats stats = new Stats(this);
	private Categories categories = new Categories(this);
	private Types types = new Types(this);
	private Status status = new Status(this);
	public Constants() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public LuaValue call(LuaValue arg1, LuaValue arg2) {
		// TODO Auto-generated method stub
		return this;
	}
	public LuaValue get(String s) {
		switch(s){
			case "Stats":return stats;
			case "Types":return types;
			case "Categories": return categories;
			case "Status":return status;
		}
		return NIL;
	}
	


}
