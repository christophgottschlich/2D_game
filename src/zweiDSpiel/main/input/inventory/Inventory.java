package zweiDSpiel.main.input.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import zweiDSpiel.gfx.Assets;
import zweiDSpiel.gfx.Text;
import zweiDSpiel.items.Item;
import zweiDSpiel.main.Handler;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	
	private int invX = 64, invY = 48,
			invWidth = 512, invHeight = 384,
			invListCenterX = invX + 171,
			invListCenterY = invY + invHeight / 2 + 5,
			invListSpacing = 30;
	
	private int invImageX = 452, invImageY = 82,
			invImageWidth = 64, invImageHeight = 64;
	
	private int invCountX = 484, invCountY = invImageY + invImageHeight + 26;
	
	private int selectedItem = 0;
	
	public Inventory(Handler handler){
		this.handler = handler;
		inventoryItems = new ArrayList<Item>();
		
		addItem(Item.coinItem.createNew(5));
		addItem(Item.arrowItem.createNew(10));
		addItem(Item.bowItem);
		}
	
	public void tick(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))
		{
			active = false;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
		{
			active = !active;
		}
		if(!active)
			return;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))
			selectedItem--;
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
			selectedItem++;
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
		{
			if(inventoryItems.get(selectedItem).getCount() > 0 && inventoryItems.get(selectedItem).getId() == 1 && handler.getWorld().getEntityManager().getPlayer().getHealth() < 7)   // Id 1 ist die Apfel Id
			{
				inventoryItems.get(selectedItem).used();
				inventoryItems.get(selectedItem).setCount(inventoryItems.get(selectedItem).getCount() - 1);
			}
			
		}
		
		if(selectedItem < 0)
			selectedItem = inventoryItems.size() - 1;
		else if(selectedItem >= inventoryItems.size())
			selectedItem = 0;
	}
	
	public void render(Graphics g) {
		if (!active)
			return;
		g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);

		// Text.drawString(g, "> Coin Item <", invListCenterX, invListCenterY, true,
		// Color.white, Assets.font28);
		int len = inventoryItems.size();
		if (len == 0)
			return;

		for (int i = -5; i < 6; i++) {
			if (selectedItem + i < 0 || selectedItem + i >= len)
				continue;
			if (i == 0) {
				Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
						invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
			} else {
				Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
						invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
			}
		}
		Item item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);

	}
	
	
	
	// Inventory methods


	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount() + item.getCount());
				i.setHandler(handler);
				return;
			}
		}
		inventoryItems.add(item);
		item.setHandler(handler);
	}
	
	//FUNKTIONIERT NOCH NICHT RICHTIG
	public void removeItem(Item item)
	{
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				if(i.getCount() > 0)
					i.setCount(i.getCount() - 1);
					return;
			}
		}
		System.out.println("nix da");
	}
	
	public void removeItem(Item item, int amount)
	{
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				if(i.getCount() >= amount)
					i.setCount(i.getCount() - amount);
					return;
			}
		}
		System.out.println("nix da");
	}
	
	
	public int getItemAmount(Item item) {
		
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				return i.getCount();
			}
		}
		return 0;
	
	}
	
	// GETTERS SETTERS
	
	
	public boolean isActive() {
		return active;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
