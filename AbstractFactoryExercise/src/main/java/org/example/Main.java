package org.example;

public class Main {
    public static void main(String[] args) {
        DecorationPlacer decorationPlacer = new DecorationPlacer(new HalloweenTableclothPatternProvider(), new HalloweenWallHangingProvider(), new HalloweenYardOrnamentProvider());

        System.out.println(decorationPlacer.placeDecorations());
    }
}
