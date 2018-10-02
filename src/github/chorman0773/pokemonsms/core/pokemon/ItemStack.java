package github.chorman0773.pokemonsms.core.pokemon;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

import github.chorman0773.pokemonsms.core.EventBus;
import github.chorman0773.pokemonsms.core.Registry.Registries;
import github.chorman0773.pokemonsms.lua.Delegable;
import github.chorman0773.pokemonsms.lua.Delegate;
import github.chorman0773.pokemonsms.lua.LuaHelpers;
import github.chorman0773.sentry.save.NBTSerializable;
import github.chorman0773.sentry.save.nbt.NBTConstants;
import github.chorman0773.sentry.save.nbt.NBTPrimitive;
import github.chorman0773.sentry.save.nbt.NBTTagBase;
import github.chorman0773.sentry.save.nbt.NBTTagCompound;

public class ItemStack implements Delegable<ItemStack>, NBTSerializable, EventBus {
	private Item i;
	private int count;
	private int meta;
	private LuaTable extra;
	private final ItemStackDelegate delegate = new ItemStackDelegate(this);
	
	private static class ItemStackDelegate extends Delegate<ItemStack>{

		public ItemStackDelegate(ItemStack obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	private static void serializeTable(LuaTable t,NBTTagCompound comp) {
		for(Varargs v:LuaHelpers.pairs(t)) {
			String name = v.checkjstring(1);
			LuaValue val = v.arg(2);
			if(val.istable())
				serializeTable(val.checktable(),comp.getTagCompound(name));
			else if(val.isboolean())
				comp.setBoolean(name, val.checkboolean());
			else if(val.isint())
				comp.setInt(name, val.checkint());
			else if(val.isnumber())
				comp.setDouble(name,val.checkdouble());
			else if(val.isstring())
				comp.setString(name, val.checkjstring());
		}
	}
	private static void deserializeTable(LuaTable t,NBTTagCompound comp) {
		for(String name:comp.getKeys()) {
			NBTTagBase tag = comp.get(name);
			if(tag.getTagType()==NBTConstants.TAG_INT)
				t.set(name, ((NBTPrimitive)tag).getInt());
			else if(tag.getTagType()==NBTConstants.TAG_BYTE)
				t.set(name, ((NBTPrimitive)tag).getBoolean()?LuaValue.TRUE:LuaValue.FALSE);
			else if(tag instanceof NBTPrimitive)
				t.set(name, ((NBTPrimitive)tag).getDouble());
			else if(tag.getTagType()==NBTConstants.TAG_COMPOUND) {
				LuaTable next = LuaValue.tableOf();
				deserializeTable(next,(NBTTagCompound)tag);
				t.set(name, next);
			}
		}
	}
	public ItemStack() {
		this.i = Registries.items.get("system:item/null");
	}
	public ItemStack(Item i) {
		this.i = i;
	}
	public ItemStack(Item i,int count) {
		this.i = i;
		this.count = count;
	}
	public ItemStack(Item i,int meta,int count) {
		this.i = i;
		this.meta = meta;
		this.count = count;
	}
	public ItemStack(Item i,int meta,int count,LuaTable extra) {
		this.i = i;
		this.meta = meta;
		this.count = count;
		this.extra = extra;
	}
	public LuaTable getExtra() {
		return extra;
	}
	public void setExtra(LuaTable t) {
		extra = t;
	}

	@Override
	public void writeNBT(NBTTagCompound comp) {
		comp.setString("Id", i.getRegistryName().toString());
		comp.setInt("Count", count);
		comp.setInt("Meta", meta);
		if(extra!=null)
			serializeTable(extra, comp.getTagCompound("Extra"));
	}

	@Override
	public void readNBT(NBTTagCompound comp) {
		i = Registries.items.get(comp.getString("Id"));
		count = comp.getInt("Count");
		meta = comp.getInt("Meta");
		if(comp.hasTag("Extra", NBTConstants.TAG_COMPOUND))
			deserializeTable(extra=LuaValue.tableOf(),comp.getTagCompound("Extra"));
	}
	
	public Item getItem() {
		return i;
	}
	public int getCount() {
		return count;
	}
	public int getSubitem() {
		return meta;
	}

	@Override
	public Delegate<ItemStack> getDelegate() {
		// TODO Auto-generated method stub
		return delegate;
	}
	public void setItem(Item item) {
		this.i = item;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setSubitem(int subitem) {
		this.meta = subitem;
	}
	@Override
	public void raise(int key, Object... parameters) {
		i.getEventBus().raise(key, parameters);	
	}
}
