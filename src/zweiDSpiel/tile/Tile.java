package zweiDSpiel.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	//STATIC STUFF HERE
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile lavaTile = new LavaTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile wallTile = new WallTile(4);
	
	//Winter tiles
	public static Tile snowTile = new SnowTile(5);
	public static Tile whiteSnowTile = new WhiteSnowTile(6);
	public static Tile iceTile = new IceTile(7);
	
	//Set Tiles
	public static Tile walkCLD = new WalkCLD(10);
	public static Tile walkCRD = new WalkCRD(11);
	public static Tile walkCLU = new WalkCLU(12);
	public static Tile walkCRU = new WalkCRU(13);
	public static Tile walkD = new WalkD(14);
	public static Tile walkU = new WalkU(15);
	public static Tile walkL = new WalkL(16);
	public static Tile walkR = new WalkR(17);
	public static Tile walk = new Walk(22);
	
	public static Tile green1 = new Green1(18);
	public static Tile green2 = new Green2(19);
	public static Tile green3 = new Green3(20);
	public static Tile green4 = new Green4(21);
	public static Tile greenCRD = new GreenCRD(26);
	public static Tile greenCLD = new GreenCLD(23);
	public static Tile greenCRU = new GreenCRU(24);
	public static Tile greenCLU = new GreenCLU(25);
	
	
	
	
	
	
	
	
	//CLASS
	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public void tick()
	{
		
	}

	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		
	}
	
}

