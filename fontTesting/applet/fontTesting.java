import processing.core.*; 
import processing.xml.*; 

import rita.wordnet.*; 
import rita.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class fontTesting extends PApplet {



PFont theFont;

String baseString;
String[] fallingWords;
RiText[] riArray;

String randomEmotion;

public void setup() {
  size(700, 700);
  background(0);

  theFont = loadFont("BordeauxRomanBoldLetPlain-48.vlw");
  
  textFont(theFont, 32);
  fill(255, 0, 0);
  //  text(word, 120, 120);
}

public void draw() {
  background(255);
}

public void mouseClicked() {
  theFont = loadFont("BordeauxRomanBoldLetPlain-48.vlw");


  RiLexicon lex = new RiLexicon(this);
  String[] similars = lex.similarBySound("cat");
  String[] rhymes  = lex.getRhymes("cat");
  println(similars);



  RiWordnet wordnet = new RiWordnet(this);
  String[] emotions = wordnet.getAllHyponyms("emotion", "n");
  println(emotions);

  int randomEmotionNumber = PApplet.parseInt(random(0, 17));
  
  randomEmotion = emotions[randomEmotionNumber];
  println(randomEmotion + "\n\n\n");
  

  fallingWords = wordnet.getAllSynonyms(randomEmotion, "n");
  println(fallingWords);
  dropWords();
}

public void dropWords() {
  fill(255);
  rect(0,0, width, height);
  fill(0);
  riArray = new RiText[fallingWords.length];
  riArray[0].fadeAllOut(0.1f);

  fill(255, 0, 0);
  RiText baseWord = new RiText(this, randomEmotion, width/2, height/2, CENTER, theFont);
  fill(255, 0, 0);


  
  for (int i=0; i<fallingWords.length; i++) {
    boolean cont = false;
    int randHeight = PApplet.parseInt(random(0, height));
    int randWidth = PApplet.parseInt(random(0, width));
    int counter = 0;
    
    cont = true; 

    RiText rt = new RiText(this, fallingWords[i], randWidth, randHeight, CENTER, theFont);
    float tw = rt.textWidth();
    rt.moveTo(width/2, height/2, 2);
    rt.fadeOut(2.0f, true);
    
    riArray[i] = rt;
    
    
     
  }
  riArray[0].fadeAllOut(2.0f);

  baseWord.scaleTo(5.5f, 0.3f, 3.0f);
  baseWord.fadeColor(244, 0, 0, 1.0f, 2.0f);



 baseWord.fadeOut(3.0f, 3.0f, true);
}







  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#FFFFFF", "fontTesting" });
  }
}
