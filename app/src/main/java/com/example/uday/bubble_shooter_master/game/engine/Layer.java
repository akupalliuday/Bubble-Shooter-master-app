package com.example.uday.bubble_shooter_master.game.engine;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Layer {
    public ArrayList<com.example.uday.bubble_shooter_master.game.engine.Sprite> sprites;
//    Sprite [] sprites;
//    private int count;
   public float x;
   public float y;
    public Layer(){
    	sprites = new ArrayList<com.example.uday.bubble_shooter_master.game.engine.Sprite>();
//    	sprites = new Sprite[200];
    }
    
    
    public synchronized void addSprite(com.example.uday.bubble_shooter_master.game.engine.Sprite newSprite){
    	sprites.add(newSprite);
//    	sprites[count++] = newSprite;
    }
    public synchronized void removeSprite (com.example.uday.bubble_shooter_master.game.engine.Sprite sprite){
    	sprites.remove(sprite);
    }
    public synchronized void render(Canvas canvas){
   
    	canvas.translate(x, y);
    	for (int i=0;i<sprites.size();i++) {
			sprites.get(i).render(canvas);
		}
    	canvas.translate(-x, -y);
    }
}
