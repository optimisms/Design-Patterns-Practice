package org.example.decorations.halloween;

import org.example.decorations.interfaces.YardOrnamentInterface;

public class HalloweenYardOrnamentProvider implements YardOrnamentInterface {
    public String getOrnament() {
        return "jack-o-lantern";
    }
}
