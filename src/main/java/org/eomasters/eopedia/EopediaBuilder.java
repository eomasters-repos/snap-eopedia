package org.eomasters.eopedia;

import org.esa.snap.runtime.Config;

import java.util.prefs.Preferences;

public class EopediaBuilder {
    // Example search URL
    // http://192.168.178.36/mediawiki/index.php?search=planet&title=Special%3ASearch&fulltext=Search

    // http://192.168.178.36/mediawiki/api.php?action=opensearch&format=json&search=planet

    private final static Preferences PREFERENCES = Config.instance().load().preferences();
    private static final String DEFAULT_EOPEDIA_URL = "192.168.178.36/mediawiki";
    private static final String FORMATTED_SEARCH_STRING = "%s%s/api.php?action=query&list=search&format=%s&srsearch=";
    //    private static final String FORMATTED_SEARCH_STRING = "/api.php?action=opensearch&format=json&search=%s";
    private static final String FORMATTED_OPEN_STRING = "/index.php?title=%s";
    private static final String FORMAT_JSON = "json";
    private static final String PROTOCOL_HTTP = "http://";
    private static final String PROTOCOL_HTTPS = "https://";


    private String baseUrl = PREFERENCES.get("eopedia.url", DEFAULT_EOPEDIA_URL);
    private boolean useHttp = PREFERENCES.getBoolean("eopedia.useHttp", false);
    private boolean prettyJSON = PREFERENCES.getBoolean("eopedia.prettyJson", false);;


    public EopediaBuilder setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public EopediaBuilder setUseHttp(final boolean useHttp) {
        this.useHttp = useHttp;
        return this;
    }

    public Eopedia createEopedia() {
        final String protocl = useHttp ? PROTOCOL_HTTP : PROTOCOL_HTTPS;
        final String searchUrl = String.format(FORMATTED_SEARCH_STRING, protocl, baseUrl, FORMAT_JSON) + "%s";
        return new Eopedia( searchUrl, baseUrl + FORMATTED_OPEN_STRING);
    }
}