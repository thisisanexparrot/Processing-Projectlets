import processing.xml.*;
import processing.opengl.*;
import geomerative.*;


RFont theFont;
String currentText;

RShape shp;
RShape polyshp;

void setup(){
  size(600, 600);
  smooth();
  RG.init(this);
  
  theFont = new RFont("Habana-48.vlw", 100, CENTER);

  fill(149, 229, 100);
  noStroke();


  translate(width/2, height/2);

  //CONFIGURE SEGMENT LENGTH AND MODE
  //SETS THE SEGMENT LENGTH BETWEEN TWO POINTS ON A SHAPE/FONT OUTLINE
  RCommand.setSegmentLength(10);//ASSIGN A VALUE OF 10, SO EVERY 10 PIXELS
  RCommand.setSegmentator(RCommand.UNIFORMLENGTH);
  
   //RCommand.setSegmentator(RCommand.CUBICBEZIERTO);
   //RCommand.setSegmentator(RCommand.ADAPTATIVE);

  //GROUP TOGETHER MY FONT & TEXT.
  RGroup myGroup = theFont.toGroup(currentText); 
  myGroup = myGroup.toPolygonGroup();
  
  //ACCESS POINTS ON MY FONT/SHAPE OUTLINE
  RPoint[] myPoints = myGroup.getPoints();

  //DRAW ELLIPSES AT EACH OF THESE POINTS
  for (int i=0; i<myPoints.length; i++) {
    ellipse(myPoints[i].x, myPoints[i].y, 5, 5);
  }



  shp = RG.loadShape("lion.svg");
  shp = RG.centerIn(shp, g, 100);
}

void draw(){
  background(255);

  // We decided the separation between the polygon points dependent of the mouseX
  float pointSeparation = map(constrain(mouseX, 100, width-100), 100, width-100, 5, 200);

  // We create the polygonized version
  RG.setPolygonizer(RG.UNIFORMLENGTH);
  RG.setPolygonizerLength(pointSeparation);

  polyshp = RG.polygonize(shp);

  // We move ourselves to the mouse position
  translate(mouseX, mouseY);

  // We draw the polygonized group with the SVG styles
  RG.shape(polyshp);
}
