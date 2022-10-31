package org.eomasters.eopedia;

public class OpenEopediaArticle implements Runnable {
    private final Eopedia eopedia;
    private final String pageTitle;

    public OpenEopediaArticle(final Eopedia eopedia, final String title) {
        this.eopedia = eopedia;
        pageTitle = title;
    }

    @Override
    public void run() {
        eopedia.openPage(pageTitle);
    }
}
