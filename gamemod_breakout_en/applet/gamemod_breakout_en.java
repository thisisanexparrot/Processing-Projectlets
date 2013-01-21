import processing.core.*; 
import processing.xml.*; 

import java.awt.geom.*; 
import java.awt.geom.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class gamemod_breakout_en extends PApplet {

// Breakout // // 28/02/2007
// Steph Thirion - Game Mod workshop: < http://trsp.net/teaching/gamemod >
// 
// english version :)
// made with Processing 0124 Beta


Rectangle gameFrame;
Brick[] bricks;
Paddle paddle;
Ball[] balls;
//
//
int frameNum = 0;
//
// SCREEN PROPERTIES --
int screenWidth = 400;
int screenHeight = 400;
int backgroundColor = 0xff303030;
boolean backgroundRefreshes = false;
// 
// GAME FRAME PROPERTIES --
int gameFrameWidth = 300;
int gameFrameHeight = 300;
int gameFrameStroke = 0xffFFFFFF;
boolean gameFrameHasStroke = false;
int gameFrameFill = 0xff000000;
int opacityOfRefresh = 255;
boolean gameFrameRefreshes = true;
// 
//
int recX = (screenWidth-gameFrameWidth)/2;
int recY = (screenHeight-gameFrameHeight)/2;
//




// SETUP FUNCTION --
public void setup() {
  size(500,600,P3D);
  background(backgroundColor);
  frameRate(60);
  //
  // create objects
  gameFrame = new Rectangle(gameFrameWidth, gameFrameHeight, gameFrameHasStroke, gameFrameStroke, true, gameFrameFill);
  gameFrame.opacity = opacityOfRefresh;
  createBricks();
  createBalls();
  paddle = new Paddle();
  //
  refreshScreen();
}





// DRAW FUNCTION --
public void draw() {
  refreshScreen();
  //
  //
  //
  saveScreenshots();  
}






public void createBalls(){
  // BALL(S) PROPERTIES --
  int numberOfBalls = 2;
  int yBalls = 150;
  //
  balls = new Ball[numberOfBalls];
  for (int i=0; i<numberOfBalls; i++){
    int x = i*20;
    balls[i] = new Ball(x, yBalls);
  }
}





public void createBricks(){
  // BRICK GROUP PROPERTIES --
  int numberOfBricks = 60;
  int bricksPerRow = 10;
  int brickWidth = gameFrameWidth/bricksPerRow;
  int brickHeight = 10;
  boolean brickHasStroke = false;
  int brickStroke = 0xffffffff;
  boolean brickHasFill = true;
  int brickFill = 0xffff0000;
  int yBricks = 50; 
  int[] rowsColors = {0xffff00ff, 0xffff0000, 0xffff9900, 0xffffff00, 0xff00ff00, 0xff00ffff};
  //
  //
  // CREATE BRICKS --
  bricks = new Brick[numberOfBricks];
  for (int i=0; i<numberOfBricks; i++){
    int rowNum = i/bricksPerRow;
    // coords
    int x = brickWidth*i;
    x -= rowNum*bricksPerRow*brickWidth;
    int y = yBricks+i/bricksPerRow*brickHeight;
    // color
    int num = min(rowNum, rowsColors.length-1);
    int rowColor = rowsColors[num];
    // create brick
    bricks[i] = new Brick(x, y, brickWidth, brickHeight, brickHasStroke, brickStroke, brickHasFill, rowColor);
  }
}







public void refreshScreen() {
  // BACKGROUND
  if(backgroundRefreshes){
    background(backgroundColor);
  }
  // GAME FRAME
  if(gameFrameRefreshes){
    gameFrame.drawYourself();
  }
  // PADDLE
  paddle.refresh();
  //
  // BRICKS
  for (int i=0; i<bricks.length; i++){
    bricks[i].refresh();
  }
  // BALLS
  for (int i=0; i<balls.length; i++){
    balls[i].refresh();
  }
}




// be careful with this function - only change if you know what you're doing
// the hard disk could fill up with images in a few minutes
// 
//
// press the 'G' key to save frames in TGA pictures in 'saved' folder
//
public void saveScreenshots(){
   frameNum++;
   if (keyPressed) {
    if (key == 'g' || key == 'G') {
      if(frameNum%2==0){
        saveFrame("saved/frame-####.tga");
      }
    }
  } 
}
      
   

// BALL



public class Ball {

  Rectangle rectangle;
  // BALL PROPERTIES --
  int[] xCoords;
  int[] yCoords;
  int tailCount;
  
  int width = 5;
  int height = 5;
  boolean hasStroke = false;
  int strokeColor = 0xffFFFFFF;
  boolean hasFill = true;
  int fillColor = 0xffffffff;
  // velocity
  int velX = 3;
  int velY = 3;
  //
  int x;
  int y;
  int ox;
  int oy;
  int xcentre;
  int ycentre;
  //
  
  

  Ball(int X, int Y) {
    x = X;
    y = Y;
    rectangle = new Rectangle(width, height, hasStroke, strokeColor, hasFill, fillColor);
    rectangle.setPosition(x, y);
    
    tailCount = 1;
    xCoords = new int[100];
    yCoords = new int[100];
    xCoords[0] = x;
    yCoords[0] = y;
  }
  
  
  

  public void refresh(){
    updatePosition();
    for(int i = 0; i < tailCount; i++) {
      rectangle.setPosition(xCoords[i], yCoords[i]);
      rectangle.drawYourself();
    }
  }
  
  
  

  public void updatePosition() {
    
    //I'm HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    int[] xCopy = new int[xCoords.length];
    int[] yCopy = new int[yCoords.length];
    arrayCopy(xCoords, xCopy); 
    arrayCopy(yCoords, yCopy);
    
    for(int i = tailCount-1; i > 0; i--) {
      xCoords[i] = xCopy[i-1];
      yCoords[i] = yCopy[i-1];
    }
    
    int prevX = x;
    int prevY = y;
    
    // add velocity to position
    x+=velX;
    y+=velY;
    // collision with limits
    if(x<=0 || x>=gameFrameWidth-width){
      velX = -velX;
      x = constrain(x, 0, gameFrameWidth-width);
    }
    if(y<=0 || y>=gameFrameHeight-height){
      velY = -velY;
      y = constrain(y, 0, gameFrameHeight-height);
    }
    xcentre = x+width/2;
    ycentre = y+height/2;
    // collision with paddle
    int result = checkCollisionWithRectangle(paddle.rectangle);
    // if collides on top, control direction of ball
    if (result == 1){
        if(xcentre < paddle.rectangle.x1+paddle.rectangle.width/2){
          if(velX>0){
            velX = -velX;  
          }
        }else{
          if(velX<0){
            velX = -velX;  
          }
        }
    }
    // collision with bricks
    if (result == 0) {
      for (int i=0; i<bricks.length; i++){
         if(bricks[i].imAlive){
           int res = checkCollisionWithRectangle(bricks[i].rectangle);
           if (res != 0){
             bricks[i].die();
             if(tailCount < 100) {
               xCoords[tailCount] = prevX;
               yCoords[tailCount] = prevY;
               tailCount++; 
             }
             break;
           }
         }
      }
    }
    ox = x;
    oy = y;
    xCoords[0] = x;
    yCoords[0] = y;
  }
  
  
  // FUNCION DETECCION DE COLISION --
  // result: 0: no collision 1: top 2: right 3: bottom 4: left 5: couldn't detect which side
  public int checkCollisionWithRectangle (Rectangle R){
    int result = 0;
    if (R.doesPointTouchMe(xcentre, ycentre)){
      // which side did it collide
      Line2D lineaBola = new Line2D.Float(xcentre,ycentre,ox+width/2,oy+height/2);
      result = R.whatSideDoesLineTouch(lineaBola, velX, velY);
      // top
      if(result==1){
        velY = -velY;
        y = R.y1-height;
        // right
      }else if(result==2){
        velX = -velX;
        x = R.x2;
        // bottom
      }else if(result==3){
        velY = -velY;
        y = R.y2;
        // left
      }else if(result==4){
        velX = -velX;
        x = R.x1-width;
      }else{
        result = 5;
      }
    }
    return result;
  }
  
}

// BRICK

public class Brick {
  
  Rectangle rectangle;
  // BRICK PROPERTIES --
  boolean hasStroke = false;
  int strokeColor = 0xffFFFFFF;
  boolean hasFill = true;
  int fillColor = 0xffffffff;
  //
  int x = gameFrameWidth/2;
  int y = 270;
  //
  boolean respawns = false;
  int timeToRespawn = 60; // time is in frames
  
  //
  int frame;
  boolean imAlive;
  //
  
  
  
  
  Brick(int X, int Y, int W, int H, boolean HASSTROKE, int STROKE, boolean HASFILL, int FILL) {
    rectangle = new Rectangle(W, H, HASSTROKE, STROKE, HASFILL, FILL);
    rectangle.setPosition(X, Y);
    imAlive = true;
  }
  
  
  
  
  public void refresh(){
    if (imAlive){
      rectangle.drawYourself();
    }else{
      if (respawns){
        frame++;
        if(frame>timeToRespawn){
          // rise up from your grave, brick
          imAlive=true;
        }
      }
    }
  }
  
  
  
  public void die() {
    imAlive = false;
    frame = 0;
  }


}

// PADDLE

public class Paddle {
  
  Rectangle rectangle;
  // PADDLE PROPERTIES --
  int width = 60;
  int height = 5;
  boolean hasStroke = false;
  int strokeColor = 0xffFFFFFF;
  boolean hasFill = true;
  int fillColor = 0xffffffff;
  //
  int x = gameFrameWidth/2;
  int y = 270;
  //
  //
  
  
  
  Paddle() {
    rectangle = new Rectangle(width, height, hasStroke, strokeColor, hasFill, fillColor);
    rectangle.setPosition(x, y);
  }
  
  
  
  public void refresh(){
    updatePosition();
    rectangle.setPosition(x, y);
    rectangle.drawYourself();
  }
  
  
 
  public void updatePosition() {
    x = mouseX-recX-width/2;
    x = constrain(x, 0, gameFrameWidth-width);
  }

}







// in this game every visible object is a Rectangle
// (ball, paddle, bricks, even the game frame)
// are represented by a Rectangle:





// CAJA



public class Rectangle {

  //
  int width;
  int height;
  boolean hasStroke = false;
  int strokeColor;
  boolean hasFill = false;
  int fillColor;
  int opacity;
  //
  int x1;
  int y1;
  int x2;
  int y2;
  //
  //
  
  
  //
  Rectangle(int W, int H, boolean HASSTROKE, int STROKE, boolean HASFILL, int FILL) {
    width = W;
    height = H;
    hasStroke = HASSTROKE;
    strokeColor = STROKE;
    hasFill = HASFILL;
    fillColor = FILL;
    //
    opacity = 255;
  }
  
  
  
  //
  public void setPosition(int X, int Y) {
    x1 = X;
    y1 = Y;
    x2 = x1+width;
    y2 = y1+height;
  }
  
 
 
  //
  public void drawYourself(){
    // stroke
    if (hasStroke) {
      stroke(strokeColor);
    } else{
      noStroke();
    }
    // fill
    if (hasFill) {
      fill(fillColor, opacity);
    } else{
      noFill();
    }
    rect(recX+x1, recY+y1, width, height);
  }
  
  
  
  // COLLISION DETECTION FUNCTIONS
  
  public boolean doesPointTouchMe (int PX, int PY){
    boolean result = false;
    if (PX >= x1 && PX <= x2) {
      if (PY >= y1 && PY <= y2) {
        result = true;
      }
    }
    return result; 
  }
    
    
    
  public int whatSideDoesLineTouch (Line2D LINE, int VELX, int VELY){
      Line2D side;
      // top (1) / bottom (3)
      if (VELY>0){
        side = new Line2D.Float(x1,y1,x2,y1);
        if(LINE.intersectsLine(side)){
          return 1;
        }
      } else if (VELY<0){
        side = new Line2D.Float(x1,y2,x2,y2);
        if(LINE.intersectsLine(side)){
          return 3;
        }
      }
      // left (4) / right (2)
      if (VELX>0){
        side = new Line2D.Float(x1,y1,x1,y2);
        if(LINE.intersectsLine(side)){
          return 4;
        }
      } else if (VELX<0){
        side = new Line2D.Float(x2,y1,x2,y2);
        if(LINE.intersectsLine(side)){
          return 2;
        }
      }
      return 0;
  }
  
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "gamemod_breakout_en" });
  }
}
