package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;

import com.gcstudios.world.Camera;
import com.gcstudios.world.FloorTile;
import com.gcstudios.world.Tile;
import com.gcstudios.world.WallTile;
import com.gcstudios.world.World;

public class Inventario {
	
	public int selected = 0;
	public boolean isPressed = false;
	public boolean isPlaceItem = false;
	public int mx, my;
	
	public int inventoryBoxSize = 50;
	
	public String[] items = {"grama", "terra", "neve", "areia", "ar"};
	
	public int initialPosition = ((Game.WIDTH * Game.SCALE) / 2) - ((items.length * inventoryBoxSize) / 2);

	public void tick() {
		
		if(isPressed) {
			
			isPressed = false;
			
			if(mx >= initialPosition && mx < initialPosition + (inventoryBoxSize * items.length)) {
				
				System.out.println("Eai");
				
				if(my >= Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1 - 400 && my < Game.HEIGHT * Game.SCALE-inventoryBoxSize - 1) {
					
					selected = (int)(mx - initialPosition)/inventoryBoxSize;
					
					
				}
			}
		}
		
		if(isPlaceItem) {
			isPlaceItem = false;
			mx = (int)(mx/Game.SCALE + Camera.x);
			my = (int)(my/Game.SCALE + Camera.y);
			
			int tilex = mx/16;
			int tiley = my/16;
			
			if(World.tiles[tilex + tiley * World.WIDTH].solid == false) {

				if(items[selected] == "grama") {
					World.tiles[tilex + tiley * World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_Grama);
				} else if(items[selected] == "terra") {
					World.tiles[tilex + tiley * World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_Terra);
				} else if(items[selected] == "ar") {
					World.tiles[tilex + tiley * World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.TILE_Dia);
				} else if(items[selected] == "areia") {
					World.tiles[tilex + tiley * World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_Areia);
				}else if(items[selected] == "neve") {
					World.tiles[tilex + tiley * World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_Neve);
				}
			}
			
			if(World.isFree(Game.player.getX(), Game.player.getY()) == false) {
				World.tiles[tilex + tiley * World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.TILE_Dia);
			}
		}
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < 5; i++) {	
			

			g.setColor(Color.gray);
			g.fillRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 400 + 1, inventoryBoxSize, inventoryBoxSize);
		
			g.setColor(Color.black);
			g.drawRect(initialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 400 + 1, inventoryBoxSize, inventoryBoxSize);
		
			if(items[i] == "grama") {
				
				g.drawImage(Tile.TILE_Grama, initialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize -400 + 10,32, 32, null);
			
			}else if(items[i] == "terra") {
				
				g.drawImage(Tile.TILE_Terra, initialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize -400 + 10,32, 32, null);
				
			} else if(items[i] == "ar") {
				g.drawImage(Tile.TILE_Dia, initialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize -400 + 10,32, 32, null);
			}else if(items[i] == "areia") {
				g.drawImage(Tile.TILE_Areia, initialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize -400 + 10,32, 32, null);
			}else if(items[i] == "neve") {
				g.drawImage(Tile.TILE_Neve, initialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize -400 + 10,32, 32, null);
			}
			
			if(selected == i) {
				g.setColor(Color.red);
				g.drawRect(initialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize - 400, inventoryBoxSize, inventoryBoxSize);
			}
			
		}
	}
}
