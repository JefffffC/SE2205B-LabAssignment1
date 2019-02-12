import java.io.*;
import java.util.Scanner;

public class Assignment1{
  
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
      int resultArray[][] = initMatrix(size); // Initiate returned array
      if (size == 1) { // For minimum threshold where it is only one number, do normal multiplication
          resultArray[0][0] = A[0][0] * B[0][0];
          return resultArray;
      }
      else {
          int nullArray[][] = initMatrix(size); // Blank array which serves as empty placeholder to fulfil sum/sub functions
          int M0[][] = denseMatrixMult(sum(A,A,0,0,size/2,size/2,size/2),sum(B,B,0,0,size/2,size/2,size/2),size/2);
          int M1[][] = denseMatrixMult(sum(A,A,size/2,0,size/2,size/2,size/2),sum(B,nullArray,0,0,0,0,size/2),size/2);
          int M2[][] = denseMatrixMult(sum(A,nullArray,0,0,0,0,size/2),sub(B,B,0,size/2,size/2,size/2,size/2),size/2);
          int M3[][] = denseMatrixMult(sum(A,nullArray,size/2,size/2,0,0,size/2),sub(B,B,size/2,0,0,0,size/2),size/2);
          int M4[][] = denseMatrixMult(sum(A,A,0,0,0,size/2,size/2),sum(B,nullArray,size/2,size/2,0,0,size/2),size/2);
          int M5[][] = denseMatrixMult(sub(A,A,size/2,0,0,0,size/2),sum(B,B,0,0,0,size/2,size/2),size/2);
          int M6[][] = denseMatrixMult(sub(A,A,0,size/2,size/2,size/2,size/2),sum(B,B,size/2,0,size/2,size/2,size/2),size/2);

          int C00[][] = sub(sum(sum(M0,M3,0,0,0,0,size/2),M6,0,0,0,0,size/2),M4,0,0,0,0,size/2);
          int C01[][] = sum(M2,M4,0,0,0,0,size/2);
          int C10[][] = sum(M1,M3,0,0,0,0,size/2);
          int C11[][] = sub(sum(sum(M0,M2,0,0,0,0,size/2),M5,0,0,0,0,size/2),M1,0,0,0,0,size/2);
          for (int i = 0; i < size/2; i++) {
              for (int j = 0; j < size/2; j++) { // Merge 4 matrices into one matrix
                  resultArray[i][j] = C00[i][j];
                  resultArray[i][j + size/2] = C01[i][j];
                  resultArray[i + size/2][j] = C10[i][j];
                  resultArray[i + size/2][j + size/2] = C11[i][j];
              }
          }
          return resultArray;
      }
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
      int sumArray[][] = new int[n][n];
      int k = (x2-1);
      int l = (y2-1);
      for (int i = x1; i < (n+x1); i++) {
          k++;
          int loopCounter = 0; // reset counter which ensures that y2 resets
          for (int j = y1; j < (n + y1); j++) {
              l++;
              loopCounter++;
              sumArray[(i - x1)][(j - y1)] = A[i][j] + B[k][l];
              if (loopCounter==(n)) { // reset y2 upon n iterations in order to ensure correct iterating
                  l=l-n;
              }
          }
      }
      return sumArray;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
      int subArray[][] = new int[n][n];
      int k = (x2-1);
      int l = (y2-1);
      for (int i = x1; i < (n+x1); i++) {
          k++;
          int loopCounter = 0; // reset counter which ensures that y2 resets
          for (int j = y1; j < (n + y1); j++) {
              l++;
              loopCounter++;
              subArray[(i - x1)][(j - y1)] = A[i][j] - B[k][l];
              if (loopCounter==(n)) { // reset y2 upon n iterations in order to ensure correct iterating
                  l=l-n;
              }
          }
      }
      return subArray;
  }
  
  
  public int[][] initMatrix(int n)
  {
      int matrixArray[][] = new int[n][n];
      return matrixArray;
  }
  
  public void printMatrix(int n, int[][] A)
  {
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < (n-1); j++) { // loop with n-1 to ensure space is not printed at end of row
              System.out.print(A[i][j] + " ");
          }
          System.out.print(A[i][n-1]); // print the last item without a space
          System.out.println();
      }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception
  {
      String workingDir = System.getProperty("user.dir");
      File homeFile = new File(workingDir + "Test.java"); //working directory is not the same as target directory, need to go back one
      String targetDir = homeFile.getParent();
      File readFile = new File(targetDir + "\\" + filename); // create File with correct pathway
      Scanner sc = new Scanner(readFile);
      int i = 0;
      int scannedArray[][] = new int[n][n]; // returned matrix to add values to
      while (i < (n*n)) {
          for (int k = 0; k < n; k++) {
              for (int l = 0; l < n; l++) {
                  int j = sc.nextInt();
                  scannedArray[k][l] = j; // scan and add all integers to the matrix correctly
                  i++;
              }
          }
      }
      sc.close();
      return scannedArray;
  }
}