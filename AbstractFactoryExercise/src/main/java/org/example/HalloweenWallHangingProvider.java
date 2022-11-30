package org.example;

import org.example.interfaces.WallHangingInterface;

public class HalloweenWallHangingProvider implements WallHangingInterface {
    public String getHanging() {
        return "spider-web";
    }
}
