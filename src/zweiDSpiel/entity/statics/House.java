package zweiDSpiel.entity.statics;

import java.awt.Graphics;
import java.util.Random;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;

public class House extends StaticEntity {
	
	public House(Handler handler, float x, float y)
	{
		super(handler, x, y, 332,(int) (276));
		health = 9999;
		bounds.x = 10;
		bounds.y = (int) (20);
		bounds.width = width - 12;
		bounds.height = (int) height - 50;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.house, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
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