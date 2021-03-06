package me.kingtux.lava;

import java.io.File;

/**
 * An easy to use Path builder.
 */
public class LavaPath {
    private StringBuilder activePath;

    public LavaPath() {

    }

    public LavaPath(String startPath) {
        activePath = new StringBuilder(startPath);
    }

    public LavaPath join(String s) {
        if (activePath.length() != 0) activePath.append(File.separator);
        activePath.append(s);
        return this;
    }

    @Override
    public String toString() {
        return activePath.toString();
    }

    public File toFile() {
        return new File(activePath.toString());
    }
}
