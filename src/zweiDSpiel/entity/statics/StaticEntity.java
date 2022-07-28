package zweiDSpiel.entity.statics;

import zweiDSpiel.entity.Entity;
import zweiDSpiel.main.Handler;

//////// A StaticEntity is an Entity that DOES NOT MOVE, like a Tree or a Rock!
//////// Unlike a Creature entity which does move, like the Player!!

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
	}
	
	
	
	

}
