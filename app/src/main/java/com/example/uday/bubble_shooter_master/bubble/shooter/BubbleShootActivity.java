package com.example.uday.bubble_shooter_master.bubble.shooter;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;

import com.example.uday.bubble_shooter_master.game.engine.SurfaceViewHandler;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;

import bubble.shoot.R;

public class BubbleShootActivity extends Activity {
    /** Called when the activity is first created. */
    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene scene;
	static Vibrator vibrator ;
	public static InputStream inputStream;
	public static BubbleShootActivity BubbleActivity ;
	public static MediaPlayer hitPlayer;
	public static MediaPlayer fallPlayer;
	public static MediaPlayer winPlayer;
	public static MediaPlayer losePlayer;
	
	static com.example.uday.bubble_shooter_master.bubble.shooter.Generate generator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.game_layout);
      
       
//       Stream of JSON file
       inputStream = getResources().openRawResource(R.raw.file);
       BubbleActivity = this;
       vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
       SurfaceViewHandler handler = (SurfaceViewHandler)findViewById(R.id.surface);
//       System.out.println(handler);
       scene = new com.example.uday.bubble_shooter_master.bubble.shooter.GameScene (handler);
       scene.resources = getResources();
       try {
		generator = new com.example.uday.bubble_shooter_master.bubble.shooter.Generate ();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

       //create Players
       hitPlayer = MediaPlayer.create(this,R.raw.hit);
       losePlayer = MediaPlayer.create(this,R.raw.lose);
       fallPlayer = MediaPlayer.create(this,R.raw.fall);
       winPlayer = MediaPlayer.create(this,R.raw.win);
       
//        scene.initialize();
    }
    @Override
    protected void onPause() {
//    	call Garbage collector
    	System.gc();
    	scene.pause();
    	super.onPause();
    }
    @Override
    protected void onResume() {
//    	System.out.println("resume Scene:" + scene);
    	if(scene != null){
    	    	scene.resume();
    	}
    	super.onResume();
    }
    @Override
    protected void onDestroy() {
    	
    	// release all Players
    	hitPlayer.release();
    	fallPlayer.release();
    	winPlayer.release();
    	losePlayer.release();
    	// TODO Auto-generated method stub
    	System.out.println("destored");
    	if(scene != null){
    		scene.started = false;
    		scene.getSurfaceViewHandler().started = false;
    	}
    	
    	//call Garbage collector
    	System.gc();
    	super.onDestroy();
    }

}