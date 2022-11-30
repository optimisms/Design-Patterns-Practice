package org.example;

import org.example.interfaces.TableclothPatternInterface;
import org.example.interfaces.WallHangingInterface;
import org.example.interfaces.YardOrnamentInterface;

public class DecorationPlacer {
    private TableclothPatternInterface tableclothPattern;
    private WallHangingInterface wallHanging;
    private YardOrnamentInterface yardOrnament;

    public DecorationPlacer(TableclothPatternInterface tableclothPattern, WallHangingInterface wallHanging, YardOrnamentInterface yardOrnament) {
        this.tableclothPattern=tableclothPattern;
        this.wallHanging=wallHanging;
        this.yardOrnament=yardOrnament;
    }

    public String placeDecorations() {
        return "Everything was ready for the party. The " + yardOrnament.getOrnament()
                + " was in front of the house, the " + wallHanging.getHanging()
                + " was hanging on the wall, and the tablecloth with " + tableclothPattern.getTablecloth()
                + " was spread over the table.";
    }
}
