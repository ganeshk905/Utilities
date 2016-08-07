package org.automation.jprotector.scripts;

import java.io.IOException;
import java.io.InputStream;

/**
 * Loads client side scripts from classpath.
 */
final class Loader {
    /**
     * File name in classpath.
     */
    private final transient String filename;

    /**
     * Ctor.
     * @param file File name in classpath.
     */
    Loader(final String file) {
        this.filename = file + ".js";
    }

    /**
     * Get the contents of the given file.
     * @return File contents.
     */
    String content() {
        try (
            final InputStream stream = Loader.class
                .getClassLoader().getResourceAsStream(this.filename)
        ) {
            if (stream == null) {
                throw new ScriptLoadException(this.filename);
            }
            final byte[] bytes = new byte[stream.available()];
            stream.read(bytes);
            return new String(bytes, "UTF-8");
        } catch (final IOException err) {
            throw new ScriptLoadException(err, this.filename);
        }
    }
}
