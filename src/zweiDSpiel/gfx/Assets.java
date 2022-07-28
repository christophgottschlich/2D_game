package zweiDSpiel.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static Font font28;
	public static Font font18;
	
	public static BufferedImage player, player2, sign, grass, lava, water, dirt, wall, tree, snow, whiteSnow, ice;
	//Items
	public static BufferedImage coin;
	public static BufferedImage apple;
	public static BufferedImage bow, arrow;
	
	//Weapons or Attacks or Projectiles
	public static BufferedImage[] arrowDown; 
	public static BufferedImage[] arrowRight;
	public static BufferedImage[] arrowLeft; 
	public static BufferedImage[] arrowUp;
	
	public static BufferedImage[] blueBallLeft;
	public static BufferedImage[] blueBallRight;
	public static BufferedImage[] blueBallUp;
	public static BufferedImage[] blueBallDown;
	
	
	//Entities
	public static BufferedImage fenceUpR, fenceUpL, fenceUpM;
	public static BufferedImage fenceDown, fenceDownR, fenceDownL;
	public static BufferedImage chestOpen, chestClose;
	public static BufferedImage teleport;
	
	public static BufferedImage house;
	
	
	//textures
	public static BufferedImage walkCRD, walkCRU, walkCLU, walkCLD, walkR, walkL, walkU, walkD, walk;
	public static BufferedImage green1, green2, green3, green4, greenCRU, greenCRD, greenCLD, greenCLU;
	
	//Inventory
	public static BufferedImage inventory;
	//HealthBar
	
	public static BufferedImage hearthFull;
	public static BufferedImage hearthEmpty;
	public static BufferedImage hearthHalf;
	
	
	//Player alt
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] player_attack;
	
	//Player neu
	public static BufferedImage[] player2_down;
	public static BufferedImage[] player2_up;
	public static BufferedImage[] player2_left;
	public static BufferedImage[] player2_right;
	
	//Enemy Ghost
	public static BufferedImage[] ghost_down;
	public static BufferedImage[] ghost_right;
	public static BufferedImage[] ghost_left;
	public static BufferedImage[] ghost_up;

	public static void init()
	{
		font28 = FontLoader.loadFont("resources/fonts/slkscr.ttf", 28);
		font18 = FontLoader.loadFont("resources/fonts/slkscr.ttf", 18);
		
		//Character SpriteSheet
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/characters.png"));
	
		SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("/textures/textures.png"));
		SpriteSheet arrowPicDown = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons/arrow.png"));
		SpriteSheet arrowPicRight = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons/arrowRight.png"));
		SpriteSheet arrowPicLeft = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons/arrowLeft.png"));
		SpriteSheet arrowPicUp = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons/arrowUp.png"));
		SpriteSheet bowSS = new SpriteSheet(ImageLoader.loadImage("/textures/Weapons/bow.png"));
		SpriteSheet blueBallLeftSS = new SpriteSheet (ImageLoader.loadImage("/textures/ballAttack/blue/blueSpriteLeft.png"));
		SpriteSheet blueBallRightSS = new SpriteSheet (ImageLoader.loadImage("/textures/ballAttack/blue/blueSpriteRight.png"));
		SpriteSheet blueBallDownSS = new SpriteSheet (ImageLoader.loadImage("/textures/ballAttack/blue/blueSpriteDown.png"));
		SpriteSheet blueBallUpSS = new SpriteSheet (ImageLoader.loadImage("/textures/ballAttack/blue/blueSpriteUp.png"));
	
		
		
		//Spritesheet Entities
		SpriteSheet chest = new SpriteSheet(ImageLoader.loadImage("/textures/entities/chest_32.png"));
		// pic1 = sheet.crop(0, 0, width, height);
		
		walk = textures.crop(352, 144, 16, 16);
		walkCRD = textures.crop(272, 112, 16, 16);
		walkCLD = textures.crop(320, 112, 16, 16);
		walkCRU = textures.crop(272, 160, 16, 16);
		walkCLU = textures.crop(320, 160, 16, 16);
		walkD = textures.crop(288, 112, 16, 16);
		walkU = textures.crop(304, 160, 16, 16);
		walkR = textures.crop(272, 128, 16, 16);
		walkL = textures.crop(320, 128, 16, 16);
		
		green1 = textures.crop(304, 128, 16, 16);
		green2 = textures.crop(304, 144, 16, 16);
		green3 = textures.crop(288, 128, 16, 16);
		green4 = textures.crop(288, 144, 16, 16);
		greenCRD = textures.crop(336, 112, 16, 16);
		greenCRU = textures.crop(336, 128, 16, 16);
		greenCLU = textures.crop(352, 128, 16, 16);
		greenCLD = textures.crop(352, 112, 16, 16);
		
		
		player2_down = new BufferedImage[3];
		player2_down[0] = sheet.crop(0, 0, 16, 16);
		player2_down[1] = sheet.crop(16, 0, 16, 16);
		player2_down[2] = sheet.crop(32, 0, 16, 16);
		
		player2_up = new BufferedImage[3];
		player2_up[0] = sheet.crop(0, 48, 16, 16);
		player2_up[1] = sheet.crop(16, 48, 16, 16);
		player2_up[2] = sheet.crop(32, 48, 16, 16);
		
		player2_left = new BufferedImage[3];
		player2_left[0] = sheet.crop(0, 16, 16, 16);
		player2_left[1] = sheet.crop(16, 16, 16, 16);
		player2_left[2] = sheet.crop(32, 16, 16, 16);
		
		player2_right = new BufferedImage[3];
		player2_right[0] = sheet.crop(0, 32, 16, 16);
		player2_right[1] = sheet.crop(16, 32, 16, 16);
		player2_right[2] = sheet.crop(32, 32, 16, 16);
		 /// Ende Character 2
		
		//Enemies
		//Ghost
		ghost_down = new BufferedImage[3];
		ghost_down[0] = sheet.crop(96, 64, 16, 16);
		ghost_down[1] = sheet.crop(112, 64, 16, 16);
		ghost_down[2] = sheet.crop(128, 64, 16, 16);
		
		ghost_up = new BufferedImage[3];
		ghost_up[0] = sheet.crop(96, 112, 16, 16);
		ghost_up[1] = sheet.crop(112, 112, 16, 16);
		ghost_up[2] = sheet.crop(128, 112, 16, 16);
		
		ghost_right = new BufferedImage[3];
		ghost_right[0] = sheet.crop(96, 96, 16, 16);
		ghost_right[1] = sheet.crop(112, 96, 16, 16);
		ghost_right[2] = sheet.crop(128, 96, 16, 16);
		
		ghost_left = new BufferedImage[3];
		ghost_left[0] = sheet.crop(96, 80, 16, 16);
		ghost_left[1] = sheet.crop(112, 80, 16, 16);
		ghost_left[2] = sheet.crop(128, 80, 16, 16);
		
		
		//Inventory
		inventory = ImageLoader.loadImage("/textures/inventoryScreen.png");
		
		
		
		
		
		
		player_attack = new BufferedImage[3];
		player_attack[0] = ImageLoader.loadImage("/textures/player1/attack/attack1.png");
		player_attack[1] = ImageLoader.loadImage("/textures/player1/attack/attack2.png");
		player_attack[2] = ImageLoader.loadImage("/textures/player1/attack/attack3.png");
		
		
		btn_start = new BufferedImage[2];
		btn_start[0] = ImageLoader.loadImage("/textures/buttons/startBtn.png");
		btn_start[1] = ImageLoader.loadImage("/textures/buttons/startBtn2.png");
		
		player_down = new BufferedImage[2];
		player_down[0] = ImageLoader.loadImage("/textures/player1/down1.png");
		player_down[1] = ImageLoader.loadImage("/textures/player1/down2.png");
		
		player_up = new BufferedImage[2];
		player_up[0] = ImageLoader.loadImage("/textures/player1/up1.png");
		player_up[1] = ImageLoader.loadImage("/textures/player1/up2.png");
		
		player_left = new BufferedImage[2];
		player_left[0] = ImageLoader.loadImage("/textures/player1/left1.png");
		player_left[1] = ImageLoader.loadImage("/textures/player1/left2.png");
		
		player_right = new BufferedImage[2];
		player_right[0] = ImageLoader.loadImage("/textures/player1/right1.png");
		player_right[1] = ImageLoader.loadImage("/textures/player1/right2.png");
		
		sign = ImageLoader.loadImage("/textures/Base64_79.png");
		player = ImageLoader.loadImage("/textures/player1/down1.png");
		player2 = ImageLoader.loadImage("/textures/player2.png");
		
		
		//Items
		coin = ImageLoader.loadImage("/textures/Items/coin_128.png");
		apple = ImageLoader.loadImage("/textures/Items/apple_64.png");
		bow = bowSS.crop(0, 0, 70, 90);
		arrow = arrowPicRight.crop(0, 0, 32, 32);
		
		//textures
		grass = ImageLoader.loadImage("/textures/grass_128.png");
		lava = ImageLoader.loadImage("/textures/lava_128.png");
		dirt = ImageLoader.loadImage("/textures/dirt_128.png");
		wall = ImageLoader.loadImage("/textures/wall_128.png");
		//new
		ice = ImageLoader.loadImage("/textures/ice_128.png");
		snow = ImageLoader.loadImage("/textures/snow.png");
		whiteSnow = ImageLoader.loadImage("/textures/snow2_128.png");
		water = ImageLoader.loadImage("/textures/bwater_128.png");
		
		//Weapons Projectile Arrow
		arrowDown = new BufferedImage[4];
		arrowDown[0] = arrowPicDown.crop(0, 0, 32, 32);
		arrowDown[1] = arrowPicDown.crop(32, 0, 32, 32);
		arrowDown[2] = arrowPicDown.crop(0, 32, 32, 32);
		arrowDown[3] = arrowPicDown.crop(32, 32, 32, 32);
		
		arrowRight = new BufferedImage[4];
		arrowRight[0] = arrowPicRight.crop(0, 0, 32, 32);
		arrowRight[1] = arrowPicRight.crop(32, 0, 32, 32);
		arrowRight[2] = arrowPicRight.crop(0, 32, 32, 32);
		arrowRight[3] = arrowPicRight.crop(32, 32, 32, 32);
		
		arrowLeft = new BufferedImage[4];
		arrowLeft[0] = arrowPicLeft.crop(0, 0, 32, 32);
		arrowLeft[1] = arrowPicLeft.crop(32, 0, 32, 32);
		arrowLeft[2] = arrowPicLeft.crop(0, 32, 32, 32);
		arrowLeft[3] = arrowPicLeft.crop(32, 32, 32, 32);
		
		arrowUp = new BufferedImage[4];
		arrowUp[0] = arrowPicUp.crop(0, 0, 32, 32);
		arrowUp[1] = arrowPicUp.crop(32, 0, 32, 32);
		arrowUp[2] = arrowPicUp.crop(0, 32, 32, 32);
		arrowUp[3] = arrowPicUp.crop(32, 32, 32, 32);
		
		blueBallRight = new BufferedImage[6];
		blueBallRight[0] = blueBallRightSS.crop(0, 0, 512, 197);
		blueBallRight[1] = blueBallRightSS.crop(512, 0, 512, 197);
		blueBallRight[2] = blueBallRightSS.crop(1024, 0, 512, 197);
		blueBallRight[3] = blueBallRightSS.crop(0, 197, 512, 197);
		blueBallRight[4] = blueBallRightSS.crop(512, 197, 512, 197);
		blueBallRight[5] = blueBallRightSS.crop(1024, 197, 512, 197);
		
		blueBallDown = new BufferedImage[6];
		blueBallDown[0] = blueBallDownSS.crop(0, 0, 197, 512);
		blueBallDown[1] = blueBallDownSS.crop(197, 0, 197, 512);
		blueBallDown[2] = blueBallDownSS.crop(0, 512, 197, 512);
		blueBallDown[3] = blueBallDownSS.crop(197, 512, 197, 512);
		blueBallDown[4] = blueBallDownSS.crop(0, 1024, 197, 512);
		blueBallDown[5] = blueBallDownSS.crop(197, 1024, 197, 512);
		
		blueBallUp = new BufferedImage[6];
		blueBallUp[0] = blueBallUpSS.crop(0, 0, 197, 512);
		blueBallUp[1] = blueBallUpSS.crop(197, 0, 197, 512);
		blueBallUp[2] = blueBallUpSS.crop(0, 512, 197, 512);
		blueBallUp[3] = blueBallUpSS.crop(197, 512, 197, 512);
		blueBallUp[4] = blueBallUpSS.crop(0, 1024, 197, 512);
		blueBallUp[5] = blueBallUpSS.crop(197, 1024, 197, 512);
		
		blueBallLeft = new BufferedImage[6];
		blueBallLeft[0] = blueBallLeftSS.crop(0, 0, 512, 197);
		blueBallLeft[1] = blueBallLeftSS.crop(512, 0, 512, 197);
		blueBallLeft[2] = blueBallLeftSS.crop(1024, 0, 512, 197);
		blueBallLeft[3] = blueBallLeftSS.crop(0, 197, 512, 197);
		blueBallLeft[4] = blueBallLeftSS.crop(512, 197, 512, 197);
		blueBallLeft[5] = blueBallLeftSS.crop(1024, 197, 512, 197);
		
		//HealthBar
		hearthFull = ImageLoader.loadImage("/textures/healthbar/heart_16.png");
		hearthEmpty = ImageLoader.loadImage("/textures/healthbar/heart_empty_16.png");
		hearthHalf = ImageLoader.loadImage("/textures/healthbar/heart_half_16.png");
		
		// Entities
		tree = ImageLoader.loadImage("/textures/tree.png");
		teleport = ImageLoader.loadImage("/textures/entities/teleport.png");
		house = textures.crop(16, 16, 95, 83);

		//Fence Entities
		fenceUpL = textures.crop(210, 112, 16, 16);
		fenceUpM = textures.crop(226, 112, 16, 16);
		fenceUpR = textures.crop(242, 112, 16, 16);
		fenceDown = textures.crop(210, 128, 16, 16);
		fenceDownL = textures.crop(210, 144, 16, 16);
		fenceDownR = textures.crop(242, 144, 16, 16);
		
		chestClose = chest.crop(0, 0, 32, 32);
		chestOpen = chest.crop(32, 0, 32, 32);
		
	}
	
	
	
}
