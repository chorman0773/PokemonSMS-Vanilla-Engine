package github.chorman0773.pokemonsms.lua;

import org.luaj.vm2.LuaUserdata;


public abstract class Delegate<D extends Delegable<D>> extends LuaUserdata {
	private D value;
	public Delegate(D obj) {
		super(obj);
		value = obj;
	}
	public  D checkuserdata() {
		return (D)checkuserdata();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public D checkuserdata(Class cl) {
		return (D)super.checkuserdata(cl);
	}
	
	public String typename() {
		return checkuserdata().getClass().getSimpleName();
	}
	
}
