package github.chorman0773.pokemonsms.core.pokemon;

import java.time.Instant;

import github.chorman0773.pokemonsms.core.EnumStat;
import github.chorman0773.pokemonsms.core.EnumStatus;
import github.chorman0773.pokemonsms.core.EventBus;
import github.chorman0773.pokemonsms.core.Registry.Registries;
import github.chorman0773.pokemonsms.lua.Delegable;
import github.chorman0773.pokemonsms.lua.Delegate;
import github.chorman0773.sentry.save.NBTSerializable;
import github.chorman0773.sentry.save.nbt.NBTTagCompound;
import github.chorman0773.sentry.save.nbt.NBTTagList;
import github.chorman0773.sentry.text.TextComponent;

public class Pokemon implements Delegable<Pokemon>, NBTSerializable, EventBus {
	private Species spec;
	private Ability ability;
	private long identity;
	private long otId;
	private long otSid;
	private TextComponent otName;
	private TextComponent name;
	private TextComponent captureLocationName;
	private Instant captureTime;
	private int[] stats;
	private int[] ivs;
	private int[] evs;
	private MoveInfo[] moves;
	private int flags;
	private int[] growthModifiers;
	private int expGrowthModifier;
	private int cryVolumeModifier;
	private int cryPitchModifier;
	private EnumNature nature;
	private int currentHP;
	private EnumStatus status;
	private int level;
	private ItemStack heldItem;
	
	private final PokemonDelegate delegate = new PokemonDelegate(this);
	private static class PokemonDelegate extends Delegate<Pokemon>{

		public PokemonDelegate(Pokemon obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static int FALT = 0x01, FSHADOW = 0x02, FSUPER = 0x80,
			FABILITY_2 = 0x10, FHIDDEN = 0x20, FGENDER = 0x40;
	
	public static enum EnumNature{
		;
		public final EnumStat increased;
		public final EnumStat decreased;
		public final String name;
		EnumNature(EnumStat up,EnumStat down,String name){
			this.increased = up;
			this.decreased = down;
			this.name= name;
		}
		public boolean isNeutral(){
			return increased==decreased;
		}
		public String toString() {
			return name;
		}
		public double modifier(EnumStat stat) {
			if(isNeutral())
				return 1.0;
			else if(increased==stat)
				return 1.1;
			else if(decreased==stat)
				return 0.9;
			return 1.0;
		}
	}
	private static class MoveInfoDelegate extends Delegate<MoveInfo>{

		public MoveInfoDelegate(MoveInfo obj) {
			super(obj);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class MoveInfo implements Delegable<MoveInfo>,NBTSerializable{
		private Move target;
		private int remainingPP;
		private int ppUpsUsed;
		private final MoveInfoDelegate delegate = new MoveInfoDelegate(this);
		public MoveInfo() {
			target = Registries.moves.get("pokemon:null");
			remainingPP = 0;
			ppUpsUsed = 0;
		}
		public MoveInfo(Move m) {
			target = m;
			remainingPP = m.getBasePP();
		}
		@Override
		public void writeNBT(NBTTagCompound comp) {
			comp.setString("id",target.getRegistryName().toString());
			comp.setInt("remainingPP",remainingPP);
			comp.setByte("ppUpsUsed", (byte)ppUpsUsed);
		}

		@Override
		public void readNBT(NBTTagCompound comp) {
			target = Registries.moves.get(comp.getString("id"));
			remainingPP = comp.getInt("remainingPP");
			ppUpsUsed = comp.getInt("ppUpsUsed");
		}
		
		public int applyEther(int points) {
			int maxPP = target.getPP(ppUpsUsed);
			int diff = Math.max(maxPP-remainingPP, points);
			remainingPP += diff;
			return diff;
		}
		public int applyMaxEther() {
			int maxPP = target.getPP(ppUpsUsed);
			int diff = maxPP-remainingPP;
			remainingPP = maxPP;
			return diff;
		}
		public boolean applyPPUp() {
			if(ppUpsUsed>=3)
				return false;
			else {
				int prevMaxPP = target.getPP(ppUpsUsed);
				ppUpsUsed += 1;
				int newMaxPP = target.getPP(ppUpsUsed);
				remainingPP += (newMaxPP-prevMaxPP);
				return true;
			}
		}

		@Override
		public Delegate<MoveInfo> getDelegate() {
			// TODO Auto-generated method stub
			return delegate;
		}
		
	}
	public Pokemon() {
		
	}

	@Override
	public void writeNBT(NBTTagCompound comp) {
		comp.setString("species", spec.getRegistryName().toString());
		comp.setString("ability", ability.getRegistryName().toString());
		comp.setString("status", status.name());
		comp.setInt("Level", level);
		
		NBTTagCompound stats = comp.getTagCompound("Stats");
		stats.setByteArray("Ivs", ivs);
		stats.setByteArray("Evs", evs);
		stats.setInt("CurrentHP", currentHP);
		NBTTagCompound ot = comp.getTagCompound("Ot");
		ot.setLong("OtId", otId);
		ot.setLong("OtSid", otSid);
		ot.setString("OtName", otName.toString());
		ot.setString("Nickname", name.toString());
		ot.setString("CaptureLocationName", captureLocationName.toString());
		ot.setInstant("CaptureTime", captureTime);
		NBTTagCompound indiv = comp.getTagCompound("Indivuality");
		indiv.setByte("Flags", (byte)flags);
		indiv.setLong("Pid", identity);
		indiv.setByte("CryVolumeModifier", (byte)cryVolumeModifier);
		indiv.setByte("CryPitchModifier", (byte)cryPitchModifier);
		indiv.setByteArray("StatGrowthModifiers", growthModifiers);
		indiv.setByte("ExpGrowthModifier",(byte)this.expGrowthModifier);
		indiv.setString("Nature", nature.toString());
		NBTTagList list = comp.getList("Moves");
		for(int i =0;i<moves.length&&moves[i]!=null;i++)
			list.add(moves[i].serializeNBT());
		comp.setTag("HeldItem",heldItem.serializeNBT());
	}

	@Override
	public void readNBT(NBTTagCompound comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public Delegate<Pokemon> getDelegate() {
		// TODO Auto-generated method stub
		return delegate;
	}
	
	public void recalculateStats() {
		int form = spec.getForm(this);
		int prevHp = stats[5];
		for(int i=0;i<5;i++) {
			EnumStat stat = EnumStat.numericOrder.get(i);
			int base = spec.getBaseStat(form, stat);
			int val = (int)((Math.floor(2*base+ivs[i]+evs[i]/4)*level+5)*nature.modifier(stat));
			stats[i] = val;
		}
		int base = spec.getBaseStat(form, EnumStat.HP);
		stats[5] = ((2*base+ivs[5]+evs[5]/4)*level)+level+10;
		int hpDiff = stats[5]-prevHp;
		currentHP += hpDiff;
	}
	public float getHP() {
		return currentHP/(float)stats[5];
	}
	public int getHPValue() {
		return currentHP;
	}
	public ItemStack getHeldItem() {
		return heldItem;
	}

	@Override
	public void raise(int key, Object... parameters) {
		spec.getEventBus().raise(key,parameters);
		ability.getEventBus().raise(key, parameters);
		heldItem.raise(key, parameters);
	}
}
