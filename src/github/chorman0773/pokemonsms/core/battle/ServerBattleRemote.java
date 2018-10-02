package github.chorman0773.pokemonsms.core.battle;

import java.util.Random;

public class ServerBattleRemote extends Battle {
	protected final Battle controller;
	protected ServerBattleRemote(Battle controller,Side[] sides,BattleSlot[] slots, Random rand) {
		super(sides,slots, rand);
		this.controller = controller;
	}

}
