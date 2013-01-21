int heightTranslate;
int addHeight;

void setup() {
  size(600, 601, P3D);
  addHeight = 1;
}

void draw() {

  line(0, 0, 0, width, height, -100);
  line(width, 0, 0, width, height, -100);
  line(0, height, 0, width, height, -100);

  if(heightTranslate >= height || heightTranslate <= 0) {
     addHeight = -addHeight;
  }
  heightTranslate += addHeight;

  noStroke();
  lights();
  translate(width/2, heightTranslate);
  sphere(28);
}
