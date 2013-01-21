int NUM_THINGS = 40;

// creating an (empty) global array with a
// capacity of 10 elements of type Ball
ArrayList things = new ArrayList();
//Thing[] things = new Thing[NUM_THINGS];
ArrayList foodList = new ArrayList();
int foodCounter = 0;
int numFood = 0;

void setup() 
{
  size(800, 800); 
  smooth();

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
  foodCounter++;
  //bg is not conceptually part of the class
  background( 0, 0, 0);

  for (int i = 0; i < numFood; i++) {
    Food f = (Food) foodList.get(i);
    f.update();
    println("update");
  }

  //call the method update everytime I draw
  for (int i = 0; i < NUM_THINGS; i++)
  {
    stroke(1);
    Thing s = (Thing)things.get(i);
    s.update();
  }
  if (foodCounter % 90 == 0) {
    spawnFood();
  }

  int eatCounter = 0;

  for (int i = 0; i < numFood; i++) {
    Food f = (Food) foodList.get(i);
    if (f.eaten() == false) {
      float d = f.diam/2;
      float fx = f.xpos;
      float fy = f.ypos;
      for (int j = 0; j < NUM_THINGS; j++) {
        Thing theThing = (Thing)things.get(j);
        if (theThing.x >= fx) {
          theThing.x-=d;
        }
        else {
          theThing.x+=d;
        }
        if (theThing.y >= fy) {
          theThing.y-=d;
        }
        else {
          theThing.y+=d;
        }

        if (abs(theThing.x - fx) < 15 && abs(theThing.y - fy) < 15) {
          eatCounter++;
          println("YEAH");
        }
      } 

      if (eatCounter > 10) {
        println("YEA");
        f.wasEaten = true;
      }
    }
  }
}

void spawnFood() {
  Food f = new Food();
  foodList.add(f);
  numFood++;

  println("Spawn");
}

class Thing
{
  //parameters of my thing
  float myDiameter = 10;
  float x;
  float y;
  color col = #00FFFF;
  float speed = 1;
  float direction = 0;
  boolean runaway = false;
  int runawayCounter = 0;

  //Constructor function - same name as the class
  Thing()
  {
    /*
  like the setup in the main sketch the constructor function 
     is executed once as soon as the object is created
     */
  }

  //method(s) for my thing
  void update()
  {
    /*
    convert direction into radians
     break it down to the sinus and cosinus
     which are the vertical and horizontal component 
     of the angle then multiply for speed 
     (because in trigonometry you always work with a radius of 1)
     */
    float dX = cos(radians(direction))*speed;
    float dY = sin(radians(direction))*speed;

    //the result are the steps in the vertical and horizontal direction
    x = x + dX;
    y = y + dY;

    //direction++;

    //managing borders
    if (x > width)
    {
      x = 0;
    }

    if (x < 0)
    {
      x = width;
    }

    if (y > height)
    {
      y = 0;
    }

    if (y < 0)
    {
      y = height;
    }

    //draw the thing  
    fill(col);
    noStroke();
    fill(255, 10, 20);

    ellipse(x, y, myDiameter, myDiameter);


    //example of interaction among things//
    //every object scans the other objects using the global array
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
          stroke(255, 100, 100);
          line(x, y, other.x, other.y);
        }
        if (dist(x, y, other.x, other.y) > 400) {
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
        if (dist(x, y, other.x, other.y) < 50) {
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

    ////////////
  }
}

