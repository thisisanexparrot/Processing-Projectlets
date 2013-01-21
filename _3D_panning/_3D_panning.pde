int x = 100;
int y = 100;
int z = 20;

int h = 20;
int w = 30;

void setup() {
  size(600, 600, P3D);
}


void draw() {
  if (mousePressed) {
    lights();
    directionalLight(0, 255, 0, 0, -1, 0);
  }
  background(0);
  camera(mouseX, mouseY, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
  
  fill(144);
  pushMatrix();
  translate(130, height/2, 0);
  rotateY(1.25);
  rotateX(0.4);
  stroke(230);
  box(100);
  popMatrix();

  pushMatrix();
  translate(500, height*0.35, -200);
  fill(200);
  //stroke(255);
  noStroke();
  sphere(280);
  popMatrix();

  translate((width/2)+20, (height/2)+100, 0);
  rotateX(1.22);
  rotateY(2.12);
  scale(40);
  beginShape();
  vertex(-1, -1, -1);
  vertex( 1, -1, -1);
  vertex( 0, 0, 1);

  vertex( 1, -1, -1);
  vertex( 1, 1, -1);
  vertex( 0, 0, 1);

  vertex( 1, 1, -1);
  vertex(-1, 1, -1);
  vertex( 0, 0, 1);

  vertex(-1, 1, -1);
  vertex(-1, -1, -1);
  vertex( 0, 0, 1);
  endShape();
}

