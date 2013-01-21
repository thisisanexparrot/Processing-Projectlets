float time = 0.0;
float y = 0;
float x = 200;
float initSpeed = 2;
float speed;


void setup() {
  noStroke();
  smooth();
  size(400, 400);

  speed = initSpeed;
}

void draw() {
  background(255, 186, 231);
  fill(240, 237, 240);
  rect(0, 250, 400, 150);

  y = -0.02*time*time + 3.5*time;

  float grey = y * 0.1 +200;
  float shadowSize = y*0.2 + 50;
  fill(grey, grey, grey);
  ellipse(x, 300, shadowSize, 10);

  float width = (200-y) * 0.1 + 50;
  float height = y * 0.1 + 50;
  float translatedY = 250-y;
  fill(224, 0, 37);
  ellipse(x, translatedY, width, height);

  if (y < 0) {
    time = 0;
    if (x < mouseX) {
      speed = initSpeed;
    } 
    if (x > mouseX) {
      speed = -initSpeed;
    }
  }



  time+=2;
  x+=speed;
}

