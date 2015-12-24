package com.example;

import java.util.Random;

public class JokeFactory {

    private String[] mJokes = {
            "joke: A baby seal walks into a club...",
            "joke: A jumper cable walks into a bar, the bartender says 'I will serve you, but don't start anything!'.",
            "joke: What do you do with a sick boat? TAKE IT TO THE DOC!",
            "joke: A neutron walks into a bar and asks \"how much for a beer?\" The bartender says, \"for you? no charge.\"",
            "joke: How does the man in the moon cut his hair? ECLIPSE IT!"
    };

    public String getJoke() {
        int randomIndex = new Random().nextInt(mJokes.length);
        return mJokes[randomIndex];
    }

}
