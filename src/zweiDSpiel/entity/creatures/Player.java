package zweiDSpiel.entity.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import zweiDSpiel.entity.Entity;
import zweiDSpiel.entity.worlds.World;
import zweiDSpiel.gfx.Animation;
import zweiDSpiel.gfx.Assets;
import zweiDSpiel.gfx.Text;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Game;
import zweiDSpiel.main.Handler;
import zweiDSpiel.main.input.healthbar.Healthbar;
import zweiDSpiel.main.input.inventory.Inventory;
import zweiDSpiel.states.EscapeState;
import zweiDSpiel.states.GameState;
import zweiDSpiel.states.MenuState;
import zweiDSpiel.states.State;

public class Player extends Creature {
	
	// Animations
	private Animation animDown;
	private Animation animUp;
	private Animation animRight;
	private Animation animLeft;
	private Animation attackLeft;
	
	//test mit Char 2
	private Animation animDown2;
	private Animation animUp2;
	private Animation animLeft2;
	private Animation animRight2;
	private BufferedImage standing;
	private int shootDirection = 4;
	
	//Attack Variables
	private boolean attackingL;  //---------------------
	private int arrowAmount;
	//Inventory
	private Inventory inventory;
	//Healthbar 
	private Healthbar healthbar;
	
	//After Hurt invincible
	private boolean justHurt;
	private long lastJustHurtTimer, hurtCooldown = 3000, hurtTimer = hurtCooldown;
	

	public EscapeState escapeState;
	public State menuState;
	private boolean escapeStateCount = true;
	
	
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private long lastAttackTimerSpecial, attackCooldownSpecial = 1000, attackTimerSpecial = attackCooldownSpecial;
	private long lastAttackTimerArrow, attackCooldownArrow = 400, attackTimerArrow = attackCooldownArrow;
	
	
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 28;
		bounds.width = 32;
		bounds.height = 34; 
		this.health = 5;
		
		escapeState = new EscapeState(handler);
		menuState = new MenuState(handler);
		//Animations
//		animDown = new Animation(500, Assets.player_down);
//		animUp = new Animation(500, Assets.player_up);
//		animRight = new Animation(500, Assets.player_right);
//		animLeft = new Animation(500, Assets.player_left);
//		attackLeft = new Animation(330, Assets.player_attack); //-----------------------
		
		animDown2 = new Animation(330, Assets.player2_down);
		animUp2 = new Animation(330, Assets.player2_up);
		animLeft2 = new Animation(330, Assets.player2_left);
		animRight2 = new Animation(330, Assets.player2_right);
		standing = Assets.player2_down[1];
		
		
		//Inventory
		inventory = new Inventory(handler);
		arrowAmount = inventory.getItemAmount(Item.arrowItem);
		//Healthbar
		healthbar = new Healthbar(handler);
	}
	

	@Override
	public void tick() 
	{
		//Animations
//		animDown.tick();
//		animUp.tick();
//		animLeft.tick();
//		animRight.tick();
//		attackLeft.tick(); //-----------------------
	
		//Char 2
		animDown2.tick();
		animUp2.tick();
		animRight2.tick();
		animLeft2.tick();
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttacks();
		shoot();
		specialAttack();
		arrowAmount = inventory.getItemAmount(Item.arrowItem);
		
		
		//Inventory
		getEscape();
		inventory.tick();
		
		
		//Healthbar
		healthbar.tick();
		
		//JustHurt
		hurtTimer += System.currentTimeMillis() - lastJustHurtTimer;
		lastJustHurtTimer = System.currentTimeMillis();
		if(hurtTimer > hurtCooldown)
			justHurt = false;
		
		
	}
	
	public void specialAttack()
	{
		attackTimerSpecial += System.currentTimeMillis() - lastAttackTimerSpecial;
		lastAttackTimerSpecial = System.currentTimeMillis();
		if(attackTimerSpecial < attackCooldownSpecial)
			return;
//		if(inventory.getItemAmount(Item.arrowItem) <= 6)
//			return;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1))
		{
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, true, false, false, false));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, true, false, false));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, true, false));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, false, true));
			
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, true, false, true, false));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, true, false, false, true));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, true, true, false));
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, true, false, true));
			//inventory.removeItem(Item.arrowItem, 6);    NUR WENN PFEILE GEBRAUCHT WERDEN SOLLEN
			attackTimerSpecial = 0;
		}
		
			
	}
	
	public void shoot() {
		attackTimerArrow += System.currentTimeMillis() - lastAttackTimerArrow;
		lastAttackTimerArrow = System.currentTimeMillis();
		if(inventory.getItemAmount(Item.bowItem) <= 0)
			return;
		if(arrowAmount <= 0)
			return;
		if(attackTimerArrow < attackCooldownArrow)
			return;
		if (handler.getKeyManager().shootM)
		{
			if(shootDirection == 2)
				handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, true, false, false, false));
			if(shootDirection == 1)
				handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, true, false, false));
			if(shootDirection == 3)
				handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, true, false));
			if(shootDirection == 4)
				handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, false, true));
			
			inventory.removeItem(Item.arrowItem); 
			//State.setState(handler.getGame().gameState2);
		
		}
		
