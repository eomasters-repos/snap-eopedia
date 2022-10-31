package org.eomasters.eopedia;

import org.esa.snap.runtime.Config;

import java.awt.*;
import java.net.URI;
import java.util.prefs.Preferences;

public class Eopedia {

    // Example search URL
    // http://192.168.178.36/mediawiki/index.php?search=planet&title=Special%3ASearch&fulltext=Search

    // http://192.168.178.36/mediawiki/api.php?action=opensearch&format=json&search=planet
    private final static Preferences settings = Config.instance().load().preferences();
    private static final String FORMATTED_SEARCH_STRING = "/api.php?action=query&list=search&format=json&srsearch=%s";
//    private static final String FORMATTED_SEARCH_STRING = "/api.php?action=opensearch&format=json&search=%s";
    private static final String FORMATTED_OPEN_STRING = "/index.php?title=%s";
    private static final String DEFAULT_EOPEDIA_URL = "https://192.168.178.36/mediawiki";
    private final String searchUrl;
    private final String openUrl;

    public Eopedia() {
        final String baseUrl = settings.get("eopedia.url", DEFAULT_EOPEDIA_URL);
        searchUrl = baseUrl + FORMATTED_SEARCH_STRING;
        openUrl = baseUrl + FORMATTED_OPEN_STRING;
    }

    public void search(String searchTerm) {
        String.format(searchUrl, "searchTerm");


    }

    public boolean openPage(String pageTitel) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(String.format(openUrl, pageTitel)));
                return true;
            } catch (Throwable e) {
                return false;
            }
        } else {
            return false;
        }


    }
}
