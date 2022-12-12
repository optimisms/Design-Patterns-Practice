package org.example.sources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FolkloreLyricsSource implements StringSource {
    private final List<String> lyrics = new ArrayList<>(
            Arrays.asList("So much for summer love and saying 'us', cause you weren't mine to lose.",
                    "I knew you'd linger like a tattoo kiss/I knew you'd haunt all of my what ifs.",
                    "They told me all of my cages were mental, so I got wasted like all my potential.",
                    "Don't want no other shade of blue but you/No other sadness in the world would do.",
                    "We gather stones never knowing what they'll mean/Some to throw, some to make a diamond ring.",
                    "You know the greatest films of all time were never made.",
                    "To live for the hope of it all, cancel plans just in case you'd call.",
                    "If I'm dead to you, why are you at the wake?",
                    "A friend to all is a friend to none.",
                    "Your faithless love's the only hoax I believe in.",
                    "You never gave a warning sign/I gave so many signs.",
                    "In my defense, I have none, for never leaving well enough alone.",
                    "I was so ahead of the curve, the curve became a sphere.",
                    "When you are young they assume you know nothing.",
                    "I had a marvelous time ruining everything.",
                    "You're not my homeland anymore, so what am I defending now?"));

    public FolkloreLyricsSource() {}

    @Override
    public String next() {
        int index = new Random().nextInt(lyrics.size());
        return lyrics.get(index);
    }
}
