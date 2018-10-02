package github.chorman0773.pokemonsms.core.battle;

import github.chorman0773.pokemonsms.core.EventBus;
import github.chorman0773.pokemonsms.lua.Delegable;

public abstract class Side implements EventBus, Delegable<Side> {
	private Battle owner;
	
	public Side(Battle owner) {
		
	}

	@Override
	public void raise(int key, Object... parameters) {
		
	}
}
