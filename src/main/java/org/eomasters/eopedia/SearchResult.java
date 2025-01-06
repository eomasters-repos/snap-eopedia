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
