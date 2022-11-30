package org.example.decorations.halloween;

import org.example.decorations.interfaces.WallHangingInterface;

public class HalloweenWallHangingProvider implements WallHangingInterface {
    public String getHanging() {
        return "spider-web";
    }
}
