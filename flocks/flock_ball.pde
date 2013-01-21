class flock_ball {

  float posX;
  float posY;

  flock_ball() {
  }
 
  void refresh() {
    fill(255);
    ellipse(posX, posY, myDiameter, myDiameter);
  } 
}
