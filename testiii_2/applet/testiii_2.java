import processing.core.*; 
import processing.xml.*; 

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

public class testiii_2 extends PApplet {

public void setup() {
  background(0);
  size(600, 600);
}

public void draw() {
  background(0);
  drawPoly(9, 50, 50, 120);
}

public void drawPoly(int numSides, int centerX, int centerY, int rad) {
  int angle = 360/numSides;

  int xDistance = PApplet.parseInt(rad * acos(radians(angle)));
  int yDistance = PApplet.parseInt(rad * asin(radians(angle)));

  triangle(centerX+0, centerY+0, centerX+xDistance, centerY+0, centerX+0, centerY+yDistance);


  int[] vertsX = new int[numSides];
  int[] vertsY = new int[numSides];

  for (int i = 0; i < numSides; i+=2) {
    vertsX[i] = 0;
    vertsY[i] = 0;

  }
  stroke(140);
  for (int i = 0; i < numSides-1; i++) {
    line(vertsX[i], vertsY[i], vertsX[i+1], vertsY[i+1]);
  }
  line(vertsX[numSides-1], vertsY[numSides-1], vertsX[0], vertsY[0]);
}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "testiii_2" });
  }
}
