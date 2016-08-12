package org.automation.jprotector.scripts;

/**
 * Interface that all scripts must implement.
 * @author Carlos Alexandro Becker (caarlos0@gmail.com)
 */
public interface Script {
    /**
     * Script content.
     * @return Contents of this script.
     */
    String content();
}