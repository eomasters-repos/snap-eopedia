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

import org.esa.snap.runtime.Config;

import java.util.prefs.Preferences;

public class EopediaBuilder {
    // Example search URL
    // http://192.168.178.36/mediawiki/index.php?search=planet&title=Special%3ASearch&fulltext=Search

    // http://192.168.178.36/mediawiki/api.php?action=opensearch&format=json&search=planet

    private final static Preferences PREFERENCES = Config.instance().load().preferences();
    private static final String DEFAULT_EOPEDIA_URL = "www.eopedia.org";
    private static final String FORMATTED_SEARCH_STRING = "%s%s/api.php?action=query&list=search&format=%s&srsearch=";
    //    private static final String FORMATTED_SEARCH_STRING = "/api.php?action=opensearch&format=json&search=%s";
    private static final String FORMATTED_OPEN_STRING = "%s%s/index.php?title=";
    private static final String FORMAT_JSON = "json";
    private static final String PROTOCOL_HTTP = "http://";
    private static final String PROTOCOL_HTTPS = "https://";


    private String baseUrl = PREFERENCES.get("eopedia.url", DEFAULT_EOPEDIA_URL);
    private boolean useHttp = PREFERENCES.getBoolean("eopedia.useHttp", false);


    public EopediaBuilder setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public EopediaBuilder setUseHttp(final boolean useHttp) {
        this.useHttp = useHttp;
        return this;
    }

    public Eopedia createEopedia() {
        final String protocol = useHttp ? PROTOCOL_HTTP : PROTOCOL_HTTPS;
        final String searchUrl = String.format(FORMATTED_SEARCH_STRING, protocol, baseUrl, FORMAT_JSON) + "%s";
        final String openUrl = String.format(FORMATTED_OPEN_STRING, protocol, baseUrl) + "%s";
        return new Eopedia(searchUrl, openUrl);
    }
}
