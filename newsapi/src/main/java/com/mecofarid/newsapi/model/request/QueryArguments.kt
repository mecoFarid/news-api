package com.mecofarid.newsapi.model.request

class QueryArguments {
    class HeadlinesRequest{
        companion object{
            const val COUNTRY       = "country"
            const val CATEGORY      ="category"
            const val SOURCES       ="sources"
            const val SEARCH_PHRASE ="q"
            const val PAGE_SIZE     ="pageSize"
            const val PAGE          ="page"
        }
    }
    
    class EverythingRequest{
        companion object {
            const val SEARCH_PHRASE = "q"
            const val SEARCH_PHRASE_IN_TITLE = "qInTitle"
            const val SOURCES = "sources"
            const val DOMAINS = "domains"
            const val EXCLUDE_DOMAINS = "excludeDomains"
            const val FROM_DATE = "from"
            const val TO_DATE = "to"
            const val LANGUAGE = "language"
            const val SORT_BY = "sortBy"
            const val PAGE_SIZE = "pageSize"
            const val PAGE = "page"
        }
    }
}