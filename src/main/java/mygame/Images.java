package mygame;

import java.io.InputStream;
import java.util.Random;

/**
 * Created by SiXFOiL on 22.06.2017.
 */

public class Images {

    private String path = "/images/";

    public InputStream getRandomMexican() {
        int index = new Random().nextInt(4) + 1;
        String name = path + "Mexicano" + index + ".png";
        return getUrl(name);
    }

    public InputStream getTrumpWithBombImage() {
        String name = path + "trumpBomb.png";
        return getUrl(name);
    }

    public InputStream getTrumpNoBombImage() {
        String name = path + "trump.png";
        return getUrl(name);
    }

    public InputStream getBombImage() {
        String name = path + "tomato.png";
        return getUrl(name);
    }

    public InputStream getBoomImage() {
        String name = path + "boom.png";
        return getUrl(name);
    }

    public InputStream getUrl(String path) {
        InputStream stream = this.getClass().getResourceAsStream(path);
        return stream;
    }
}
