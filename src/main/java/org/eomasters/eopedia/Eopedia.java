package org.eomasters.eopedia;

import com.google.gson.Gson;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Eopedia {

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
                makePageTitleValid(pageTitel);
                Desktop.getDesktop().browse(new URI(String.format(openUrl, pageTitel)));
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }


    }

    private String makePageTitleValid(final String pageTitel) {
        return pageTitel.replace(' ', '_');
    }
}
