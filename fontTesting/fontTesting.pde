import rita.wordnet.*;
import rita.*;
PFont theFont;

String baseString;
String[] fallingWords;
RiText[] riArray;

String randomEmotion;

void setup() {
  size(700, 700);
  background(0);
  FrameRate(20);
  theFont = loadFont("BordeauxRomanBoldLetPlain-48.vlw");
  
  textFont(theFont, 32);
  fill(255, 0, 0);
  //  text(word, 120, 120);
}

void draw() {
  background(255);
}

void mouseClicked() {
  theFont = loadFont("BordeauxRomanBoldLetPlain-48.vlw");


  RiLexicon lex = new RiLexicon(this);
  String[] similars = lex.similarBySound("cat");
  String[] rhymes  = lex.getRhymes("cat");
  println(similars);



  RiWordnet wordnet = new RiWordnet(this);
  String[] emotions = wordnet.getAllHyponyms("emotion", "n");
  println(emotions);

  int randomEmotionNumber = int(random(0, 17));
  
  randomEmotion = emotions[randomEmotionNumber];
  println(randomEmotion + "\n\n\n");
  

  fallingWords = wordnet.getAllSynonyms(randomEmotion, "n");
  println(fallingWords);
  dropWords();
}

void dropWords() {
  fill(255);
  rect(0,0, width, height);
  fill(0);
  riArray = new RiText[fallingWords.length];
  riArray[0].fadeAllOut(0.1);

  fill(255, 0, 0);
  RiText baseWord = new RiText(this, randomEmotion, width/2, height/2, CENTER, theFont);
  fill(255, 0, 0);


  
  for (int i=0; i<fallingWords.length; i++) {
    boolean cont = false;
    int randHeight = int(random(0, height));
    int randWidth = int(random(0, width));
    int counter = 0;
    
    cont = true; 

    RiText rt = new RiText(this, fallingWords[i], randWidth, randHeight, CENTER, theFont);
    float tw = rt.textWidth();
    rt.moveTo(width/2, height/2, 2);
    rt.fadeOut(2.0, true);
    
    riArray[i] = rt;
    
    
     
  }
  riArray[0].fadeAllOut(2.0);

  baseWord.scaleTo(5.5, 0.3, 3.0);
  baseWord.fadeColor(244, 0, 0, 1.0, 2.0);



 baseWord.fadeOut(3.0, 3.0, true);
}







