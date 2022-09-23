//64050538 ประณต คำวงษา
// 1.6 From worker1 thread 14 x value is 3
//     From worker2 thread 15 x value is 7
//     from main x value is 7

import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);

        //Q4
        Thread tCal[][] = new Thread[matC_r][matC_c]; 
        for(int i = 0 ; i < matC_r ; i++){
            for(int j = 0 ; j < matC_c ; j++){
                tCal[i][j] = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
                tCal[i][j].start();
            }
        }

        //Q5
        try{
            for(int i = 0 ; i < matC_r ; i++){
                for(int j = 0 ; j < matC_c ; j++){
                    tCal[i][j].join();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        matC.show();   
    }

    static class MatrixMulThread implements Runnable{
        int processing_row;
        int processing_col;
        MyData datA;
        MyData datB;
        MyData datC;

        MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c){
            //Q1
            processing_row = tRow;
            processing_col = tCol;
            datA = a;
            datB = b;
            datC = c;
        }

        //Q2
        public void run(){
            //Q3
            datC.data[processing_row][processing_col] = 0;
            for(int i = 0 ; i < datB.data.length ; i++){
                datC.data[processing_row][processing_col] += datA.data[processing_row][i] * datB.data[i][processing_col]; 
            }
            System.out.println("perform sum of multiplication of assigned row and col");
        }
    }
    
    static class MyData{
        int[][] data;
        MyData(int[][] m){
            data = m;
        }

        MyData(int r, int c){
            data = new int[r][c];
            for(int[] aRow : data){
                Arrays.fill(aRow, 9);
            }
        }

        void show(){
            System.out.println(Arrays.deepToString(data));
        }
    }
}
