package org.example;

import org.example.interfaces.YardOrnamentInterface;

public class HalloweenYardOrnamentProvider implements YardOrnamentInterface {
    public String getOrnament() {
        return "jack-o-lantern";
    }
}
