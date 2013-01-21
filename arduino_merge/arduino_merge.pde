//import the libraries for serial communication
//and for arduino/firmata specifically
import processing.serial.*;
import cc.arduino.*;
 
//this object class Arduino
//represents guess what? Your board
Arduino arduino;
 
//like in an arduino sketch it's good to
//use variables for pin numbers 
int buttonPin = 13;
int sensorPin = 0;
 
void setup() {
  size(500, 500);
  smooth();
  noStroke();
  ellipseMode(CENTER);
 
  //the arduino object needs to be created at the beginning
  arduino = new Arduino(this, Arduino.list()[0], 57600);
 
  //once it's created I set the pin modes  
  arduino.pinMode(buttonPin, Arduino.INPUT);
  arduino.pinMode(sensorPin, Arduino.INPUT); 
}
 
//we are in processing so the main function is draw (not loop)
void draw()
{
  //read a digital value from the button pin
  int digitalValue = arduino.digitalRead(buttonPin);
 
  //change the background accordingly
  if (digitalValue == Arduino.HIGH)
    background(255);
  else
    background(0);
 
  //read an analog value from the sensor pin
  int analogValue =  arduino.analogRead(sensorPin);
  println(analogValue); //print it for testing purposes
 
  //draw a red circle ellipse the size of the analog value
  fill(255,0,0);
  ellipse(width/2, height/2, analogValue, analogValue);
}