//		if(handler.getKeyManager().aUp) 
//		 {
//			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, true, false));
//		 } 
//		 else if(handler.getKeyManager().aDown) 
//		 {
//			 handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 0, 0, 64, 64, false, false, false, true));
//		 } 
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
		attackTimerArrow = 0;
		
	}
	
	private void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		if(inventory.isActive())
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		 if(handler.getKeyManager().aUp) 
		 {
			 ar.x = cb.x + cb.width / 2 - arSize / 2;
			 ar.y = cb.y - arSize;
		 } 
		 else if(handler.getKeyManager().aDown) 
		 {
			 ar.x = cb.x + cb.width / 2 - arSize / 2;
			 ar.y = cb.y + cb.height;
		 } 
		 else if(handler.getKeyManager().aLeft) 
		 {
			 ar.x = cb.x - arSize;
			 ar.y = cb.y + cb.height / 2 - arSize / 2;
			 attackingL = true;        ////////////--------------------------------
			 
		 } 
		 else if(handler.getKeyManager().aRight) 
		 {
			 ar.x = cb.x + cb.width;
			 ar.y = cb.y + cb.height / 2 - arSize / 2;
		 }
		 else
		 {
			 attackingL = false; /////////------------------------------
			 return;
		 }
		 attackTimer = 0;
		  
		 for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			 if(e.equals(this))
				 continue;
			 if(e.getCollisionBounds(0, 0).intersects(ar)) {
				 e.hurt(1);
				 return;
			 }
		 }
		
		
	}
	
	@Override
	public void die()
	{
		System.out.println("You lose!");
		handler.getWorld().setPause(true); 
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g)
	{
		inventory.render(g);
		healthbar.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0)
			{
			shootDirection = 1;
			standing = Assets.player2_left[1];
			//if(justHurt)
			//return animLeftHurt.getCurrentFrame();
			return animLeft2.getCurrentFrame();
			}
		else if (xMove > 0)
		{
			shootDirection = 2;
			standing = Assets.player2_right[1];
			return animRight2.getCurrentFrame();
		}
		else if (yMove < 0)
		{
			shootDirection = 3;
			standing = Assets.player2_up[1];
			return animUp2.getCurrentFrame();
		}
		else if (yMove > 0)
		{
			shootDirection = 4;
			standing = Assets.player2_down[1];
			return animDown2.getCurrentFrame();
		}
		//else if(attackingL)                            //_____________________________________
		//	return attackLeft.getCurrentFrame();      //--------------------------
		else  
			return standing;
	}
	
	public void getEscape()
	{
		escapeState.setPreviousGameState(State.getState());
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) && !inventory.isActive())
		{
			State.setState(escapeState);	
		}
		
	}


	public Inventory getInventory() {
		return inventory;
	}


	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@Override
	public void hurt(int amt) {
		health -= amt;
		if(health <= 0)
		{
			active = false;
			die();
		}
		justHurt = true;
		hurtTimer = 0;
		
	}


	
}
