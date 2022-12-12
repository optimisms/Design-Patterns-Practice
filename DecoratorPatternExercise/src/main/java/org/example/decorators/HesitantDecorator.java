package org.example.decorators;

import org.example.sources.StringSource;

public class HesitantDecorator extends Decorator {
    public HesitantDecorator(StringSource source) {
        super(source);
    }

    @Override
    public String next() {
        String original = source.next();
        return original.replace(" ", "...");
    }
}
