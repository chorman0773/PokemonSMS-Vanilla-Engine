/**
 * 
 */
/**
 * @author connor
 *
 */
module github.chorman0773.pokemonsms {
	requires transitive java.desktop;
	requires transitive github.chorman0773.sentry;
	requires transitive luaj;
	requires transitive github.lightningcreations.lclib;
	requires java.base;
	exports github.chorman0773.pokemonsms;
	exports github.chorman0773.pokemonsms.core;
	exports github.chorman0773.pokemonsms.game;
	exports github.chorman0773.pokemonsms.core.pokemon;
	exports github.chorman0773.pokemonsms.core.bindings;
	
}