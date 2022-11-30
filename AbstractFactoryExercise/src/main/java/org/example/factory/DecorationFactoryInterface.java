package org.example.factory;

import org.example.decorations.interfaces.TableclothPatternInterface;
import org.example.decorations.interfaces.WallHangingInterface;
import org.example.decorations.interfaces.YardOrnamentInterface;

public interface DecorationFactoryInterface {
    TableclothPatternInterface getTablecloth();
    WallHangingInterface getWallHanging();
    YardOrnamentInterface getYardOrnament();
}
