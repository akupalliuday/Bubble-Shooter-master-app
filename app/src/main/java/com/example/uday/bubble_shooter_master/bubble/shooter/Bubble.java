package com.example.uday.bubble_shooter_master.bubble.shooter;

import android.graphics.Bitmap;

import com.example.uday.bubble_shooter_master.game.engine.Sprite;
import com.example.uday.bubble_shooter_master.game.engine.SurfaceViewHandler;

import java.util.Random;

public class Bubble {
	Sprite sprite;
	boolean alive;
	
	int row ;
	int column;
	int timer;
	final static Bitmap BITMAP = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleMap;
	int color;
	int velocity;
	double theta;
	double deltaX ;
	double deltaY ;
	boolean checked ;
	static final private float inwordFactor = 0.15f;
	static final int VELOCITY = 10;
	public Bubble(int x,int y ,int color) {
		sprite = new Sprite(BITMAP, com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleHight, com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleWight);
		reIntialize(x, y, color);
	}
	
	public Bubble reIntialize (int x,int y ,int color) {
		sprite.moveTo(x, y);
		column = (int) ( (sprite.x+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize/2) / com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize) ;
		row = (int) ( (sprite.y+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize/2) / com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize);
		alive = true;
		this.color = color;
		sprite.setCurrentVerFrame(color);
		timer = 0;
		checked = false ;
		sprite.setCurrentHorFrame(0);
		return this ;
	}
	
	public void updateFrame()
	{
		timer ++ ;
		if (sprite.currentHorFrame == 3)
			{
                com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.layer.removeSprite(sprite);
                com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.falling.remove(this);
                com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.pool.add(this);
			}
		if (timer%5 == 0)
			sprite.nextHorFrame();
	}
	
	private void calculateTheta(double x,double y)
	{
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		theta =  Math.toDegrees(Math.atan(y/x));
		
		if (theta <0)
			theta += 180 ;
		
		System.out.println("Theta = " + theta);
		deltaX = velocity*Math.cos(Math.toRadians(theta)) ;
		deltaY = velocity*Math.sin(Math.toRadians(theta)) * -1;
	}
	
	public void fire(int x,int y)
	{
		velocity = VELOCITY ;
		calculateTheta(x- com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.originalPoint.x, com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.originalPoint.y-y);
	}
	
