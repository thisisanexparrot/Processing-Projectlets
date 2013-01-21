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

public class testiii_3 extends PApplet {

float updatePercent;
float fillPercent;
boolean mouseClickOn;
String loadingText;
int loadNum;

public void setup() {
  size(300, 300); 
  background(0);
}

public void draw() {
  background(0);
  stroke(255);
  fill(0);
  rect(width/2-100, height/2-10, 200, 20);
  updatePercent(0.005f);
  loadingText = "Loading " + loadNum + "%" ;
}

public void updatePercent(float rate) {
  loadingText = "Loading " + loadNum + "%" ;

  fill(255);
  if(fillPercent >= 1) {
    mouseClickOn = true;
    rect(width/2-100, height/2-10, 200, 20);
    loadNum = 100;
    text(loadingText, width/2-100, height/2-20);
  }
  else {
    float progressWidth = 200 * fillPercent;
    fillPercent += rate; 
    rect(width/2-100, height/2-10, progressWidth, 20);
    loadNum = PApplet.parseInt(100 * fillPercent);
    text(loadingText, width/2-100, height/2-20);

  }
}

public void mouseClicked() {
  if(mouseClickOn) {
    mouseClickOn = false;
    fillPercent = 0; 
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "testiii_3" });
  }
}
