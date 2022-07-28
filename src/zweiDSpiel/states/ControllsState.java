package zweiDSpiel.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.gfx.Text;
import zweiDSpiel.main.Handler;

public class ControllsState extends State{
	
	private EscapeState escapeState;

	public ControllsState(Handler handler, EscapeState escapeState) {
		super(handler);
		this.escapeState = escapeState;
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
		{
			State.setState(escapeState);
		}
	}

	@Override
	public void render(Graphics g) {
		//Movements
		Text.drawString(g, "Movement", 65, 45, false, Color.RED, Assets.font28);
		
		Text.drawString(g, "W", 50, 65, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Move Upwards", 100, 70, false, Color.BLACK, Assets.font18);
		
		Text.drawString(g, "S", 50, 95, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Move Downwards", 100, 100, false, Color.BLACK, Assets.font18);
		
		Text.drawString(g, "A", 50, 125, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Move Left", 100, 130, false, Color.BLACK, Assets.font18);
		
		Text.drawString(g, "D", 50, 155, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Move Right", 100, 160, false, Color.BLACK, Assets.font18);
		
		//Interactions
		Text.drawString(g, "Interaction", 500, 45, false, Color.RED, Assets.font28);
		
		Text.drawString(g, "Q", 485, 65, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Open Chest", 535, 70, false, Color.BLACK, Assets.font18);
		
		Text.drawString(g, "M", 485, 95, true, Color.BLUE, Assets.font28);
		Text.drawString(g, "Shoot", 535, 100, false, Color.BLACK, Assets.font18);
		
		
	}

}
