package github.chorman0773.pokemonsms.core.bindings;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import github.chorman0773.pokemonsms.siding.EnumSide;

public final class Siding extends TwoArgFunction {
	private IsSide isSideServer = new IsSide(EnumSide.SERVER);
	private IsSide isSideClient = new IsSide(EnumSide.CLIENT);
	public Siding() {
		// TODO Auto-generated constructor stub
	}
	private static final class IsSide extends OneArgFunction{
		private EnumSide side;
		private IsSide(EnumSide side) {
			this.side = side;
		}
		@Override
		public LuaValue call(LuaValue arg) {
			// TODO Auto-generated method stub
			return EnumSide.current()==side?TRUE:FALSE;
		}
	}

	@Override
	public LuaValue call(LuaValue arg1, LuaValue arg2) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public LuaValue get(LuaValue key) {
		if(key.isstring())
			return get(key.tojstring());
		else
			return LuaValue.NIL;
	}

	@Override
	public LuaValue get(String key) {
		if(key.equals("isSideClient"))
			return isSideClient;
		else if(key.equals("isSideServer"))
			return isSideServer;
		else
			return LuaValue.NIL;
	}
	

}
