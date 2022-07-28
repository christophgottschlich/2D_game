package zweiDSpiel.entity.statics;

import java.awt.Graphics;
import java.util.Random;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;

public class Tree extends StaticEntity {
	
	public Tree(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILEWIDTH,(int) (Tile.TILEHEIGHT * 1.5f));
		health = 5;
		bounds.x = 25;
		bounds.y = (int) (height / 1.5f + 5);
		bounds.width = width - 40;
		bounds.height = (int) (height - height / 1.5f - 10);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		// g.drawRect((int) x + bounds.x, (int) x + bounds.y, bounds.width, bounds.height);
	}
	
	@Override
	public void die()
	{
		Random r = new Random();
		int low = 1;
		int high = 4;
		int result = r.nextInt(high-low) + low;
		
		for(int i = 0; i < result; i++){
			handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int) x + 25 + (20 * i), (int) y + 50 + (20 * i)));
		}
	}

}
