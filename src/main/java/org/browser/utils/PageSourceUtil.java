package org.browser.utils;

import automation.utils.UtilBase;
import org.omg.CORBA.portable.InputStream;
import org.openqa.selenium.WebDriver;

/**
 * Created by shantonu on 4/19/16.
 */
public class PageSourceUtil extends UtilBase{

    public PageSourceUtil(WebDriver aDriver) {
        super(aDriver);
    }

    /**
     * This will get all resources (css, image, font, javascript etc from driver source
     * @param resourceName
     * @return
     * todo
     */
    public InputStream getResourceAsStream(String resourceName){
        return null;
    }
}
