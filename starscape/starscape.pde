Sprite mySprite;

void setup() {
  size(1680, 1050); 
  background(159, 292, 191);
  mySprite = new Sprite();
}

void draw() {
  mySprite.animateIn();
}
