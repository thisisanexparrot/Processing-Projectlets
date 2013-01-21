import processing.core.*; 
import processing.xml.*; 

import ddf.minim.*; 

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

public class drawingApp extends PApplet {

ArrayList<Branch> branches;
int c;
boolean isAddingBranches;
int newBranches;
int strokeSize;
int isDrawing;

int mouseXDown;
int mouseYDown;
int mouseXUp;
int mouseYUp;

int red;
int green;
int blue;

boolean mouseClicked;

public void setup() {
  background(red, green, blue);
  setupAudio();
  size(1200, 800);

  c = color(200);
  strokeWeight(20);
  strokeCap(ROUND);
  stroke(c);
  smooth();

  red = 229;
  green = 36;
  blue = 255;
}

public void mousePressed() {
  mouseXDown = mouseX;
  mouseYDown = mouseY;
}

public void mouseDragged() {
  line(mouseX, mouseY, mouseXDown, mouseYDown);
}

public void mouseReleased() {
  mouseXUp = mouseX;
  mouseYUp = mouseY;
  mouseClicked = true;

  branches = new ArrayList<Branch>();

  Branch b1 = new Branch(mouseXUp, mouseYUp, mouseXDown, mouseYDown, 0, -1, 3, 20, 200);
  b1.isOpenEnded = true;
  branches.add(b1);

  isDrawing = 0;
}

public void draw() {
  if (mouseClicked) {
    if (isDrawing == 3) {
      background(red, green, blue);
      if (red > 50) {
        red = red - 2;
        green = green - 1;
        blue = blue - 2;
      }
      else if (red < 190) {
        red = red + 2;
        green = green + 1;
        blue = blue + 2;
      }
      ArrayList<Branch> tempList = new ArrayList<Branch>();

      for (Branch b : branches) {
        if (b.isGrowingCurrently) {
          if (b.isOpenEnded) {
            b.y1 = b.y1 + b.growY;
            b.x1 = b.x1 + b.growX;



            int numberNewBranches = PApplet.parseInt(random(-100, 5));
            if (numberNewBranches > 0) {
              newBranches = numberNewBranches;
              isAddingBranches = true;
              b.isOpenEnded = !b.isOpenEnded;
              println("new branches: "+ numberNewBranches);
            }
            else {
              newBranches = 0;
            }


            for (int br = 0; br < newBranches; br++) {
              int growXNew = PApplet.parseInt(random(-10, 10));
              println("x: " +growXNew);
              int growYNew = PApplet.parseInt(random(-10, -1));
              int newStroke;
              int minus = PApplet.parseInt(random(1, 5));

              if (b.strokeSize - minus > 1) {
                newStroke = b.strokeSize - minus;
              }
              else {
                newStroke = 1;
              }
              Branch b1NewBranch = new Branch(b.x1, b.y1, b.x1, b.y1, growXNew, growYNew, b.stopGrowingProb - 1, newStroke, b.c+5);
              // Branch b2NewBranch = new Branch(b.x2, b.y2, b.x2, b.y2, -growXNew, -growYNew, b.stopGrowingProb - 1, newStroke, b.c+5);

              tempList.add(b1NewBranch);
              // tempList.add(b2NewBranch);
            }
          }


          int isGrowingNext = PApplet.parseInt(random(b.stopGrowingProb, 15));
          if (isGrowingNext < 0) {
            b.isGrowingCurrently = false;
          }
        }
        stroke(b.c);
        strokeWeight(b.strokeSize);
        println("weight: " + b.strokeSize);
        line(b.x1, b.y1, b.x2, b.y2);
      }
      branches.addAll(tempList);
      isDrawing = 0;
    }
    else {
      isDrawing++;
    }
  }
}

class Branch {
  int c;
  int x1;
  int x2;
  int y1; 
  int y2;
  int growX; //+/- 10
  int growY; //-1 through - 10
  boolean isOpenEnded;
  boolean isGrowingCurrently;
  int stopGrowingProb;
  int strokeSize;
 
  Branch(int x1a, int y1a, int x2a, int y2a, int growXa, int growYa, int stopGrowingPropa, int stroke, int c1){
    x1 = x1a;
    x2 = x2a;
    y1 = y1a;
    y2 = y2a;
    growX = growXa;
    growY = growYa;
    isOpenEnded = true;
    isGrowingCurrently = true;
    stopGrowingProb = stopGrowingPropa;
    strokeSize = stroke;
    c = c1;
  }
  
  public void display() {
    fill(c);
    line(x1, y1, x2, y2); 
  }
  
}
// Don't worry about understanding the stuff below!

 
Minim minim;
AudioInput in;
float volume = 0;
float volumeF = 0; 
 
//-------------------------------------
public void setupAudio(){
  minim = new Minim(this);
  in = minim.getLineIn(Minim.MONO, 512);
}
 
//-------------------------------------
public void getVolume(){
  volumeF = in.right.level()*1000;
  volume = 0.8f*volume + 0.2f*volumeF;
}
 
//-------------------------------------
public void stop(){
  in.close();
  minim.stop();
  super.stop();
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "drawingApp" });
  }
}
