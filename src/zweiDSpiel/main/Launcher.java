package zweiDSpiel.main;

import zweiDSpiel.display.Display;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Tile Game", 1000, 800);
		game.start();

	}

}
