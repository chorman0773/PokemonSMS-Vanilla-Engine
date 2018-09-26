package github.chorman0773.pokemonsms.game;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import github.chorman0773.sentry.annotation.Game;
import github.chorman0773.sentry.generic.GenericGame;

@Game(gameId = "pokemonsms", gameName = "PokemonSMS", gameVersion = "1.0", serialId = -6093277751799286295L, uuid = "f008f340-bff6-11e8-a355-529269fb1459")
public final class PokemonSMS extends GenericGame {

	private List<Runnable> tickables = new ArrayList<>();
	private List<Consumer<Graphics>> renderables = new ArrayList<>();
	private Object runLock = new Object();
	private Object renderLock = new Object();
	private boolean running;
	private boolean drawing;
	private Options opts;
	private static final long serialVersionUID = -6093277751799286295L;

	public PokemonSMS() {
		super(60, 40);
		// TODO Auto-generated constructor stub
	}
	protected void doInit() throws IOException {
		opts = new Options(this.getDirectory());
	}
	protected void destroyGame() throws IOException{
		opts.close();
	}
	public void registerTickable(Runnable tickable) {
		synchronized(runLock) {
			tickables.add(tickable);
		}
	}
	public void registerRenderable(Consumer<Graphics> renderable) {
		synchronized(renderLock) {
			renderables.add(renderable);
		}
	}

	@Override
	public void tick() {
		synchronized(runLock) {
			
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
