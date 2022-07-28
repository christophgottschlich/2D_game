package zweiDSpiel.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;
	public boolean sUp, sDown, sLeft, sRight;
	public boolean shootM, openQ;
	public boolean special1;
	
	public KeyManager()
	{
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick()
	{
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i])
				cantPress[i] = false;
			else if (justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if (!cantPress[i] && keys[i])
				justPressed[i] = true;
		}
		
		if(keyJustPressed(KeyEvent.VK_E))
		{
			//System.out.println("E JUST PRESSED!");
		}
		
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aRight = keys[KeyEvent.VK_RIGHT];
		aLeft = keys[KeyEvent.VK_LEFT];
		
		sUp = keys[KeyEvent.VK_I];
		sDown = keys[KeyEvent.VK_K];
		sRight = keys[KeyEvent.VK_L];
		sLeft = keys[KeyEvent.VK_J];
		
		special1 = keys[KeyEvent.VK_1];
		shootM = keys[KeyEvent.VK_M];
		openQ = keys[KeyEvent.VK_Q];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
}

}
