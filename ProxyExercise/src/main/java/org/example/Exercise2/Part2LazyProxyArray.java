package org.example.Exercise2;

public class Part2LazyProxyArray implements Part2Array2D<Integer> {
    Part2RealArray myArray;
    String fileName;

    public Part2LazyProxyArray(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void set(int row, int col, Integer value) {
        lazyLoad();
        myArray.set(row, col, value);
    }

    @Override
    public Integer get(int row, int col) {
        lazyLoad();
        return myArray.get(row, col);
    }

    public void save(String fileName) {
        lazyLoad();
        myArray.save(fileName);
    }

    public void load(String fileName) {
        lazyLoad();
        myArray.load(fileName);
    }

    private void lazyLoad() {
        if (myArray == null) { myArray = new Part2RealArray(fileName); }
    }

    public String getFileName() {
        return fileName;
    }

    public Part2RealArray getMyArray() {
        return myArray;
    }
}
