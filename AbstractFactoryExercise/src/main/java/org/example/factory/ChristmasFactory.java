package org.example.factory;

import org.example.decorations.christmas.ChristmasTableclothProvider;
import org.example.decorations.christmas.ChristmasWallHangingProvider;
import org.example.decorations.christmas.ChristmasYardOrnamentProvider;
import org.example.decorations.interfaces.TableclothPatternInterface;
import org.example.decorations.interfaces.WallHangingInterface;
import org.example.decorations.interfaces.YardOrnamentInterface;

public class ChristmasFactory implements DecorationFactoryInterface {
    @Override
    public TableclothPatternInterface getTablecloth() {
        return new ChristmasTableclothProvider();
    }

    @Override
    public WallHangingInterface getWallHanging() {
        return new ChristmasWallHangingProvider();
    }

    @Override
    public YardOrnamentInterface getYardOrnament() {
        return new ChristmasYardOrnamentProvider();
    }
}
