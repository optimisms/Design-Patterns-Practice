package org.example.decorators;

import org.example.sources.StringSource;

public abstract class Decorator implements StringSource {
    protected final StringSource source;

    public Decorator(StringSource source) {
        this.source = source;
    }
}
