package zweiDSpiel.tile;

import zweiDSpiel.gfx.Assets;

public class LavaTile extends Tile{
 
	public LavaTile(int id)
	{
		super(Assets.lava, id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
	
}
