package org.example;

import org.example.factory.HalloweenFactory;

public class Main {
    public static void main(String[] args) {
        DecorationPlacer decorationPlacer = new DecorationPlacer(new HalloweenFactory());

        System.out.println(decorationPlacer.placeDecorations());
    }
}
