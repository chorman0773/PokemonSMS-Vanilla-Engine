package github.chorman0773.pokemonsms.lua;


/**
 * Base class of Concrete Non-Lua Types that can be Converted to a LuaValue.
 * @author connor
 * @param <D> Restriction Type To Lock the Type
 */
public interface Delegable<D extends Delegable<D>> {
	public abstract Delegate<D> getDelegate();
}
