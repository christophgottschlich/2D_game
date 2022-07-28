package zweiDSpiel.entity.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import zweiDSpiel.gfx.Animation;
import zweiDSpiel.gfx.Assets;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;
import zweiDSpiel.states.GameState2;
import zweiDSpiel.states.State;

public class GhostEnemy extends Creature {
	
	//Animation
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	private BufferedImage standing;
	
	private int maxHealth;
	private double currentHealth;
	
	
	//Move Timer
	private long lastMoveTimer, moveCooldown = 500, moveTimer = moveCooldown;
	private int directionCount;
	
	//AttackTimer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	

	public GhostEnemy(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 20;
		bounds.width = 32;
		bounds.height = 34;
		
		animDown = new Animation(300, Assets.ghost_down);
		animRight = new Animation(300, Assets.ghost_right);
		animLeft = new Animation(300, Assets.ghost_left);
		animUp = new Animation(300, Assets.ghost_up);
		
		directionCount = 0;
		health = 5;
		maxHealth = 5;
		
}

	@Override
	public void tick() {
		animDown.tick();
		animRight.tick();
		animUp.tick();
		animLeft.tick();
		run();
		move();
		shoot();
	
		
	}
	
	public void shoot() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown)
			return;

		if (handler.getWorld().getEntityManager().getPlayer().getX() > this.getX() - 70
				&& handler.getWorld().getEntityManager().getPlayer().getX() < this.getX() + 70
				&& handler.getWorld().getEntityManager().getPlayer().getY() > this.getY()
				&& yMove > 0){
			handler.getWorld().getEntityManager().addEntity(new BlueAttack(handler, 0, 0, 48, 48, false, false, false, true, this));
			
		} else if (handler.getWorld().getEntityManager().getPlayer().getY() > this.getY() - 70
				&& handler.getWorld().getEntityManager().getPlayer().getY() < this.getY() + 70
				&& handler.getWorld().getEntityManager().getPlayer().getX() > this.getX()
				&& xMove > 0) {
			handler.getWorld().getEntityManager().addEntity(new BlueAttack(handler, 0, 0, 48, 48, true, false, false, false, this));
			
		} else if (handler.getWorld().getEntityManager().getPlayer().getY() > this.getY() - 70
				&& handler.getWorld().getEntityManager().getPlayer().getY() < this.getY() + 70
				&& handler.getWorld().getEntityManager().getPlayer().getX() < this.getX()
				&& xMove < 0) {
			handler.getWorld().getEntityManager().addEntity(new BlueAttack(handler, 0, 0, 48, 48, false, true, false, false, this));
			
		} else if (handler.getWorld().getEntityManager().getPlayer().getX() > this.getX() - 70
				&& handler.getWorld().getEntityManager().getPlayer().getX() < this.getX() + 70
				&& handler.getWorld().getEntityManager().getPlayer().getY() < this.getY()
				&& yMove < 0) {
			handler.getWorld().getEntityManager().addEntity(new BlueAttack(handler, 0, 0, 48, 48, false, false, true, false, this));
		} 
//		 else if(handler.getKeyManager().aLeft) 
//		 {
//			 handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, true, false, false));
//			 
//		 } 
//		 else if(handler.getKeyManager().aRight) 
//		 {
//			 handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, true, false, false, false));
//		 }
//		 else
//		 {
//			 return;
//		 }
		attackTimer = 0;
		
	}

	
	public void run()
	{
//		while(active)
//		{
		moveTimer += System.currentTimeMillis() - lastMoveTimer;
		lastMoveTimer = System.currentTimeMillis();
		
		if (moveTimer < moveCooldown)
			return;
		xMove = 0;
		yMove = 0;
		
		if (directionCount < 4) {
				xMove = -2;
			
			directionCount++;
			moveTimer = 0;
			return;
		}

		if (directionCount < 8) {
		
				yMove = 2;
			
			directionCount++;
			moveTimer = 0;
			return;
		}
		

		if (directionCount < 12) {
			
				xMove = 2;
			
			directionCount++;
			moveTimer = 0;
			return;
		}

		if (directionCount < 16) {
			
				yMove = -2;
			
			directionCount++;
			moveTimer = 0;
			return;
		}
		directionCount = 0;
		
		// }
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0)
			{
			standing = Assets.ghost_left[0];
			return animLeft.getCurrentFrame();
			}
		else if (xMove > 0)
		{
		
			standing = Assets.ghost_right[0];
			return animRight.getCurrentFrame();
		}
		else if (yMove < 0)
		{
			standing = Assets.ghost_up[0];
			return animUp.getCurrentFrame();
		}
		else if (yMove > 0)
		{
			standing = Assets.ghost_down[0];
			return animDown.getCurrentFrame();
		}
		//else if(attackingL)                            //_____________________________________
		//	return attackLeft.getCurrentFrame();      //--------------------------
		else  
			return standing;
	}
	
	public double getHealthDrawn()
	{
		currentHealth = (double) health / (double) maxHealth * 50;
//		System.out.println(health);
//		System.out.println(maxHealth);
//		System.out.println(currentHealth);
		return currentHealth;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), (int) width, (int) height, null);
		
		if (this.health != maxHealth) {
			g.setColor(Color.BLACK);
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()) + 10,
					(int) (y - handler.getGameCamera().getyOffset()) - 10, 50, 5);
			g.setColor(Color.red);
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()) + 10,
					(int) (y - handler.getGameCamera().getyOffset()) - 10, (int) getHealthDrawn(), 5);
		}
	}

	@Override
	public void die() {
		GameState2 gameState2 = new GameState2(handler);
		State.setState(gameState2);
		
	}
}