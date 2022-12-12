package org.example.decorators;

import org.example.sources.StringSource;

public class ExcitingDecorator extends Decorator{
    public ExcitingDecorator(StringSource source) {
        super(source);
    }

    @Override
    public String next() {
        String original = source.next();
        return original.substring(0, original.length() - 1) + "!";
    }
}
