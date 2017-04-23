package example.lowestcastpath.com.lowestcastpath;


import java.util.ArrayList;

public class LowestCostPath {

    public static OpResult lowestCostPath(String[][] data) {
        if (data == null) {
            return null;
        }
        int[][] array = new int[data.length][data[0].length];
        try {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    array[i][j] = Integer.parseInt(data[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }

        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i][0];
        }

        int currentRow = minValueIndex(temp);
        int sum = array[currentRow][0];
        ArrayList<Integer> visitedRows = new ArrayList<Integer>();
        visitedRows.add(Integer.valueOf(currentRow + 1));

        for (int i = 0; i < array[0].length - 1; i++) {
            int a = array[currentRow - 1 >= 0 ? currentRow - 1 : array.length - 1][i + 1];
            int b = array[currentRow][i + 1];
            int c = array[currentRow + 1 >= array.length ? 0 : currentRow + 1][i + 1];
            int min = minimum(a, b, c);
            currentRow += indexOf(min, a, b);
            if (currentRow < 0) {
                currentRow = array.length - 1;
            } else if (currentRow >= array.length) {
                currentRow = 0;
            }
            sum += min;
            if (sum > 50) {
                sum -= min;
                break;
            }
            visitedRows.add(Integer.valueOf(currentRow + 1));
        }
        OpResult opResult = new OpResult();
        opResult.minCost = sum;
        opResult.traversedGrid = visitedRows.size() == array[0].length ? "Yes" : "No";
        opResult.rowsTraversed = visitedRows;
        return opResult;
    }

    private static int minValueIndex(int[] array) {
        int index = 0;
        int min = array[index];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    private static int indexOf(int value, int i, int j) {
        if (value == i) {
            return -1;
        } else if (value == j) {
            return 0;
        } else {
            return 1;
        }
    }

    private static int minimum(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }

}
