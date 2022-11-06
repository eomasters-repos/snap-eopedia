package org.eomasters.eopedia;

import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;

import java.io.IOException;

// see https://netbeans.apache.org/tutorials/nbm-quick-search.html
public class EopediaSearchProvider implements SearchProvider {

    @Override
    public void evaluate(final SearchRequest searchRequest, final SearchResponse searchResponse) {
        final Eopedia eopedia = new EopediaBuilder().setUseHttp(true).createEopedia();
        try {
            final SearchResult sr = eopedia.search(searchRequest.getText());
            for (SearchResult.SearchHits search : sr.query.search) {
                searchResponse.addResult(new OpenEopediaArticle(eopedia, search.title), search.title);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}