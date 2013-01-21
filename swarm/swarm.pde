import processing.serial.*;
import cc.arduino.*;


int NUM_THINGS = 50;

// creating an (empty) global array with a
// capacity of 10 elements of type Ball
ArrayList things = new ArrayList();
//Thing[] things = new Thing[NUM_THINGS];
ArrayList foodList = new ArrayList();
int foodCounter = 0;
int numFood = 0;

Arduino arduino;
int buttonPin = 13;
int sensorPin = 0;
boolean isFood = false;

int minDistance = 50;
int maxDistance = 400;
int maxDistSub = 650;

int prev1 = 350;
int prev2 = 350;
int prev3 = 350;
int prev4 = 350;

float redColor = 0;
boolean foodFocus = false;
Food f;


void setup() 
{
  size(800, 800); 
  smooth();

  arduino = new Arduino(this, Arduino.list()[0], 57600);
 
  arduino.pinMode(buttonPin, Arduino.INPUT);
  arduino.pinMode(sensorPin, Arduino.INPUT); 


  for (int i = 0; i < NUM_THINGS; i++) 
  {
    Thing s = new Thing();
    s.x = random(0, width);
    s.y = random(0, height);
    s.direction = random(0, 360);

    things.add(s);   // populating the array
  }
}

void draw() 
{ 
  int digitalValue = arduino.digitalRead(buttonPin);
  if (digitalValue == Arduino.HIGH) {
    if(minDistance <= 1000) {
      maxDistSub += 5;
    }
    isFood = true;
    spawnFood();
  }



  int analogValue =  (int)arduino.analogRead(sensorPin);
  int averageDist = (analogValue + prev1 + prev2 + prev3 + prev4)/5;

  maxDistance = (int)map(averageDist, 260, 600, 400, 50);//(maxDistSub-analogValue);
  println("Average: "+averageDist);
  redColor = map(analogValue, 290, 550, 0, 255);
  if(maxDistance > 300) {
    maxDistance = 400; 
  }
 
  prev4 = prev3;
  prev3 = prev2;
  prev2 = prev1;
  prev1 = analogValue;
  println("Analog: "+analogValue);


  
  
  if(numFood > 10) {
    ArrayList newList = new ArrayList();
    
    newList.add(foodList.get(10));
    newList.add(foodList.get(9));
    foodList = newList; 
    numFood = 2;
  }
  foodCounter++;
  //bg is not conceptually part of the class
  background( 0, 0, 0);

  for (int i = 0; i < numFood; i++) {
    Food f = (Food) foodList.get(i);
    f.update();
  }

  //call the method update everytime I draw
  for (int i = 0; i < NUM_THINGS; i++)
  {
    stroke(1);
    Thing s = (Thing)things.get(i);
    s.update();
  }
//  if (foodCounter % 90 == 0) {
//    isFood = true;
//    spawnFood();
//  }

  int eatCounter = 0;

  for (int i = 0; i < numFood; i++) {
    if(!foodFocus) {
      foodFocus = true;
      f = (Food) foodList.get(i);
    }
      if (f.eaten() == false) {
        float d = f.diam/2;
        float fx = f.xpos;
        float fy = f.ypos;
        for (int j = 0; j < NUM_THINGS; j++) {
          Thing theThing = (Thing)things.get(j);
          if (theThing.x >= fx) {
            theThing.x-=(d + ((fx+theThing.x)/theThing.x));
          }
          else {
            theThing.x+=(d + ((fx+theThing.x)/theThing.x));
          }
          if (theThing.y >= fy) {
            theThing.y-=(d + ((fy+theThing.y)/theThing.y));
          }
          else {
            theThing.y+=(d + ((fy+theThing.y)/theThing.y));
          }
  
          if (abs(theThing.x - fx) < 15 && abs(theThing.y - fy) < 15) {
            eatCounter++;
          }
        } 
  
        if (eatCounter > 10) {
          f.wasEaten = true;
          isFood = false;
          foodFocus = false;
        }
      }
    }
  
}

void spawnFood() {
  Food f = new Food();
  foodList.add(f);
  numFood++;

}

class Thing
{
  //parameters of my thing
  float myDiameter = 10;
  float x;
  float y;
  float speed = 1;
  float direction = 0;
  boolean runaway = false;
  int runawayCounter = 0;

  Thing()
  {}

  void update()
  {
    float dX = cos(radians(direction))*speed;
    float dY = sin(radians(direction))*speed;

    x = x + dX;
    y = y + dY;

    if (x > width) {
      x = width;
    }

    if (x < 0) {
      x = 0;
    }

    if (y > height) {
      y = height;
    }

    if (y < 0) {
      y = 0;
    }

    noStroke();
    fill(redColor, 50, 50);
    ellipse(x, y, myDiameter, myDiameter);

    runawayCounter++;
    for (int i = 0; i < NUM_THINGS; i++)
    {
      //temporary variable to store the other
      Thing other = (Thing)things.get(i);

      if (runawayCounter > 10) {
        runaway = false; 
        runawayCounter = 0;
      }

      //it's not a good idea to make theinteract with itself
      //this points to the specific instance
      if (this != other)
      {
        //after I determine it's another thing I check the distance
        if (dist(x, y, other.x, other.y)<100)
        {
          //and do something
          stroke(redColor, 100, 100);
          line(x, y, other.x, other.y);
        }
        if (dist(x, y, other.x, other.y) > maxDistance) {
          if (x >= other.x) {
            x--;
          }
          else {
            x++;
          }
          if (y >= other.y) {
            y--;
          }
          else {
            y++;
          }
        }
        if (dist(x, y, other.x, other.y) < minDistance) {
          runaway = true;
          if (x >= other.x) {
            x+=1;
          }
          else {
            x-=1;
          }
          if (y >= other.y) {
            y+=1;
          }
          else {
            y-=1;
          }
        }
      }
    }
  }
}

