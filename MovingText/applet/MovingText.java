import processing.core.*; 
import processing.xml.*; 

import geomerative.*; 

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

public class MovingText extends PApplet {

/*
///////////////////////////////////////////////
 --------- GEOMERATIVE EXAMPLES ---------------
 //////////////////////////////////////////////
 Title   :   TypoGeo_Motion_02
 Date    :   31/08/2011 
 Version :   v0.5
 
 Moves our particles along the x axis. 
 
 Code adapted from an original idea by St\u00e9phane Buellet
 http://www.chevalvert.fr/
 
 Licensed under GNU General Public License (GPL) version 3.
 http://www.gnu.org/licenses/gpl.html
 
 A series of tutorials for using the Geomerative Library
 developed by Ricard Marxer. 
 http://www.ricardmarxer.com/geomerative/
 
 More info on these tutorials and workshops at :
 www.freeartbureau.org/blog
 
 */
//////////////////////////////////////////////


RFont myFont;
RGroup myGroup;
RPoint[] myPoints;
String tick = "TICK";
String tock = "TOCK";

int clockCounter = 0;

FontAgent[] myAgents;
int step = 7;

public void setup() {
  size(650, 700);
  background(0);
  smooth();

  RG.init(this);
  myFont = new RFont("Habana.ttf", 143, CENTER);

  resetAgents();


//  RCommand.setSegmentLength(1);
//  RCommand.setSegmentator(RCommand.UNIFORMLENGTH);
//  myGroup = myFont.toGroup(tick);
//  myPoints = myGroup.getPoints();
//
//  myAgents = new FontAgent[myPoints.length];
//  for (int i=0; i<myPoints.length; i++) {
//    myAgents[i] = new FontAgent(new PVector(myPoints[i].x, myPoints[i].y));
//  }
}


public void draw() {
  fill(0, 23);
  rect(0, 0, width, height);
  translate(350, 205);
  //background(0);

  for (int i = 0; i < myPoints.length; i++) {
    myAgents[i].display();
    myAgents[i].motion();
  }
  
  float lifeSpan = myAgents[0].getLifespan();
  if(lifeSpan <= 0.0f) {
    resetAgents();
    myAgents[0].reset(); 
  }
}

public void resetAgents() {
  String curString;
  if(clockCounter % 2 == 0) {
    curString = tick;
  }
  else {
    curString = tock;
  }
  
  clockCounter++;
  
  RCommand.setSegmentLength(1);
  RCommand.setSegmentator(RCommand.UNIFORMLENGTH);
  myGroup = myFont.toGroup(curString);
  myPoints = myGroup.getPoints();

  myAgents = new FontAgent[myPoints.length];

  for (int i=0; i<myPoints.length; i++) {
      myAgents[i] = new FontAgent(new PVector(myPoints[i].x, myPoints[i].y));
  }
}

public void keyPressed() {
 if(key=='s' || key =='S')
   saveFrame("savedImage_###.png"); 
  
  
}
class FontAgent {

  PVector loc;
  //PVector acc;
  float dia = 1.5f;
  float xMove;
  float yMove;
  float theta1;
  float theta2;
  float lifespan = 255;
  float widthSize = 650;
  float heightSize = 700;

  FontAgent(PVector l) {
    loc = l.get();
    // acc = new PVector (0, 0.1);
  }

  public void motion() {
    xMove += random (-6, 6);
    yMove += random(-6, 6);
    theta1 = radians(xMove);
    theta2 = radians(yMove);
    
    float newX = loc.x + cos(theta1);
    float newY = loc.y + sin(theta2);
    

    float rand1 = random(0, 1);
    
    if(rand1 > 0.5f ) {
      loc.x += cos(theta1);
    }
    else {
      loc.x -= cos(theta1); 
    }
    
    if(newY < heightSize) {
       loc.y += sin(theta2) + 03;
    }
    else {
       loc.y = heightSize-10;
    }
    
    lifespan -= 1.79f;
  }  

  public void display() {
    noStroke();
    fill(lifespan);

    ellipse(loc.x, loc.y, dia, dia);
  }
  
  public float getLifespan () {
    return lifespan; 
  }
  
  public void reset() {
    println("reset");
    lifespan = 255; 
  }
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "MovingText" });
  }
}
