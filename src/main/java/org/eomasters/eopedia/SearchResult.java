package org.eomasters.eopedia;

import java.util.List;

public class SearchResult {
//    String batchcomplete;
    Query query;

    class Query {
        Searchinfo searchinfo;
        List<SearchHits> search;
    }

    class Searchinfo {
        int totalhits;
    }

    class SearchHits {
        int ns;
        String title;
        // "pageid": 10,
        // "size": 2268,
        // "wordcount": 274,
        String snippet;
        // "timestamp": "2022-08-25T07:54:12Z"

    }
}
