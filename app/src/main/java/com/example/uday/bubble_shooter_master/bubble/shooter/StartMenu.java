package com.example.uday.bubble_shooter_master.bubble.shooter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bubble.shoot.R;

public class StartMenu  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.start_menu);
		super.onCreate(savedInstanceState);
	}
	
	public void playButton(View v){
		Intent intent = new Intent(this,LevelSelectMenu.class);
		startActivity(intent);
	}
	public void optionButton(View v){
		startActivity(new Intent(this,Options.class));
	}
}
