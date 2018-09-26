package github.chorman0773.pokemonsms.siding;

import github.chorman0773.sentry.GameBasic;

public enum EnumSide {
	CLIENT, SERVER, COMMON;
	public static EnumSide current() {
		return GameBasic.getInstance()!=null?CLIENT:SERVER;
	}
}
