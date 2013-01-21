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

public class testiii extends PApplet {

PImage background;
PImage car;
int carX;
int carY;

public void setup() {
  background(0);
  size(540, 540);
  background = loadImage("background.png");
  car = loadImage("car.png");
  carX = -130;
  carY = 100;
}

public void draw() {
  image(background, 0, 0);
  iterateCar();
}

public void iterateCar() {
  image(car, carX, carY);
  carX += 2;
  carY += 1;
  if(carX > 580){
    carX = -130;
    carY = 100; 
  }
}
  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "testiii" });
  }
}
