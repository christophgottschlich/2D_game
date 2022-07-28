package zweiDSpiel.entity.worlds;

import java.awt.Graphics;

import zweiDSpiel.entity.EntityManager;
import zweiDSpiel.entity.creatures.Player;
import zweiDSpiel.entity.statics.*;
import zweiDSpiel.items.ItemManager;
import zweiDSpiel.main.Game;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;
import zweiDSpiel.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private static int levelCount = 0;
	
	boolean pause;

	//Entities
	private EntityManager entityManager;
	//Items
	private ItemManager itemManager;
	
	public World(Handler handler, String path)
	{
		this.handler = handler;
		pause = false;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		
		loadWorld(path);
		
		if (levelCount == 0) {
			// Entity
			entityManager.getPlayer().setX(spawnX);
			entityManager.getPlayer().setY(spawnY);
			entityManager.addEntity(new Tree(handler, 900, 70));
			entityManager.addEntity(new Tree(handler, 1300, 70));
			entityManager.addEntity(new Tree(handler, 1000, 70));
			entityManager.addEntity(new Tree(handler, 1100, 70));
			entityManager.addEntity(new Tree(handler, 1200, 70));
			entityManager.addEntity(new Tree(handler, 200, 200));

			// fences haben abstand 50 Pixel
			//OBERE REIHE ZAEUNE
			entityManager.addEntity(new FenceUpL(handler, 0, 273));
			entityManager.addEntity(new FenceUpM(handler, 50, 273));
			entityManager.addEntity(new FenceUpM(handler, 100, 273));
			entityManager.addEntity(new FenceUpM(handler, 150, 273));
			entityManager.addEntity(new FenceUpM(handler, 200, 273));
			entityManager.addEntity(new FenceUpM(handler, 250, 273));
			entityManager.addEntity(new FenceUpM(handler, 300, 273));
			entityManager.addEntity(new FenceUpM(handler, 350, 273));
			entityManager.addEntity(new FenceUpM(handler, 400, 273));
			entityManager.addEntity(new FenceUpM(handler, 450, 273));
			entityManager.addEntity(new FenceUpM(handler, 500, 273));
			entityManager.addEntity(new FenceUpM(handler, 550, 273));
			entityManager.addEntity(new FenceUpM(handler, 600, 273));
			entityManager.addEntity(new FenceUpM(handler, 650, 273));
			entityManager.addEntity(new FenceUpM(handler, 700, 273));
			entityManager.addEntity(new FenceUpM(handler, 750, 273));
			entityManager.addEntity(new FenceUpM(handler, 800, 273));
			entityManager.addEntity(new FenceUpM(handler, 850, 273));
			entityManager.addEntity(new FenceUpM(handler, 900, 273));
			
			//KURVE
			entityManager.addEntity(new FenceUpR(handler, 950, 273));
			entityManager.addEntity(new FenceDown(handler, 950, 323));
			entityManager.addEntity(new FenceDown(handler, 950, 373));
			entityManager.addEntity(new FenceDownR(handler, 950, 423));
			
			//FENCE DOWN IST DER VERTIKALE MITTLERE ZAUN
			
			
			//UNTERE REIHE ZAEUNE
			entityManager.addEntity(new FenceUpM(handler, 50, 423));
			entityManager.addEntity(new FenceUpM(handler, 100, 423));
			entityManager.addEntity(new FenceUpM(handler, 150, 423));
			entityManager.addEntity(new FenceUpM(handler, 200, 423));
			entityManager.addEntity(new FenceUpM(handler, 250, 423));
			entityManager.addEntity(new FenceUpM(handler, 300, 423));
			entityManager.addEntity(new FenceUpM(handler, 350, 423));
			entityManager.addEntity(new FenceUpM(handler, 400, 423));
			entityManager.addEntity(new FenceUpM(handler, 450, 423));
			entityManager.addEntity(new FenceUpM(handler, 500, 423));
			entityManager.addEntity(new FenceUpM(handler, 550, 423));
			entityManager.addEntity(new FenceUpM(handler, 600, 423));
			entityManager.addEntity(new FenceUpM(handler, 650, 423));
			entityManager.addEntity(new FenceUpM(handler, 700, 423));
			entityManager.addEntity(new FenceUpM(handler, 750, 423));
			entityManager.addEntity(new FenceUpM(handler, 800, 423));
			entityManager.addEntity(new FenceUpM(handler, 850, 423));
			entityManager.addEntity(new FenceUpM(handler, 900, 423));
			entityManager.addEntity(new FenceDownL(handler, 0, 423));
			
			entityManager.addEntity(new FenceDown(handler, 0, 323));
			entityManager.addEntity(new FenceDown(handler, 0, 373));
			
		
			
			entityManager.addEntity(new House(handler, 1000, -20));

			// chest
			entityManager.addEntity(new Chest(handler, 100, 100));
			entityManager.addEntity(new Chest(handler, 400, 100));

			// teleport
			// entityManager.addEntity(new Teleport(handler, 400, 200, 64, 64));
		}
		
		if (levelCount > 1) {
			System.out.println(levelCount);
			entityManager.addEntity(new Chest(handler, 100,100));
		}
		
		levelCount++;
		
		
		
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() 
	{
		
		itemManager.tick();
		if (pause)          
			return;
		entityManager.tick();
		
	}

	public void render(Graphics g)
	{
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(width, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		
		for (int y = yStart; y < yEnd; y++)
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));

			}
		
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
		
	}

	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
			
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.grassTile;
		return t;
	}

	private void loadWorld(String path) 
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}
	
	public void setPause(boolean a) {
		pause = a;
	}
	
	public boolean getPause() {
		return pause;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	
}
