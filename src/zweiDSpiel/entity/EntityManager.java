package zweiDSpiel.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import zweiDSpiel.entity.creatures.GhostEnemy;
import zweiDSpiel.entity.creatures.Player;
import zweiDSpiel.entity.creatures.Projectile;
import zweiDSpiel.main.Handler;

// Contains all Entities and stores them in an Array


public class EntityManager {

	private Handler handler;
	private Player player;
	private Projectile projectile;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() <= b.getY() + b.getHeight())
				return -1;
			return 1;
		}
};
	
	public EntityManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
		
		//Enemies
		GhostEnemy ghost = new GhostEnemy(handler, 500, 500);
		GhostEnemy ghost2 = new GhostEnemy(handler, 500, 600);
		addEntity(ghost);
		addEntity(ghost2);
	
	}

//	public void tick() {
//		Iterator<Entity> it = entities.iterator();
//		while (it.hasNext()) {
//			Entity e = it.next();
//			e.tick();
//			if (!e.isActive())
//				it.remove();
//		}
//		entities.sort(renderSorter);
//	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (!e.isActive())
				entities.remove(e);
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
		player.postRender(g);
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}

	
	//Getters and Setters!!!!
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
