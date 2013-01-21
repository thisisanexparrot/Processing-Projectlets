void setup() {
  size(400, 400);
}

void draw() {
  for (int i = 0; i < 20; i++) {
    for (int j = 0; j < 40; j++) {
      rect(i*10, j*20, 20, 20);
    }
  }

  noStroke();
  rectMode(CENTER);

  fill(192);
  rect(100, 100, 40, 40);

  fill(0, 0, 255, 128);

  pushMatrix();

  translate(100, 100);
  rotate(radians(45));    

  scale(2);
  rect(0, 0, 40, 40);

  popMatrix();
}

