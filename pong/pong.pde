int ballX;
int ballY;
int ballSizeX;
int ballSizeY;

int ballSpeedX;
int ballSpeedY;

int mouseYTrack;

void setup() {
  size(1000, 800);
  noStroke();

  ballX = 300;
  ballY = 200;

  ballSizeX = 30;
  ballSizeY = 30;

  ballSpeedX = 4;
  ballSpeedY = 4;
}

void draw() {
  background(0);

  detectCollision();

  ballX = ballX + (1 * ballSpeedX);
  ballY = ballY + (1 * ballSpeedY);

  ellipse(ballX, ballY, ballSizeX, ballSizeY);

  drawPlayerPaddle();
  drawPCPaddle();
}

void drawPlayerPaddle() {
  fill(255);
  mouseYTrack = mouseY;
  rect(50, mouseYTrack, 40, 130);
}

void drawPCPaddle() {
  fill(255);
  rect(750, ballY, 40, 130);
}

void detectCollision() {
  //check vertical
  if (ballY-ballSizeY <= 0 || ballY + ballSizeY >=800) {
    ballSpeedY = -ballSpeedY;
    ballSpeedY++;
  }

  if (ballX+ballSizeX >= 750 || ballX-ballSizeX <= 50) {
    ballSpeedX = -ballSpeedX;
    ballSpeedX++;
  }
}

