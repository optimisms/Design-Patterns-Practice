package org.example.decorators;

import org.example.sources.StringSource;

import java.util.Random;

public class RandomCapsDecorator extends Decorator{
    public RandomCapsDecorator(StringSource source) {
        super(source);
    }

    @Override
    public String next() {
        String modified = source.next();
        for (int i = 0; i < modified.length(); i++) {
            if (new Random().nextInt(2) == 1) {
                char curr = modified.charAt(i);
                modified = modified.substring(0, i) + Character.toUpperCase(curr) + modified.substring(i + 1);
            }
        }
        return modified;
    }
}
