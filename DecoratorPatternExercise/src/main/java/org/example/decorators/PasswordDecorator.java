package org.example.decorators;

import org.example.sources.StringSource;

public class PasswordDecorator extends Decorator {
    public PasswordDecorator(StringSource source) {
        super(source);
    }

    @Override
    public String next() {
        String original = source.next();
        original = original.replace("o", "0");
        original = original.replace("l", "1");
        original = original.replace("E", "3");
        original = original.replace("s", "5");
        original = original.replace("B", "8");

        return original;
    }
}
