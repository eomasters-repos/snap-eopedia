package org.eomasters.eopedia;

import com.google.gson.Gson;
import org.esa.snap.rcp.SnapApp;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eopedia {
    private static Logger LOGGER = SnapApp.getDefault().getLogger();

    private final String searchUrl;
    private final String openUrl;


    public Eopedia(String searchUrl, final String openUrl) {
        this.searchUrl = searchUrl;
        this.openUrl = openUrl;
    }

    public SearchResult search(String searchTerm) throws IOException {
        final String searchUrlString = String.format(searchUrl, searchTerm);
        final URL url = new URL(searchUrlString);
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            return gson.fromJson(br, SearchResult.class);
        }
    }

    public boolean openPage(String pageTitel) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(String.format(openUrl, makePageTitleValid(pageTitel))));
                return true;
            } catch (Throwable t) {
                LOGGER.log(Level.SEVERE, String.format("Not able to open URL '%s' ", openUrl), t);
            }
        }
        return false;


    }

    private String makePageTitleValid(final String pageTitel) {
        return pageTitel.replace(' ', '_');
    }
}
