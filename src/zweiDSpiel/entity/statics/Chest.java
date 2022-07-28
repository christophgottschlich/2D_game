package zweiDSpiel.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;
import zweiDSpiel.tile.Tile;

public class Chest extends StaticEntity{
	
	boolean closed;
	Rectangle radius;
	private int dropItemOnce;

	public Chest(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.TILEWIDTH - 14,(int) (Tile.TILEHEIGHT - 14));
		health = 5;
		bounds.x = 10;
		bounds.y = (int) (height - 30);
		bounds.width = width - 10;
		bounds.height = (int) height / 2 - 20;
		closed = true;
		radius = new Rectangle((int) (x - handler.getGameCamera().getxOffset()) - 20,
				(int) (y - handler.getGameCamera().getyOffset()) - 20, width + 40, height + 40);
		dropItemOnce = 0;
	}

	@Override
	public void tick() {
		openChest();
		
	}

	@Override
	public void render(Graphics g) {
		if(closed)
		{
			g.drawImage(Assets.chestClose, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			//g.drawRect((int) (x - handler.getGameCamera().getxOffset()) - 20,(int) (y - handler.getGameCamera().getyOffset()) - 20, width + 40, height + 40);
		}
		else
			g.drawImage(Assets.chestOpen,  (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	public void openChest()
	{
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(radius)) 
		{
			if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q))
			{
				closed = false;
				if(dropItemOnce < 1)
				{
					handler.getWorld().getItemManager().addItem(Item.bowItem.createNew((int) x + 25, (int) y + 50 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.arrowItem.createNew((int) x + 15, (int) y + 75 ));
					handler.getWorld().getItemManager().addItem(Item.coinItem.createNew((int) x, (int) y + 50 ));
					dropItemOnce++;
				}
			}
			
		}
		
	}
	
	
	
}
