int x = 100;
int y = 100;
int z = 20;

int h = 20;
int w = 30;
float angle = 0;

void setup() {
  size(600, 600, P3D);
  noStroke();
  smooth();
}


void draw() {
  if (frameCount % 1 == 0) {
    background(255, 255, 255);
    fill(130, 200, 133);
    //    translate(x, y, z);
    //    rotateX(frameCount);
    //    rectMode(CENTER);  
    angle = angle+0.01;
    
    for (int i = 0; i < width+40; i+=35) {
      for (int j = 0; j < height+40; j +=35) {
        spinningRect(i, j, 0, 0, angle);
      }
    }

    //rect(0, 0, w, h);
  }
}

void spinningRect(int xIn, int yIn, int rectPlacementX, int rectPlacementY, float angle) {
  pushMatrix();
  translate(xIn, yIn, z);
  rotateX(angle);
  rectMode(CENTER);
  rect(rectPlacementX, rectPlacementY, 30, 30);
  popMatrix();
}

