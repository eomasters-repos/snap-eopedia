/*-
 * ========================LICENSE_START=================================
 * EOpedia Search Plugin - Allows to search the EOpedia Wiki. This plugin is brought to you by EOMasters
 * -> https://www.eopedia.org
 * ======================================================================
 * Copyright (C) 2022 - 2025 Marco Peters
 * ======================================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * -> http://www.gnu.org/licenses/gpl-3.0.html
 * =========================LICENSE_END==================================
 */

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
