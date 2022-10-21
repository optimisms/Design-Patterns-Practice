package org.example.Exercise2;

public class Part2Main {
    public static void main(String[] args) {
        int[][] myArray = new int[][]{
                {1,2,3,4}, {2,3,4,1}, {3,4,1,2}, {4,1,2,3}
        };

        Part2RealArray real = new Part2RealArray(4, 4);

        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                real.set(i, j, myArray[i][j]);
            }
        }

        real.save("myArray.txt");

        Part2LazyProxyArray proxy = new Part2LazyProxyArray("myArray.txt");
        System.out.println("The filename is " + proxy.getFileName());
        System.out.println("The real array does not exist: " + proxy.getMyArray());
        System.out.println("To initialize it, I will ask for a value: " + proxy.get(1, 1));
        System.out.println("Now the real array exists: " + proxy.getMyArray());

        System.out.println("I will change the value.");
        proxy.set(1, 1, 19);
        System.out.println("Now the item at (1,1) is " + proxy.get(1, 1));
    }
}