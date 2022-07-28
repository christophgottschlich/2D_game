package zweiDSpiel.tile;

import zweiDSpiel.gfx.Assets;

public class Green4 extends Tile{

	public Green4(int id) {
		super(Assets.green4, id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
	


}
