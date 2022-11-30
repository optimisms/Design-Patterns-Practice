package org.example.decorations.christmas;

import org.example.decorations.interfaces.YardOrnamentInterface;

public class ChristmasYardOrnamentProvider implements YardOrnamentInterface {
    @Override
    public String getOrnament() {
        return "snowman";
    }
}
