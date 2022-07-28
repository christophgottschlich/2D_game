package zweiDSpiel.entity.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.main.Handler;

public class Teleport  extends StaticEntity {
	
	private Rectangle radius;

	public Teleport(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = 9999;
		radius = new Rectangle((int) (x - handler.getGameCamera().getxOffset()) - 20,
				(int) (y - handler.getGameCamera().getyOffset()) - 20, width + 40, height + 40);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.teleport, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	public void teleport(int x, int y)
	{
		handler.getWorld().getEntityManager().getPlayer().setX(x);
		handler.getWorld().getEntityManager().getPlayer().setY(y);
	}
	
	public void realTeleport(int x, int y)
	{
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(radius)) 
		{
			teleport(100, 100);
		}
	}
	
}
