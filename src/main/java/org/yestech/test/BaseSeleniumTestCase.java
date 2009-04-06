package org.yestech.test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 *
 *
 */
public class BaseSeleniumTestCase
{

    private static final Logger log = LoggerFactory.getLogger(BaseSeleniumTestCase.class);

    public static final String PROPERTY_BROWSER = "selenium.browser";
    public static final String PROPERTY_SELENIUM_SERVER = "selenium.rc.server";
    public static final String PROPERTY_SELENIUM_PORT = "selenium.rc.port";
    public static final String PROPERTY_URL = "selenium.url";

    public static final String DEFAULT_BROWSER = "*firefox";
    public static final String DEFAULT_SELENIUM_SERVER = "localhost";
    public static final String DEFAULT_SELENIUM_PORT = "4444";
    public static final String DEFAULT_SELENIUM_URL = "http://localhost:8080";

    private ResourceBundle properties;
    protected Selenium selenium;

    public BaseSeleniumTestCase()
    {
        properties = getResourceBundle();
    }

    @Before
    public void setup()
    {
        String server = getConfigProperty(PROPERTY_SELENIUM_SERVER, DEFAULT_SELENIUM_SERVER);
        int port = Integer.parseInt(getConfigProperty(PROPERTY_SELENIUM_PORT, DEFAULT_SELENIUM_PORT));
        String defaultBrowser = getConfigProperty(PROPERTY_BROWSER, DEFAULT_BROWSER);
        String url = getConfigProperty(PROPERTY_URL, DEFAULT_SELENIUM_URL);
        selenium = new DefaultSelenium(server, port, defaultBrowser, url);
    }

    protected Selenium getSelenium()
    {
        return selenium;
    }

    public ResourceBundle getResourceBundle() {
        try
        {
            return PropertyResourceBundle.getBundle("selenium");
        }
        catch (Exception e)
        {
            log.warn("No selenium.properties was found in the classpath");
            return null;
        }
    }

    public String getConfigProperty(String key, String defaultVal)
    {

        String value = null;
        if (properties != null)
        {
            try
        {
            value =  properties.getString(key);
            }
            catch (RuntimeException e)
            {
                if (log.isDebugEnabled())
                {
                    log.debug(e.getMessage(), e);
                }
                // ignore
            }
        }
        if (value == null) {
            value = defaultVal;
        }
        return value;
    }


}
