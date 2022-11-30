package org.example.decorations.christmas;

import org.example.decorations.interfaces.TableclothPatternInterface;

public class ChristmasTableclothProvider implements TableclothPatternInterface {
    @Override
    public String getTablecloth() {
        return "christmas trees and reindeer";
    }
}
