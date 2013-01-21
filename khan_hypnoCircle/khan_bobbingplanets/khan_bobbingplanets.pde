float[] pos;
PImage planet;
int frameCounter = 0;

void setup() {
  //frameRate(0);
  planet = loadImage("plan.jpeg");
  size(400, 400, P3D); 
  pos = new float[10];
  for(int i = 0; i < 10; i++) {
    pos[i] = random(0, 1);
  }
  noStroke();
}

void draw() {
  lights();

  background(0);
  fill(255);
  
  for(int i = 0; i < 4; i++) {
    pushMatrix();
    translate(pos[i] * 300, (pos[i+1] * 300) + (sin(frameCounter * 0.05 + pos[i] * 360)*44));
    //scale(0.1 + (i/10), 0.1+(i/10));
    //image(planet, 0, 0);
    ellipse(30, 30, 30, 30);
    //sphere(30);
    popMatrix();
  }
  frameCounter+=1;
}
