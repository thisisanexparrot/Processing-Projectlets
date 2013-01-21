/*
///////////////////////////////////////////////
 --------- GEOMERATIVE EXAMPLES ---------------
 //////////////////////////////////////////////
 Title   :   TypoGeo_Motion_02
 Date    :   31/08/2011 
 Version :   v0.5
 
 Moves our particles along the x axis. 
 
 Code adapted from an original idea by Stéphane Buellet
 http://www.chevalvert.fr/
 
 Licensed under GNU General Public License (GPL) version 3.
 http://www.gnu.org/licenses/gpl.html
 
 A series of tutorials for using the Geomerative Library
 developed by Ricard Marxer. 
 http://www.ricardmarxer.com/geomerative/
 
 More info on these tutorials and workshops at :
 www.freeartbureau.org/blog
 
 */
//////////////////////////////////////////////
import geomerative.*;

RFont myFont;
RGroup myGroup;
RPoint[] myPoints;
String tick = "TICK";
String tock = "TOCK";

int clockCounter = 0;

FontAgent[] myAgents;
int step = 7;

void setup() {
  size(650, 700);
  background(0);
  smooth();

  RG.init(this);
  myFont = new RFont("Habana.ttf", 143, CENTER);

  resetAgents();


//  RCommand.setSegmentLength(1);
//  RCommand.setSegmentator(RCommand.UNIFORMLENGTH);
//  myGroup = myFont.toGroup(tick);
//  myPoints = myGroup.getPoints();
//
//  myAgents = new FontAgent[myPoints.length];
//  for (int i=0; i<myPoints.length; i++) {
//    myAgents[i] = new FontAgent(new PVector(myPoints[i].x, myPoints[i].y));
//  }
}


void draw() {
  fill(0, 23);
  rect(0, 0, width, height);
  translate(350, 205);
  //background(0);

  for (int i = 0; i < myPoints.length; i++) {
    myAgents[i].display();
    myAgents[i].motion();
  }
  
  float lifeSpan = myAgents[0].getLifespan();
  if(lifeSpan <= 0.0) {
    resetAgents();
    myAgents[0].reset(); 
  }
}

void resetAgents() {
  String curString;
  if(clockCounter % 2 == 0) {
    curString = tick;
  }
  else {
    curString = tock;
  }
  
  clockCounter++;
  
  RCommand.setSegmentLength(1);
  RCommand.setSegmentator(RCommand.UNIFORMLENGTH);
  myGroup = myFont.toGroup(curString);
  myPoints = myGroup.getPoints();

  myAgents = new FontAgent[myPoints.length];

  for (int i=0; i<myPoints.length; i++) {
      myAgents[i] = new FontAgent(new PVector(myPoints[i].x, myPoints[i].y));
  }
}

void keyPressed() {
 if(key=='s' || key =='S')
   saveFrame("savedImage_###.png"); 
  
  
}
