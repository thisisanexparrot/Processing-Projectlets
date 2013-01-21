import processing.serial.*;
import cc.arduino.*;

Arduino arduino;

int potenPin = 0;
int flexPin = 1;
int pressurePin = 2;

int redcolor = 0;
int level = 1;

float angle = 0;
int flick = 0;
PImage bg;


void setup() {
  size(760, 1000);
  smooth();
  noStroke();
  ellipseMode(CENTER);

  arduino = new Arduino(this, Arduino.list()[0], 57600);

  arduino.pinMode(potenPin, Arduino.INPUT);
  arduino.pinMode(flexPin, Arduino.INPUT); 
  arduino.pinMode(pressurePin, Arduino.INPUT);
}

void draw() {
  if (level == 0) {
    animateIn();
    level = 1;
  }

  if (level == 1) {
    drawLevelOne();
    int analogValue = arduino.analogRead(potenPin);
    //println("Pot: " + analogValue); 
    if (analogValue > 1000) {
      println("GOT IT");
      level = 2;
      angle = 0;
    }
  }

  else if (level == 2) {
    drawLevelTwo();
    int flexValue = arduino.analogRead(flexPin);
    //println("Flex: " + flexValue);
    if (flexValue > 800) {
      println("BINGO");
      level = 3;
    }
  }

  else if (level == 3) { 
    drawLevelThree();
    int pressureValue = arduino.analogRead(pressurePin);
    println("Pressure: " + pressureValue);
    if (pressureValue < 20) {
      println("BINGO");
      level = 4;
    }
  }

  else if (level == 4) {
    drawManualReset();
  }
}

void drawLevelOne() {
  pushMatrix();
  bg = loadImage("pipes1.png");
  image(bg, 0, 0);
  PImage test2 = loadImage("knob.png");
  translate(500, 600);
  rotate(angle);
  angle -= 0.2;
  image(test2, -50, -50);
  popMatrix();
}

void drawLevelTwo() {
  pushMatrix();
  bg = loadImage("pipes2.png");
  image(bg, 0, 0);
  
  PImage bottom = loadImage("lever-bottom.png");
  image(bottom, 255, 615);

  translate(249, 615);
  rotate(angle);
  if(flick < 5) {
    rotate(angle);
    angle += 0.1;
    flick++;
  }
  else {
    angle = 0;
    flick = 0; 
  }
  
  PImage top = loadImage("lever-top.png");
  
 
  image(top, 0, -102);
 
  
  popMatrix();
}

void drawLevelThree() {
  bg = loadImage("pipes3.png");
  image(bg, 0, 0);
}

void drawManualReset() {
  bg = loadImage("end.png");
  image(bg, 0, 0);
}

void animateIn() {
}

void animateToTwo() {
}

void animateToThree() {
}

