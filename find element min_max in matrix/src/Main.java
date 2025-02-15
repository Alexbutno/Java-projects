import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            int[][] matrix = inputFile ("first.txt");
            if ((matrix.length==1)&&(matrix[0].length==1)){
                System.out.println("there is only 1 element");
            }
            if ((matrix.length==1)&&(matrix[0].length==2)){
                if (matrix[0][0]<matrix[0][1]){
                    System.out.println("element:"+matrix[0][0]+" is minimum on position:line "+1+" column "+1);
                    System.out.println("element:"+matrix[0][1]+" is maximum on position:line "+1+" column "+2);
                }
            }
            if ((matrix.length==2)&&(matrix[0].length==1)){
                if (matrix[0][0]<matrix[1][0]){
                    System.out.println("element:"+matrix[0][0]+" is minimum on position:line "+1+" column "+1);
                    System.out.println("element:"+matrix[1][0]+" is minimum on position:line "+2+" column "+1);
                }
            }
            if ((matrix.length==1)&&(matrix[0].length>2)){
                int k = matrix[0].length;
                if (matrix[0][0]<matrix[0][1]){
                    System.out.println("element:"+matrix[0][0]+" is minimum on position:line "+1+" column "+1);
                } else{
                    System.out.println("element:"+matrix[0][0]+" is maximum on position:line "+1+" column "+1);
                }
                if (matrix[0][k-2]<matrix[0][k-1]){
                    System.out.println("element:"+matrix[0][k-1]+" is maximum on position:line "+1+" column "+k);
                } else {
                    System.out.println("element:"+matrix[0][k-1]+" is minimum on position:line "+1+" column "+k);
                }
                for (int j=1;j < (matrix[0].length - 1);j++){
                    if ((matrix[0][j]<matrix[0][j-1])&&(matrix[0][j]<matrix[0][j+1])){
                        System.out.println("element:"+matrix[0][j]+" is minimum on position:line "+1+" column "+(j+1));
                    }
                }
                for (int j=1;j < (matrix[0].length - 1);j++){
                    if ((matrix[0][j]>matrix[0][j-1])&&(matrix[0][j]>matrix[0][j+1])){
                        System.out.println("element:"+matrix[0][j]+" is maximum on position:line "+1+" column "+(j+1));
                    }
                }
            }
            if ((matrix.length>2)&&(matrix[0].length==1)){
                int k = matrix.length;
                if (matrix[0][0]<matrix[1][0]){
                    System.out.println("element:"+matrix[0][0]+" is minimum on position:line "+1+" column "+1);
                } else{
                    System.out.println("element:"+matrix[0][0]+" is maximum on position:line "+1+" column "+1);
                }
                if (matrix[k-2][0]<matrix[k-1][0]){
                    System.out.println("element:"+matrix[k-1][0]+" is maximum on position:line "+k+" column "+1);
                } else {
                    System.out.println("element:"+matrix[k-1][0]+" is minimum on position:line "+k+" column "+1);
                }
                for (int i=1;i < (matrix.length - 1);i++){
                    if ((matrix[i][0]<matrix[i-1][0])&&(matrix[i][0]<matrix[i+1][0])){
                        System.out.println("element:"+matrix[i][0]+" is minimum on position:line "+(i+1)+" column "+1);
                    }
                }
                for (int i=1;i < (matrix.length - 1);i++){
                    if ((matrix[i][0]>matrix[i-1][0])&&(matrix[i][0]>matrix[i+1][0])){
                        System.out.println("element:"+matrix[i][0]+" is maximum on position:line "+(i+1)+" column "+1);
                    }
                }

            }
            if ((matrix.length>=2)&&(matrix[0].length>=2)) {
                findCountsCheckingCorners(matrix);
                findCountsMinCenter(matrix);
                findCountsMaxCenter(matrix);
                findCountsMinLines(matrix);
                findCountsMaxLines(matrix);
                findCountsMinColumn(matrix);
                findCountsMaxColumn(matrix);
            }
        } catch (InputMismatchException e){
            System.out.println("incorrect data entry");
        } catch (DataInvalidException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e){
            System.out.println("not enough elements");
        } catch (FileNotFoundException exp) {
            System.out.println("The specified file cannot be found");
        }
    }
    public static class DataInvalidException extends Exception {
        public DataInvalidException(String message) {
            super(message);
        }
    }
    public static int[][] inputFile (String path) throws NumberFormatException,FileNotFoundException,NoSuchElementException,DataInvalidException {

        Scanner scanner = new Scanner(new FileReader(path));
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if (n <= 0) throw new DataInvalidException("n<=0");
        if (m <= 0) throw new DataInvalidException("m<=0");
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        if (scanner.hasNext()) throw new DataInvalidException("excess number of elements");
        return matrix;
    }
    public static void findCountsMinCenter(int[][] matrix){
    int n = matrix.length;
    int m = matrix[0].length;
    for (int i = 1;i < n-1;i++){
        for (int j = 1;j < m-1;j++){
            if ((matrix[i][j]<matrix[i-1][j-1])&&(matrix[i][j]<matrix[i][j-1])&&(matrix[i][j]<matrix[i+1][j-1])&&(matrix[i][j]<matrix[i+1][j])&&(matrix[i][j]<matrix[i+1][j+1])&&(matrix[i][j]<matrix[i][j+1])&&(matrix[i][j]<matrix[i-1][j+1])&&(matrix[i][j]<matrix[i-1][j])){
                System.out.println("element:"+matrix[i][j]+" is minimum on position:line "+(i+1)+" column "+(j+1));
            }
        }
    }
    }
    public static void findCountsMaxCenter(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 1;i < n-1;i++){
            for (int j = 1;j < m-1;j++){
                if ((matrix[i][j]>matrix[i-1][j-1])&&(matrix[i][j]>matrix[i][j-1])&&(matrix[i][j]>matrix[i+1][j-1])&&(matrix[i][j]>matrix[i+1][j])&&(matrix[i][j]>matrix[i+1][j+1])&&(matrix[i][j]>matrix[i][j+1])&&(matrix[i][j]>matrix[i-1][j+1])&&(matrix[i][j]>matrix[i-1][j])){
                    System.out.println("element:"+matrix[i][j]+" is maximum on position:line "+(i+1)+"column "+(j+1));
                }
            }
        }
    }
    public static void findCountsMinLines(int[][] matrix){
        for (int j=1;j < (matrix[0].length - 1);j++){
            if ((matrix[0][j]<matrix[0][j-1])&&(matrix[0][j]<matrix[1][j-1])&&(matrix[0][j]<matrix[1][j])&&(matrix[0][j]<matrix[1][j+1])&&(matrix[0][j]<matrix[0][j+1])){
                System.out.println("element:"+matrix[0][j]+" is minimum on position:line "+0+"column "+j);
            }
        }
        int i = matrix.length;
        for (int j=1;j < (matrix[0].length - 1);j++){
            if ((matrix[i-1][j]<matrix[i-1][j-1])&&(matrix[i-1][j]<matrix[i-2][j-1])&&(matrix[i-1][j]<matrix[i-2][j])&&(matrix[i-1][j]<matrix[i-2][j+1])&&(matrix[i-1][j]<matrix[i-1][j+1])){
                System.out.println("element:"+matrix[i-1][j]+" is minimum on position:line "+i+"column "+(j+1));
            }
        }
    }
    public static void findCountsMaxLines(int[][] matrix){
        for (int j=1;j < (matrix[0].length - 1);j++){
            if ((matrix[0][j]>matrix[0][j-1])&&(matrix[0][j]>matrix[1][j-1])&&(matrix[0][j]>matrix[1][j])&&(matrix[0][j]>matrix[1][j+1])&&(matrix[0][j]>matrix[0][j+1])){
                System.out.println("element:"+matrix[0][j]+" is maximum on position:line "+0+"column "+j);

            }
        }
        int i = matrix.length;
        for (int j=1;j < (matrix[0].length - 1);j++){
            if ((matrix[i-1][j]>matrix[i-1][j-1])&&(matrix[i-1][j]>matrix[i-2][j-1])&&(matrix[i-1][j]>matrix[i-2][j])&&(matrix[i-1][j]>matrix[i-2][j+1])&&(matrix[i-1][j]>matrix[i-1][j+1])){
                System.out.println("element:"+matrix[0][j]+" is minimum on position:line "+i+"column "+j);
            }
        }
    }
    public static void findCountsMinColumn(int[][] matrix){

        for (int i=1;i < (matrix.length - 1);i++){
            if ((matrix[i][0]<matrix[i-1][0])&&(matrix[i][0]<matrix[i-1][1])&&(matrix[i][0]<matrix[i][1])&&(matrix[i][0]<matrix[i+1][1])&&(matrix[i][0]<matrix[i+1][0])){
                System.out.println("element:"+matrix[i][0]+" is minimum on position:line "+(i+1)+" column "+1);
            }
        }
        int j = matrix[0].length;
        for (int i=1;i < (matrix.length - 1);i++){
            if ((matrix[i][j-1]<matrix[i-1][j-1])&&(matrix[i][j-1]<matrix[i-1][j-2])&&(matrix[i][j-1]<matrix[i][j-2])&&(matrix[i][j-1]<matrix[i+1][j-2])&&(matrix[i][j-1]<matrix[i+1][j-1])){
                System.out.println("element:"+matrix[i][j-1]+" is minimum on position:line "+(i+1)+" column "+j);
            }
        }
    }
    public static void findCountsMaxColumn(int[][] matrix){

        for (int i=1;i < (matrix.length - 1);i++){
            if ((matrix[i][0]>matrix[i-1][0])&&(matrix[i][0]>matrix[i-1][1])&&(matrix[i][0]>matrix[i][1])&&(matrix[i][0]>matrix[i+1][1])&&(matrix[i][0]>matrix[i+1][0])){
                System.out.println("element:"+matrix[i][0]+" is maximum on position:line "+(i+1)+" column "+1);
            }
        }
        int j = matrix[0].length;
        for (int i=1;i < (matrix.length - 1);i++){
            if ((matrix[i][j-1]>matrix[i-1][j-1])&&(matrix[i][j-1]>matrix[i-1][j-2])&&(matrix[i][j-1]>matrix[i][j-2])&&(matrix[i][j-1]>matrix[i+1][j-2])&&(matrix[i][j-1]>matrix[i+1][j-1])){
                System.out.println("element:"+matrix[i][j-1]+" is maximum on position:line "+(i+1)+" column "+j);
            }
        }
    }
    public static void findCountsCheckingCorners(int[][] matrix){
        int i = matrix.length;
        int j = matrix[0].length;
        if ((matrix[0][0]<matrix[0][1])&&(matrix[0][0]<matrix[1][1])&&(matrix[0][0]<matrix[1][0])){
            System.out.println("element:"+matrix[0][0]+" is minimum on position:line "+1+" column "+1);
        }
        if ((matrix[0][j-1]<matrix[0][j-2])&&(matrix[0][j-1]<matrix[1][j-2])&&(matrix[0][j-1]<matrix[1][j-1])){
            System.out.println("element:"+matrix[0][j-1]+" is minimum on position:line "+1+" column "+j);
        }
        if ((matrix[i-1][j-1]<matrix[i-1][j-2])&&(matrix[i-1][j-1]<matrix[i-2][j-2])&&(matrix[i-1][j-1]<matrix[i-2][j-1])){
            System.out.println("element:"+matrix[i-1][j-1]+" is minimum on position:line "+i+" column "+j);
        }
        if ((matrix[i-1][0]<matrix[i-2][0])&&(matrix[i-1][0]<matrix[i-2][1])&&(matrix[i-1][0]<matrix[i-1][1])){
            System.out.println("element:"+matrix[i-1][0]+" is minimum on position:line "+i+" column "+1);
        }

        if ((matrix[0][0]>matrix[0][1])&&(matrix[0][0]>matrix[1][1])&&(matrix[0][0]>matrix[1][0])){
            System.out.println("element:"+matrix[0][0]+" is maximum on position:line "+1+" column "+1);
        }
        if ((matrix[0][j-1]>matrix[0][j-2])&&(matrix[0][j-1]>matrix[1][j-2])&&(matrix[0][j-1]>matrix[1][j-1])){
            System.out.println("element:"+matrix[0][j-1]+" is maximum on position:line "+1+" column "+j);
        }
        if ((matrix[i-1][j-1]>matrix[i-1][j-2])&&(matrix[i-1][j-1]>matrix[i-2][j-2])&&(matrix[i-1][j-1]>matrix[i-2][j-1])){
            System.out.println("element:"+matrix[i-1][j-1]+" is maximum on position:line "+i+" column "+j);
        }
        if ((matrix[i-1][0]>matrix[i-2][0])&&(matrix[i-1][0]>matrix[i-2][1])&&(matrix[i-1][0]>matrix[i-1][1])){
            System.out.println("element:"+matrix[i-1][0]+" is maximum on position:line "+i+" column "+1);
        }
    }
}