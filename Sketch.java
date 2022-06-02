import processing.core.PApplet;

public class Sketch extends PApplet {
	
  int intWidth = 20; 
  int intHeight = 20;
  int intRow = 10;
  int intColumn = 10;
  int MARGIN = 5;

  int SCREEN_WIDTH = intWidth * intColumn + MARGIN * (intColumn + 1);
  int SCREEN_HEIGHT = intHeight * intRow + MARGIN * (intColumn + 1);

  int[][] intGrid = new int[10][10]; 
  int cellCount;
 
  public void settings() {
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  public void setup() {
    background(0);
  }

  public void draw() {
    // draw grid 
    for (int i  = 0; i < intColumn; i++) {
      for (int x = 0; x < intRow; x++) {
        if (intGrid[x][i] == 1){
          fill(0, 255, 0);
          rect(MARGIN + MARGIN * i + intWidth * i, MARGIN + MARGIN * x + intHeight * x, intWidth, intHeight);
        }
        else {
          fill(255);
          rect(MARGIN + MARGIN * i + intWidth * i, MARGIN + MARGIN * x + intHeight * x, intWidth, intHeight);
        }
      }
    }
  }

  public void mousePressed(){
    int intRowCount = (int)(mouseY / (MARGIN + intHeight));
    int intColumnCount = (int)(mouseX / (MARGIN + intWidth));

  
    if (intGrid[intRowCount][intColumnCount] == 0) {
      intGrid[intRowCount][intColumnCount] = 1;
    }
    else if (intGrid[intRowCount][intColumnCount] == 1){
      intGrid[intRowCount][intColumnCount] = 0;    
    }

    // change colour of lower cell
    if (intRowCount < intRow - 1 && intGrid[intRowCount + 1][intColumnCount] == 0) {
      intGrid[intRowCount + 1][intColumnCount] = 1;
    }
    else if (intRowCount < intRow - 1 && intGrid[intRowCount + 1][intColumnCount] == 1) {
      intGrid[intRowCount + 1][intColumnCount] = 0;
    }

    // change colour of upper cell
    if (intRowCount > 0 && intGrid[intRowCount - 1][intColumnCount] == 0) {
      intGrid[intRowCount - 1][intColumnCount] = 1;
    }
    else if (intRowCount > 0 && intGrid[intRowCount - 1][intColumnCount] == 1) {
      intGrid[intRowCount - 1][intColumnCount] = 0;
    }

    // change colour of cell to the right
    if (intColumnCount < intColumn - 1 && intGrid[intRowCount][intColumnCount + 1] == 0) {
      intGrid[intRowCount][intColumnCount + 1] = 1;
    }
    else if (intColumnCount < intColumn - 1 && intGrid[intRowCount][intColumnCount + 1] == 1) {
      intGrid[intRowCount][intColumnCount + 1] = 0;
    }

    // change colour of cell to the left
    if (intColumnCount > 0 && intGrid[intRowCount][intColumnCount - 1] == 0) {
      intGrid[intRowCount][intColumnCount - 1] = 1;
    }
    else if (intColumnCount > 0 && intGrid[intRowCount][intColumnCount - 1] == 1) {
      intGrid[intRowCount][intColumnCount - 1] = 0;
    } 


    // calculate number of selected cells
    cellCount = 0;
    for (int r = 0; r < intRow; r++) {
      for (int c = 0; c < intColumn; c++) {
        if (intGrid[r][c] == 1){
          cellCount ++;
        }
      }
    }


    for (int r = 0; r < intRow; r++){
      // count the green cells in a row
      int intGreenrowcount = 0;
      // count the number of continueous green cells in a row
      int intContinuous = 0;
      // count the largest number of continueous green cells in a row
      int intMaxContinuous = 0;

      for (int c = 0; c < intColumn; c++) {
        if (intGrid[r][c] == 1) {
          intContinuous ++;
          intGreenrowcount ++;
          if (intContinuous > intMaxContinuous) {
            intMaxContinuous = intContinuous;
          }
        }
          else if (intGrid[r][c] == 0){
          intContinuous = 0;
        }
      }

      if (intMaxContinuous > 2) {
        System.out.println("There are " + intMaxContinuous + " continous blocks selected on row " + r + ".");
      }
      System.out.println("Row " + r + " has " + intGreenrowcount + " cells selected.");
    }

    for (int c = 0; c < intColumn; c++) {
      int cellColCount = 0;
      for (int r = 0; r < intRow; r++) {
        if (intGrid[r][c] == 1) {
          cellColCount ++;
        }
      }
      System.out.println("Column " + c + " has " + cellColCount + " cells selected.");
    }

    // cells
    System.out.println("Total of " + cellCount + " are selected.");
  }
}