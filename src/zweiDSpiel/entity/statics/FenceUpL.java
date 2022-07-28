package zweiDSpiel.entity.statics;

import java.awt.Graphics;
import java.util.Random;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;

public class FenceUpL extends StaticEntity {
	
	public FenceUpL(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILEWIDTH - 14,(int) (Tile.TILEHEIGHT - 14));
		health = 9999;
		bounds.x = 10;
		bounds.y = (int) (height - 30);
		bounds.width = width - 10;
		bounds.height = (int) height / 2 - 20;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.fenceUpL, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public void die()
	{
		Random r = new Random();
		int low = 1;
		int high = 4;
		int result = r.nextInt(high-low) + low;
		
		for(int i = 0; i < result; i++){
			handler.getWorld().getItemManager().addItem(Item.coinItem.createNew((int) x + 25 + (20 * i), (int) y + 50 + (20 * i)));
		}
	}

}