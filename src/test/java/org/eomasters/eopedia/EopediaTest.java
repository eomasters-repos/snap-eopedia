package org.eomasters.eopedia;

import junit.framework.TestCase;

import java.io.IOException;

public class EopediaTest extends TestCase {

    public void testSearch() throws IOException {
        final Eopedia eopedia = new EopediaBuilder().setUseHttp(true).createEopedia();
        final SearchResult result = eopedia.search("Landsat");

        System.out.println(result.toString());
    }

    public void testOpenPage() {
    }
}