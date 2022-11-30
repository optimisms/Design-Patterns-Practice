package org.example;

import org.example.decorations.interfaces.TableclothPatternInterface;
import org.example.decorations.interfaces.WallHangingInterface;
import org.example.decorations.interfaces.YardOrnamentInterface;
import org.example.factory.DecorationFactoryInterface;

public class DecorationPlacer {
    private final TableclothPatternInterface tableclothPattern;
    private final WallHangingInterface wallHanging;
    private final YardOrnamentInterface yardOrnament;

    public DecorationPlacer(DecorationFactoryInterface factory) {
        this.tableclothPattern = factory.getTablecloth();
        this.wallHanging = factory.getWallHanging();
        this.yardOrnament = factory.getYardOrnament();
    }

    public String placeDecorations() {
        return "Everything was ready for the party. The " + yardOrnament.getOrnament()
                + " was in front of the house, the " + wallHanging.getHanging()
                + " was hanging on the wall, and the tablecloth with " + tableclothPattern.getTablecloth()
                + " was spread over the table.";
    }
}
