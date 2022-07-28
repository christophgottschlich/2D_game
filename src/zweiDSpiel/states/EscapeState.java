package zweiDSpiel.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.gfx.Text;
import zweiDSpiel.main.Handler;

public class EscapeState extends State{

	private int selectedItem = 0;
	private State previousState;
	private ControllsState controllsState;
	
	
	public EscapeState(Handler handler) {
		super(handler);
		previousState = handler.getGame().gameState;
		controllsState = new ControllsState(handler, this);
	}

	@Override
	public void tick() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
		{
			State.setState(previousState);
		}
		getSelected();
		getEnter();
		
	}

	@Override
	public void render(Graphics g) {
	
		Text.drawString(g, "Steuerung durch die Navigation mit den Tasten W und S", 500, 750, true, Color.BLACK, Assets.font18);
		
		if (selectedItem == 0) {
			Text.drawString(g, "Zurueck zum Spiel", 500, 300, true, Color.RED, Assets.font28);
			Text.drawString(g, "Steuerung", 500, 350, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Hilfe", 500, 400, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Beenden", 500, 450, true, Color.BLACK, Assets.font28);
		}
		if (selectedItem == 1) {
			Text.drawString(g, "Zurueck zum Spiel", 500, 300, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Steuerung", 500, 350, true, Color.RED, Assets.font28);
			Text.drawString(g, "Hilfe", 500, 400, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Beenden", 500, 450, true, Color.BLACK, Assets.font28);
		}
		if (selectedItem == 2) {
			Text.drawString(g, "Zurueck zum Spiel", 500, 300, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Steuerung", 500, 350, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Hilfe", 500, 400, true, Color.RED, Assets.font28);
			Text.drawString(g, "Beenden", 500, 450, true, Color.BLACK, Assets.font28);
		}
		if (selectedItem == 3) {
			Text.drawString(g, "Zurueck zum Spiel", 500, 300, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Steuerung", 500, 350, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Hilfe", 500, 400, true, Color.BLACK, Assets.font28);
			Text.drawString(g, "Beenden", 500, 450, true, Color.RED, Assets.font28);
		}
		
		
		
	}
	
	public void getSelected()
	{
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;
		
		if(selectedItem < 0)
			selectedItem = 3;
		else if(selectedItem > 3)
			selectedItem = 0;
	}
	
	public void getEnter()
	{
		if(selectedItem == 3 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
			System.exit(0);
		if(selectedItem == 0 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
			State.setState(previousState);
		if(selectedItem == 1 && handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
			State.setState(controllsState);
	}
	
	public void setPreviousGameState(State state)
	{
		previousState = state;
	}

}
