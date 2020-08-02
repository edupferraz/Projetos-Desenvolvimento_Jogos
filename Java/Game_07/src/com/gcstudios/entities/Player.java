package com.gcstudios.entities;


import java.awt.Graphics;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;


public class Player extends Entity{

	
	public boolean right,left;
	
	public int life = 100;
	
	public int dir = 1;
	private double gravity = 2;
	
	public boolean jump = false;
	
	public boolean isJumping = false;
	public int jumpHeight = 48;
	public int jumpFrames = 0;
	
	private int framesAnimation = 0;
	private int maxFrames = 15;
	
	private int maxSprite = 2;
	private int curSprite = 0;
	
	public BufferedImage Attack_Right;
	public BufferedImage Attack_Left;
	
	public boolean attack = false;
	public boolean isAttacking = false;
	public int attackFrames = 0;
	public int maxFramesAttack = 10;
	
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		Attack_Right = Game.spritesheet.getSprite(32, 32, 16, 16 );
		Attack_Left = Game.spritesheet.getSprite(48, 32, 16, 16 );
	}
	
	public void tick(){
		depth = 2;
		if(World.isFree((int)x,(int)(y+gravity)) && isJumping == false) {
			y+=gravity;
		}
		if(right && World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			dir = 1;
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = -1;
		}
		
		if(jump) {
			if(!World.isFree(this.getX(),this.getY()+1)) {
				isJumping = true;
			}else {
				jump = false;
			}
		}
		
		if(isJumping) {
			if(World.isFree(this.getX(), this.getY()-2)) {
				y-=2;
				jumpFrames+=2;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		
		// Sistema de ataque
		if(attack) {
			
			if(isAttacking == false) {
				attack = false;
				isAttacking = true;
			}
			
		}
		
		if(isAttacking) {
			attackFrames++;
			
			if(attackFrames == maxFramesAttack) {
				attackFrames = 0;
				isAttacking = false;
			}
		}
		
		collisionEnemy();
		
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);
		
		
	}
	
	public void collisionEnemy() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			
			if(e instanceof Enemy) {
				
				if(Entity.rand.nextInt(100) < 50) {
					if(Entity.isColidding(this, e)) {
						life -= 1;
					}
					
					if(isAttacking) {
						((Enemy) e).vida--;
					}
				}
				
			}
		}
	}
	
	public void render(Graphics g){
		framesAnimation++;
		if(framesAnimation == maxFrames) {
			curSprite++;
			framesAnimation = 0;
			if(curSprite == maxSprite) {
				curSprite = 0;
			}
		}
		if(dir == 1) {
			sprite = Entity.PLAYER_SPRITE_RIGHT[curSprite];
			
			if(isAttacking) {
				g.drawImage(Attack_Right, this.getX() - Camera.x + 10, this.getY() - Camera.y, null);
			}
		}else if(dir == -1) {
			sprite = Entity.PLAYER_SPRITE_LEFT[curSprite];
			if(isAttacking) {
				g.drawImage(Attack_Left, this.getX() - Camera.x - 10, this.getY() - Camera.y, null);
			}
		}
		super.render(g);
	}
	

	


}
