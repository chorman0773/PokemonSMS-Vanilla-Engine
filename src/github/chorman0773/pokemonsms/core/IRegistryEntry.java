package github.chorman0773.pokemonsms.core;

import org.luaj.vm2.LuaValue;

public interface IRegistryEntry<T extends IRegistryEntry<T>> {
	public Class<T> getType();
	public ResourceLocation getRegistryName();
	public abstract class Impl<T extends Impl<T>> implements IRegistryEntry<T>{
		private LuaValue val;
		private ResourceLocation loc;
		protected Impl(LuaValue val) {
			this.val = val;
			this.loc = new ResourceLocation(val.get("location").checkjstring());
		}
		@Override
		public ResourceLocation getRegistryName() {
			return loc;
		}
		
		public LuaValue getValue() {
			return val;
		}	
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if(o==null)
				return false;
			else if(o==this)
				return true;
			else if(!getType().isInstance(o))
				return false;
			else 
				return ((Impl<T>)o).loc.equals(this.loc);//Checked by getType().isInstance()
		}
		public int hashCode() {
			return loc.hashCode();
		}
		protected final Object clone()throws CloneNotSupportedException{
			throw new CloneNotSupportedException("Cloning Registry Entries is invalid");
		}
	}
}
