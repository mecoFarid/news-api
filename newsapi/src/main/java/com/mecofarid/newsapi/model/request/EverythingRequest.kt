package com.mecofarid.newsapi.model.request

import com.mecofarid.newsapi.toNewsApiDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class EverythingRequest(
    private val mSearchPhrase: String,
    private val mSearchPhraseInTitle: String,
    private val mSources: String,
    private val mDomains: String,
    private val mExcludeDomains: String,
    private val mFromDate: String,
    private val mToDate: String,
    private val mLanguage: String,
    private val mSortBy: String,
    private val mPageSize: Int,
    private val mPage: Int
){
    data class Builder constructor(
        private var searchPhrase: String,
        private var searchPhraseInTitle: String,
        private val sources: ArrayList<String> = arrayListOf(),
        private val domains: ArrayList<String> = arrayListOf(),
        private val excludeDomains: ArrayList<String> = arrayListOf(),
        private var fromDate: Date,
        private var toDate: Date,
        private var language: String,
        private var sortBy: String,
        private var pageSize: Int,
        private var page: Int
    ){
        fun setSearchPhrase(phrase: String)                  = this.apply { this.searchPhrase = phrase }
        fun setTitleSearchPhrase(titlePhrase: String)        = this.apply { this.searchPhraseInTitle = titlePhrase }
        fun setSources(vararg sources: String)               = this.sources.addAll(sources.asList())
        fun setDomains(vararg domains: String)               = this.domains.addAll(domains.asList())
        fun setExcludeDomains(vararg excludeDomains: String) = this.excludeDomains.addAll(excludeDomains.asList())
        fun setFromDate(date: Date)                          = this.apply { this.fromDate = date}
        fun setToDate(date: Date)                            = this.apply { this.toDate = date }
        fun setLanguage(language: String)                    = this.apply { this.language = language }
        fun sortBy(sortBy: String)                           = this.apply { this.sortBy = sortBy }
        fun setPageSize(pageSize: Int)                       = this.apply { this.pageSize = pageSize }
        fun setPage(page: Int)                               = this.apply { this.page = page }

        fun build(): EverythingRequest{
            return EverythingRequest(
                mSearchPhrase        = searchPhrase,
                mSearchPhraseInTitle = searchPhraseInTitle,
                mSources             = sources.joinToString(),
                mDomains             = domains.joinToString(),
                mExcludeDomains      = excludeDomains.joinToString(),
                mFromDate            = fromDate.toNewsApiDateFormat(),
                mToDate              = toDate.toNewsApiDateFormat(),
                mLanguage            = language,
                mSortBy              = sortBy,
                mPageSize            = pageSize,
                mPage                = page
            )
        }
    }
}