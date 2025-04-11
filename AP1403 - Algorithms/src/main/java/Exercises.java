import java.util.ArrayList;

public class Exercises {

    /*
        there is an array of positive integers as input of function and another integer for the target value
        all the algorithm should do is to find those two integers in array which their multiplication is the target
        then it should return an array of their indices
        e.g. {1, 2, 3, 4} with target of 8 -> {1, 3}

        note: you should return the indices in ascending order and every array's solution is unique
    */
    public int[] productIndices(int[] values, int target) {
        int size = values.length;
        int[] result = new int[2];
        for (int i = 0; i < size; i++){
            if (target % values[i] == 0){
                for (int j = i + 1; j < size; j++){
                    if (target == (values[i] * values[j])){
                        result[0] = i;
                        result[1] = j;
                        //System.out.println(i + " " + j);
                        return result;

                    }
                }
            }
        }
        return null;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        if (rows <= 0 || cols <= 0){
            return null;
        }
        int k = 0;
        int count = 0;
        int i = 0;
        int j = 0;
        int[] result = new int[rows * cols];
        for (; count < rows * cols;){
            for(i = k; j < cols - k && count < rows * cols; j++){
                result[count] = values[i][j];
                //System.out.println(result[count]);
                count++;
            }
            i++;
            for (j = cols - k - 1;i < rows - k && count < rows * cols; i++){
                result[count] = values[i][j];
                //System.out.println(result[count]);
                count++;
            }
            i--;
            for (j = cols - k - 2; j >= k && count < rows * cols; j--){
                result[count] = values[i][j];
                //System.out.println(result[count]);
                count++;
            }
            i--;
            for(j = k ; i > k + 1 && count < rows * cols; i--){
                result[count] = values[i][j];
                //System.out.println(result[count]);
                count++;
            }
            k++;
        }
        return result;

    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */

    public ArrayList<ArrayList<Integer>> intPartitions(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int count = -1;
        int i = 0, j = 0, k = 0, t = 0;

        for (; k < n; k++) {
            t = 0;
            result.add(new ArrayList<>());
            count++;

            for (i = 0; i < n / (n - k); i++) {
                result.get(count).add(n - k);
            }
            if (k != 0 && n % (n - k) != 0) {
                result.get(count).add(n % (n - k));
            }
            if (result.get(count).size() == 1 || result.get(count).get(0) == n - 1) {
                continue;
            }
            if(result.get(count).size() == n){
                return result;
            }


            for (j = result.get(count).size() - 1; j > 0 && result.get(count).size() > 1 && result.get(count).get(1) != 1; j--) {
                int temp = result.get(count).get(j);
                if (result.get(count).get(j) != 1) {
                    result.add(new ArrayList<>());
                    result.get(count + 1).addAll(result.get(count));
                    count++;
                    result.get(count).set(j, temp - 1);
                    result.get(count).add(1);
                    result.add(new ArrayList<>());
                    result.get(count + 1).addAll(result.get(count));
                    count++;

                }

                for (i = j + 1; result.get(count).get(j) != 1 && i < result.get(count).size(); i++) {
                    if (result.get(count).get(j) > result.get(count).get(i) + 1) {
                        temp = result.get(count).get(j);
                        result.get(count).set(j, temp - 1);
                        temp = result.get(count).get(i);
                        result.get(count).set(i, temp + 1);
                        count++;

                    } else if (result.get(count).get(j) == 2 && result.get(count).get(j + 1) == 1) {
                        temp = result.get(count).get(j);
                        result.get(count).set(j, temp - 1);
                        result.get(count).add(1);
                    }

                }
                if(result.get(count).equals(result.get(count-1))){
                    result.remove(count);
                    count--;
                }
            }


        }

        return result;
    }


    public static void main(String[] args) {
        Exercises ex = new Exercises();
        /*
        int[] array = {5, 10, 2, 20};
        System.out.println(ex.productIndices(array, 20));
        int[] array2 = {3, 7, 1, 14};
        System.out.println(ex.productIndices(array2, 21));
        int[] array3 = {69, 15, 17, 23, 5, 11, 4, 6, 85, 39, 81, 34, 76, 21, 3, 36, 98, 77, 9, 42};
        System.out.println(ex.productIndices(array3, 2898));

        int[][] nums = {
                {1, 2, 3, 4, 5}
        };
        int rows = 1;
        int cols = 5;
        System.out.print(ex.spiralTraversal(nums, rows, cols));
        int[][] nums2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        int rows2 = 4;
        int cols2 = 3;
        System.out.print(ex.spiralTraversal(nums2, rows2, cols2));
        int[][] nums3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        int rows3 = 2;
        int cols3 = 4;
        System.out.print(ex.spiralTraversal(nums3, rows3, cols3));
        int[][] nums4 = {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30}
        };
        int rows4 = 5;
        int cols4 = 6;
        System.out.print(ex.spiralTraversal(nums4, rows4, cols4));

        System.out.println(ex.intPartitions(1));
        System.out.println(ex.intPartitions(4));
        System.out.println(ex.intPartitions(5));
        System.out.println(ex.intPartitions(6));
         */
    }
    
}
