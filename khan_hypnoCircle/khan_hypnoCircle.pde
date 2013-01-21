float angle = 0;
float radius = 15;
int centerX = 200;
int centerY = 200;

void setup() {
  size(400, 400);
  background(26, 0, 41);
  stroke(1, 64, 3);
  fill(230, 255, 130);  
  smooth();
}

void draw() {
  float x = sin(angle) * radius;
  float y = cos(angle) * radius;
  
  println("X: "+x);
  println("Y: "+y);
  
  ellipse(x + centerX, -y + centerY, 40, 40);
  
  angle = angle + 0.1;
  radius = radius + 0.1;
}
