import processing.core.*; 
import processing.xml.*; 

import processing.pdf.*; 

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

public class targets extends PApplet {



int c1;
int c2;

public void setup() {
  size(500, 500);
  //size(500, 500);
  background(0);
  smooth();
  noStroke();
  c1 = color(255);
  c2 = color(255, 0, 0);
}

public void draw() {
  int x = 0;
  for (int h = 80; h > 0; h -= 10) {
    for (int i = 100; i < 500; i+=100) {
      for (int j = 100; j < 500; j+=100) {
        float hFloat = h+0.0f;
        if ((hFloat/10)%2 == 0) {
          fill(c2);
          x = 1;
        }
        else {
          fill(c1);
          x = 0;
        }

        //        fill(255, 0, 0);
        ellipse(i, j, h, h);
      }
    }
  }

}

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "targets" });
  }
}
