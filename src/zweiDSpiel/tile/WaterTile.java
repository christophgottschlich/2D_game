package zweiDSpiel.tile;

import zweiDSpiel.gfx.Assets;

public class WaterTile extends Tile {

	public WaterTile(int id)
	{
		super(Assets.water, id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}
