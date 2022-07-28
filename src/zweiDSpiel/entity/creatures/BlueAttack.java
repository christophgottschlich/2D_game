package zweiDSpiel.entity.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import zweiDSpiel.entity.Entity;
import zweiDSpiel.gfx.Animation;
import zweiDSpiel.gfx.Assets;
import zweiDSpiel.main.Handler;

public class BlueAttack extends Creature{
	protected boolean hit;
	protected boolean right, left, up, down;
	protected double rateOfFire, range, damage, projSpeed;
	protected double xMove, yMove;
	protected double xStart, yStart;
	private double xBeginning, yBeginning;
	private Entity entity;
	private Rectangle radius;
	
	
	private Animation anRight;
	private Animation anLeft;
	private Animation anUp;
	private Animation anDown;
	

	public BlueAttack(Handler handler, float x, float y, int width, int height, boolean right, boolean left, boolean up, boolean down, Entity entity) {
		super(handler, x, y, width, height);
		range = 500;
		projSpeed = 5;
		damage = 5;
		
		this.right = right;
		this.up = up;
		this.left = left;
		this.down = down;
		this.entity = entity;
		
		bounds.width = 20;
		bounds.height = 4;
		
		xStart = x;
		yStart = y;
		
		
		//if(right || left)
		//{
			
		//}
		
		
		//Animations
		anRight = new Animation(200, Assets.blueBallRight);
		anDown = new Animation(200, Assets.blueBallDown);
		anUp = new Animation(200, Assets.blueBallUp);
		anLeft = new Animation(200, Assets.blueBallLeft);
	}
	
	private void checkContact()
	{
		if(right || left)
		{
		radius = new Rectangle((int)(x + xBeginning - handler.getGameCamera().getxOffset() + 15),
				(int) (y + yBeginning - handler.getGameCamera().getyOffset() + 22), 33, 18);
		}
		if (up || down)
		{
			radius = new Rectangle((int)(x + xBeginning - handler.getGameCamera().getxOffset() + 22),
					(int) (y + yBeginning - handler.getGameCamera().getyOffset() + 15), 18, 33);
		}
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			 if(e.equals(this))
				 continue;
			 if(e.equals(entity))
				 continue;
			 if(e.equals(handler.getWorld().getEntityManager().getPlayer()))
			 {
				if (e.getCollisionBounds(0, 0).intersects(radius)) {
					e.hurt(1);
					handler.getWorld().getEntityManager().getEntities().remove(this);
					return;
				}
			 }
			 if(e.getCollisionBounds(0, 0).intersects(radius)) {
				 handler.getWorld().getEntityManager().getEntities().remove(this);
				 return;
			 }
		}
	}
	
	private void getInput() {
		if (right) {
			xMove = projSpeed;
		} else if (left) {
			xMove = -projSpeed;
		}
		if (up) {
			yMove = -projSpeed;
		} else if (down) {
			yMove = projSpeed;
		}

		if(x >= xStart + range || x <= xStart - range || y >= yStart + range || y <= yStart - range)
			active = false;
			
	}

	@Override
	public void tick() {
		
		//Animations
		anRight.tick();
		anDown.tick();
		anLeft.tick();
		anUp.tick();
		
		getInput();
		checkContact();
		
		x += xMove;
		y += yMove;
		
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.RED);
//		//g.drawRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 8, 8);
//		g.fillOval((int) (x + handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset()),
//				(int) (y + handler.getWorld().getEntityManager().getPlayer().getY()- handler.getGameCamera().getyOffset()), 10, 10);
		
		if(xBeginning == 0)
		{
			xBeginning = (int) (x + entity.getX());
		}
		if(yBeginning == 0)
			yBeginning = (int) (y + entity.getY());
		g.drawImage(getCurrentAnimationFrame(), (int) (x + xBeginning - handler.getGameCamera().getxOffset()),
			(int) (y + yBeginning - handler.getGameCamera().getyOffset()), width, height, null);
		
		//DRAWS COLLISION BOX----------------------------------------------------
//		g.drawRect((int)(x + xBeginning - handler.getGameCamera().getxOffset() + 23),
//				(int) (y + yBeginning - handler.getGameCamera().getyOffset() + 15), 18, 33);
		
		
/////////// CODE TO GET ALL COLLISION BOUNDS DISPLAYED///////////////////////////////////////////////////////////////////////////////////////		
//		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
//			Rectangle p = e.getCollisionBounds(0, 0);
//			int a = p.x;
//			int b = p.y;
//			int c = (int) p.getHeight();
//			int d = (int) p.getWidth();
//
//			g.drawRect(a, b, d, c);
//		}
		
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (right)
			return anRight.getCurrentFrame();
		else if (left)
			return anLeft.getCurrentFrame();
		if (down)
			return anDown.getCurrentFrame();
		else if (up)
			return anUp.getCurrentFrame();
		
		return anRight.getCurrentFrame();
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
