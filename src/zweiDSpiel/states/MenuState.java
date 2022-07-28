package zweiDSpiel.states;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.main.Game;
import zweiDSpiel.main.Handler;
import zweiDSpiel.main.ui.ClickListener;
import zweiDSpiel.main.ui.UIImageButton;
import zweiDSpiel.main.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	private Button startbtn;

	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImageButton(450, 375, 128, 64, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
	}

}