	// test collied with 4 points
	private boolean collied () // return true if collision happened
	{
		double row1,row2,column1,column2;
		int rowI1,rowI2,columnI1,columnI2;
		int center ;
		
		row1 =  ((sprite.y+deltaY));
		
		row1+=inwordFactor* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize;
		
		row1= (row1 + (row1/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleShitup)/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize;
		
		row2 =  ((sprite.y+deltaY+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize-(inwordFactor* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize)));
		row2= (row2 + row2/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleShitup)/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize;
		
		rowI1 = (int)row1;
		rowI2 = (int)row2;
			column1 = ((sprite.x+deltaX+inwordFactor* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize)/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize);
			column2 = ((sprite.x+deltaX+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize-inwordFactor* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize)/ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize);
		
		columnI1 = (int) column1;
		columnI2 = (int) column2;
		
		center = (int) ((column1+column2)/2);
		
		if (columnI1 >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length)
			columnI1 = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1;
		if (columnI2 >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length)
			columnI2 = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1;
		
		if (columnI1 < 0)
			columnI1=0;
		if (columnI2 >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length)
			columnI2=com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1;
		
		if (rowI1 >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length)
			rowI1 = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1;
		if (rowI2 >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length)
			rowI2 = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1;
		
		boolean upLeft = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI1][columnI1] != null;
		boolean upRight = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI1][columnI2] != null ;
		boolean downLeft =   com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI2][columnI1] != null ;
		boolean downRight =  com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI2][columnI2] != null;
		if(upRight || upLeft || downRight || downLeft){
			
			System.out.println("rowI1 = " + rowI1);
			System.out.println("rowI2 = " + rowI2);
			System.out.println("columnI1 = " + columnI1);
			System.out.println("columnI2 = " + columnI2);
			System.out.println(upLeft+" "+upRight);
			System.out.println(downLeft+" "+downRight);
			
			if(color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor){
				if(upLeft)
					color =  com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI1][columnI1].color;
				else if(upRight)
					color =  com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI1][columnI2].color;
				else if(downRight)
					color = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI2][columnI2].color;
				else
					color = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[rowI2][columnI1].color;
				
				sprite.setCurrentVerFrame(color);
			}
			
			// row
			if((upRight || upLeft ) && !(downLeft || downRight)){ // up coll
				System.out.println("upColl");
				row = rowI1+1;
			}
			else if( (upRight || upLeft ) && !(downLeft || downRight)){ //down coll
				System.out.println("DownColl");
				row = rowI2-1;
			}
			else if ((upRight&& downRight) || (upLeft && downLeft)){
				System.out.println("sameRow");
				row = (int) ((row1+row2)/2);
			}
			else if ((upLeft && downRight) ||(upRight && downLeft)){
				row = rowI1+1;
			}
			else{
				System.out.println("ROW ERROR SETTING R+1");
				row = rowI1+1;
			}
			
			System.out.println("row is "+row);
			//column
			if( upLeft && downRight ){
				System.out.println("upLeft && downRight");
				if(row%2 == 0){
					System.out.println("even col");
					column = columnI1;
				}
				
				else{
					System.out.println("odd col");
					column = columnI1;
				}
				
			}
			else if (upRight && downLeft){
				System.out.println("upRight && downLeft");
				if(row%2 == 0){
					System.out.println("even col+1");
					column = columnI1+1;
				}
				
				else{
					System.out.println("odd col+1");
					column = columnI1+1;
				}
				
			}
			else if(upLeft || downLeft){
				System.out.println("upLeft || downLeft");
				if(row%2 == 0){
					System.out.println("even col+1");
					column = columnI1 +1;
				}
				
				else{
					System.out.println("odd col");
					column = columnI1;
				}
					
			}
			else if(upRight || downRight){
				System.out.println("upRight || downRight");
				if(row%2 == 0){
					System.out.println("even col+1");
					column = columnI1+1 ;
				}
					
				else{
					column = columnI1;//c
					System.out.println("odd col");
				}
				
			}
			
			if(upLeft && downLeft){
				column = columnI1+1;
			}
			else if(upRight && downRight){
				column = columnI1 ;
			}
		if(columnI1 == columnI2){
			column = columnI1;
		}
			System.out.println("row:"+row+" column:"+column);
			if(row < 0)
				row = 0;
			if(row >= 	com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length)
				row = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1;
			if(column < 0)
				column = 0;
			if(column > com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length)
				column = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1;
			if(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column] != null){
				panicRecover();
			}
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column] = this;
			if (row%2 == 1){
				sprite.animateTo((column)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize + com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize/2, (row)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize  - row* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleShitup ,velocity );
			}
			else{
				sprite.animateTo((column)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize, (row)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize  - row* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleShitup , velocity );
			}
			
			return true;
		}
		
		else if(rowI2 == 0) // if reach to upper wall (wsl ll 7eta elly fo2 :D)
		{
			System.out.println("reach to upper wall (wsl ll 7eta elly fo2 :D)");
			row = (int) ((row1+row2)/(2));
			column = (int) ((column1+column2)/(2));
			
			if(row < 0)
				row = 0;
			if(row >= 	com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length)
				row = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1;
			if(column < 0)
				column = 0;
			if(column >= com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length)
				column = com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1;
			
			System.out.println("Put in " + row + " , : " + column);
			if(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column] != null){
				panicRecover();
			}
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column] = this ;
		
			//animateTo instead of moveTo
			if(color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor){
				int bc = new Random().nextInt(4);
				color = bc;
				sprite.setCurrentVerFrame(bc);
				}
			sprite.animateTo((column)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize, (row)* com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize , velocity/2);
			
			return true;
		}
		else
			return false ;
	}
	
	public boolean updatePos(){
		if(velocity <= 0){
			return false;
		}
		
//		if (sprite.x < 0) ??? bta3t a dy :D
//			return true ;
		
		if (collied())
			return true ;
		
		if (sprite.x+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize/2 <0
			|| (sprite.x + sprite.dispWidth+ com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.bubbleSize/2) >= SurfaceViewHandler.cWidth)
			deltaX*= -1 ;
		
		sprite.moveBy(deltaX,deltaY);
		
		return false ;
	}
	
	public void panicRecover(){
		System.err.println("________PANIC_RECOVERY___________");
		if(column+1 < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length && (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1] == null)){
			column++;
		}
		else if(row-1 > 0 && (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column] != null)){
			row--;
		}
		else if(row+1 < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length &&  (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column] == null) ){
			row++;
		}
		else if(column-1 > 0 &&  (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1] == null)){
			column--;
		}
		else{
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.layer.removeSprite( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column].sprite);
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column]=null;
			System.out.println("_____________RECOVERY_FAILED______________");
			
		}
			
	}
	public void checkFalls ()
	{
		alive = false ;
        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add(this);
		while (!com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.isEmpty())
		{
//			System.out.println("IN");
//			 remove first bubble and get it's Neighbor 
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.BubblesGroup.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.peek());
            com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.poll().getNeighbor();
		}
		
		/*NOW
		 * we have bubble loksha
		 * we need to get Bubbles which Not Connected
		 * get it if there is falling So call in GameScene looper 
		 */
	}
	
	public static void getNotConnected()
	{
		/*
		 *  visit Bubble which in First Row
		 *  That will visit all Bubble connected with them
		 *  So any Bubble to connect to them Will fall :)   
		 */
		
		for (int i=0; i< com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length; i++)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0][i] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0][i].alive
			&& !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0][i].checked)
				{
//					System.out.println("Visit " + i);
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0][i].visit();
				}

		for (int i=0; i< com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length; i++)
		{
			for (int j=0; j< com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length; j++)
				if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[i][j] != null && !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[i][j].checked)
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.extra.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[i][j]);
		}
	
		/*
		 * return checked attribute to false
		 * I must find another way to solve this 
		 */
		
		for (int i=0; i< com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length; i++)
		{
			for (int j=0; j< com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length; j++)
				if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[i][j] != null)
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[i][j].checked = false ;
		}
	}
	
	private void visit ()
	{
//		System.out.println("Visit : row = " + row +" , Column = " + column);
		checked = true ;
		// Up
		if (row > 0)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].alive
			&& !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].checked)
				{
//					System.out.println("Go UP");
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].visit();
				}

		// Down
		if (row < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].alive
			&& !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].checked)
				{
//					System.out.println("Go Down");	
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].visit();
				}
		
		// Left
		if (column > 0)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].alive
			&& !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].checked)
				{
//					System.out.println("Go Left");	
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].visit();
				}
		
		// Right
		if (column < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1)
		{
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].alive
					&& !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].checked)
					{
//					System.out.println("Go Right");	
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].visit();
					}
		}

		// Upper_Left and Down_Left
		if (row %2 ==0 && column >0)
		{
			//Upper_Left
			if(row >0 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1] != null
			&& com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].alive && !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].checked)
				{
//					System.out.println("Go UP_Left");
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].visit();
				}
			
			//Down_Left
			if(row < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1] != null
			&& com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].alive && !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].checked)
				{
//					System.out.println("Go Down_Left");
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].visit();
				}
		}
		
		// Upper_Right and Down_Right
		if (row %2 ==1 && column < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1)
		{
			//Upper_Right
			if(row >0 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1] != null
			&& com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].alive && !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].checked)
				{
//					System.out.println("Go UP_Right");
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].visit();
				}
			
			//Down_Right
			if(row < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1] != null
			&& com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].alive && !com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].checked)
				{
//					System.out.println("Go Down_Right");
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].visit();
				}
		}
	}
	
	private void getNeighbor ()
	{
		//1) in the same row 
		if (column > 0)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].alive
			&&(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
				{
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1].alive = false ;
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column-1]);
//					System.out.println("left , " + row);
				}
		
		if (column < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1)
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].alive
			&&(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
				{
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1].alive = false ;
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row][column+1]);
//					System.out.println("Right , " + row);
				}
		
		//2) prev. row
		if (row > 0)
		{
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].alive
					&&(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
				{
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column]);
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column].alive = false ;
//					System.out.println("Up , " + row);
				}
			
			if (column > 0)
			{
				if (row%2 == 0 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].alive
					&& (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
					{
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1].alive = false ;
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column-1]);
//								System.out.println("upper left , " + row);
					}
			}
			
			if (column < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1)
				if (row%2 == 1 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].alive
				&&(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
					{
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1].alive = false ;
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row-1][column+1]);
//						System.out.println("upper right , " + row);
					}
		}
		
		//3) next row
		if (row < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array.length-1)
		{
			if (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].alive
					&&(com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
				{
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column].alive = false ;
                    com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column]);
//					System.out.println("down , " + row);
				}
			if (column > 0)
				if (row%2 == 0 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].alive
				&& (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
					{
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1].alive = false ;
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column-1]);
//						System.out.println("down left , " + row);
					}
			
			if (column < com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[0].length-1)
				if (row%2 == 1 && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1] != null && com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].alive
				&& (com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].color == color || com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].color == com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.superBubbleColor))
					{
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1].alive = false ;
                        com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.temp.add( com.example.uday.bubble_shooter_master.bubble.shooter.GameScene.array[row+1][column+1]);
//						System.out.println("down right , " + row);
					}
		}
	}
	
}
