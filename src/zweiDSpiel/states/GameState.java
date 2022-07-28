package zweiDSpiel.states;

import java.awt.Graphics;


import zweiDSpiel.entity.creatures.Player;
import zweiDSpiel.entity.statics.Tree;
import zweiDSpiel.entity.worlds.World;
import zweiDSpiel.gfx.Assets;
import zweiDSpiel.main.Game;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;

public class GameState extends State{
	
	private World world;
	private World world2;

	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "resources/worlds/world3.txt");
		// ODER!!!! -->      world = new World(handler, "resources/worlds/world2.txt");
		handler.setWorld(world);
		
		
		
	}
	
	@Override
	public void tick() {
		world.tick();
		
		//moves the Camera with every tick, but Player is moving aswell
		//game.getGameCamera().move(1, 1);
	}

	@Override
	public void render(Graphics g) {
//		g.drawImage(Assets.sign, 0, 20, null);
//		g.drawImage(Assets.player, 0, 120, null);
		world.render(g);
//		Tile.tiles[0].render(g, 0, 0);
//		Tile.tiles[0].render(g, 64, 0);
//		Tile.tiles[0].render(g, 128, 0);
//		Tile.tiles[0].render(g, 192, 0);
//		
//		Tile.tiles[2].render(g, 0, 64);
//		Tile.tiles[2].render(g, 0, 128);
//		Tile.tiles[2].render(g, 0, 192);
//		Tile.tiles[2].render(g, 0, 256);
		
	}

}
