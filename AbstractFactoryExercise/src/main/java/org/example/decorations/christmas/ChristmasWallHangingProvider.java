package org.example.decorations.christmas;

import org.example.decorations.interfaces.WallHangingInterface;

public class ChristmasWallHangingProvider implements WallHangingInterface {
    @Override
    public String getHanging() {
        return "giant snowflake";
    }
}
