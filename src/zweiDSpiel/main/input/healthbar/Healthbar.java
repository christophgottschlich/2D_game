package zweiDSpiel.main.input.healthbar;

import java.awt.Graphics;

import zweiDSpiel.entity.creatures.Player;
import zweiDSpiel.gfx.Assets;
import zweiDSpiel.main.Handler;

public class Healthbar {

	private Handler handler;
	private double health;
	private int count;
	
	public Healthbar(Handler handler)
	{
		this.handler = handler;
		count = 0;
		
	}
	
	public void render(Graphics g)
	{
		count = 0;
		for(int i = 0; i < 7; i ++)
		{
			g.drawImage(Assets.hearthEmpty, 5 + i * 32, 10, 32, 32, null);
		}
		for(int i = 0; i < health; i ++)
		{
			g.drawImage(Assets.hearthFull, 5 + i * 32, 10, 32, 32, null);
//			g.drawImage(Assets.hearthFull, 47, 10, 32, 32, null);
//			g.drawImage(Assets.hearthFull, 84, 10, 32, 32, null);
			count ++;
		}
		
		
		//berechnet die fehlenden Herzen.. ist aufwendiger als einfach die leeren Herzen als unterlage zu drawen und die vollen dann einfach drüber!
		
//		for(int i = 0; i < 5 - count; i++)
//		{
//			g.drawImage(Assets.hearthEmpty, i * 32 + count * 32 + 5, 10, 32, 32, null);
//		}
	}
	
	public void tick()
	{
		health = handler.getWorld().getEntityManager().getPlayer().getHealth();
	}
	
}
