package org.example;

import org.example.decorators.*;
import org.example.sources.EvermoreLyricsSource;
import org.example.sources.FolkloreLyricsSource;

public class Main {
    public static void main(String[] args) {
        Decorator excited = new ExcitingDecorator(new FolkloreLyricsSource());
        Decorator hesitant = new HesitantDecorator(new EvermoreLyricsSource());
        Decorator caps = new RandomCapsDecorator(new FolkloreLyricsSource());
        Decorator password = new PasswordDecorator(new EvermoreLyricsSource());

        System.out.println(excited.next());
        System.out.println(hesitant.next());
        System.out.println(caps.next());
        System.out.println(password.next());

        Decorator excitedHesitant = new ExcitingDecorator(new HesitantDecorator(new FolkloreLyricsSource()));
        System.out.println(excitedHesitant.next());

        Decorator capsExcited = new RandomCapsDecorator(excited);
        Decorator passwordCapsExcited = new PasswordDecorator(capsExcited);
        Decorator hesitantPasswordCapsExcited = new HesitantDecorator(passwordCapsExcited);

        System.out.println(hesitantPasswordCapsExcited.next());

    }
}