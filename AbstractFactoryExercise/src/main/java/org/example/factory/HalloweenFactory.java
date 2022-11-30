package org.example.factory;

import org.example.decorations.halloween.HalloweenTableclothPatternProvider;
import org.example.decorations.halloween.HalloweenWallHangingProvider;
import org.example.decorations.halloween.HalloweenYardOrnamentProvider;
import org.example.decorations.interfaces.TableclothPatternInterface;
import org.example.decorations.interfaces.WallHangingInterface;
import org.example.decorations.interfaces.YardOrnamentInterface;

public class HalloweenFactory implements DecorationFactoryInterface {
    @Override
    public TableclothPatternInterface getTablecloth() {
        return new HalloweenTableclothPatternProvider();
    }

    @Override
    public WallHangingInterface getWallHanging() {
        return new HalloweenWallHangingProvider();
    }

    @Override
    public YardOrnamentInterface getYardOrnament() {
        return new HalloweenYardOrnamentProvider();
    }
}
