package org.example.sources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EvermoreLyricsSource implements StringSource {
    private final List<String> lyrics = new ArrayList<>(
            Arrays.asList("I use my best colors for your portrait.",
                    "I can't stop you putting roots in my dreamland.",
                    "I haven't met the new me yet.",
                    "You had a speech; you're speechless.",
                    "Never be so kind you forget to be clever. Never be so clever you forget to be kind.",
                    "If the shoe fits walk in it everywhere you go.",
                    "One for the money, two for the show, I never was ready so I watch you go.",
                    "Seeing the shape of your name still spells out pain.",
                    "My mind turns your life into folklore.",
                    "I know my love should be celebrated, but you tolerate it.",
                    "I was catching my breath, barefoot in the wildest winter/Catching my death.",
                    "I don't need your closure.",
                    "Long story short I survived.",
                    "I can feel you smoothing me over."));

    public EvermoreLyricsSource() {}

    @Override
    public String next() {
        int index = new Random().nextInt(lyrics.size());
        return lyrics.get(index);
    }
}
