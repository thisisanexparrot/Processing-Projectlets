class Sprite {
  PImage topImg;
  float xpos;
  float ypos;
  float scale; //between 0 and 1
  int zPos; 

  Sprite() {
    topImg = loadImage("assets/sprite.png");
    topImg.resize(0, 150);
    animateIn();
  }

  void animateIn() {
    tint(255, 255, 255, 40);
    image(topImg, 30, 30);
  }
}

