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

public class squares extends PApplet {

float k;
int s;


public void setup() {
  background(0);
  size(900, 700);  
  fill(255);
  smooth();
  noStroke();
  k = 1.3f;
  s = 0;
}

public void draw() {


  if(s == 1){
    fill(0);
    rect(0, 0, width, height);

    if (k > TWO_PI)
      k = 0;
    for (int i = 100; i < 800; i+=30) {
      for (int j = 100; j < 600; j+=30) {
        
        fill(255); 
        
        float constant = 200;
        arc(j, i, 20, 20, PI+k+(i/constant)+(j/constant), TWO_PI+k+(i/constant)+(j/constant));
        fill(0);
        arc(j, i, 20, 20, 0+k+(i/constant)+(j/constant), PI+k+(i/constant)+(j/constant));
      }
    }
    s=0;
    k += 0.131f;

  }
  s++;

}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "squares" });
  }
}
