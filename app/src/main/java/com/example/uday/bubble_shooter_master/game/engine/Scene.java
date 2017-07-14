package com.example.uday.bubble_shooter_master.game.engine;

import android.graphics.Canvas;

public class Scene {
	
	private LayerManager layerManager;
	private com.example.uday.bubble_shooter_master.game.engine.SurfaceViewHandler surfaceViewHandler;
	private Looper looper;
	private int width;
	private int heigth;
	public boolean started;
	public Scene(com.example.uday.bubble_shooter_master.game.engine.SurfaceViewHandler surfaceViewHandler, long delay) {
	    this.surfaceViewHandler = surfaceViewHandler;
	    System.out.println(surfaceViewHandler);
	    this.surfaceViewHandler.setScene(this);
	    System.out.println("HRHR#");
		layerManager = new LayerManager(this.surfaceViewHandler);
		looper = new Looper(this, delay);
	}
	public void initialize(){
		
	}
	public com.example.uday.bubble_shooter_master.game.engine.SurfaceViewHandler getSurfaceViewHandler(){
		return surfaceViewHandler;
	}
	public LayerManager getLayerManager() {
		return layerManager;
	}
	public int getWidth() {
		return width;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	public void start(){
		looper.start();
		started = true;

	}
	public void stop(){
		looper.stop();
	}
	
	public void run(){
		layerManager.run();
	}
	public void pause(){
		looper.pause();
	}
	public void resume(){
		if(started)
			looper.resume();
	}
	public void render(Canvas c){
	
		 layerManager.render(c);
			
	}
	public void dispChanged() {
		
		
	}
}
