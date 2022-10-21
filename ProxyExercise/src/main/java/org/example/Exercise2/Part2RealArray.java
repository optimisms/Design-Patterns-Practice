package org.example.Exercise2;

import java.io.*;

public class Part2RealArray implements Part2Array2D<Integer> {
    private int[][] array;

    public Part2RealArray(int rows, int cols) {
        array = new int[rows][cols];
    }

    public Part2RealArray(String fileName) {
        load(fileName);
    }

    @Override
    public void set(int row, int col, Integer value) {
        array[row][col] = value;
    }

    @Override
    public Integer get(int row, int col) {
        return array[row][col];
    }

    public void save(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream iis = new ObjectInputStream(fis);
            array = (int[][]) iis.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
