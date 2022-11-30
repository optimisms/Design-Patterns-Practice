package org.example;

import org.example.factory.ChristmasFactory;
import org.example.factory.HalloweenFactory;

public class Main {
    public static void main(String[] args) {
        DecorationPlacer halloweenDecorationPlacer = new DecorationPlacer(new HalloweenFactory());
        DecorationPlacer christmasDecorationPlacer = new DecorationPlacer(new ChristmasFactory());

        System.out.println(halloweenDecorationPlacer.placeDecorations());
        System.out.println(christmasDecorationPlacer.placeDecorations());
    }
}
