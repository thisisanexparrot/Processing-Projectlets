int myDiameter = 10;
float posX = 250;
float posY = 250;
 
flock_ball f;

void setup() {
  size(500, 500); 
  smooth();
  noStroke();
  f = new flock_ball();  
}
 
void draw()  { 
  background(0, 0, 0);
  posX++;
  f.refresh();
}
