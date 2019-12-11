package com.mecofarid.newsapi.model.request

import kotlin.collections.ArrayList

data class HeadlinesRequest(
    private val mCountry: String,
    private val mCategory: String,
    private val mSources: String,
    private val mSearchPhrase: String,
    private val mPageSize: Int,
    private val mPage: Int
){
    data class Builder constructor(
        private var country: String,
        private var category: String,
        private val sources: ArrayList<String>,
        private var searchPhrase: String,
        private var pageSize: Int,
        private var page: Int
    ){
        fun setCountry(country: String)        = this.apply { this.country = country }
        fun setCategory(category: String)      = this.apply { this.category = category }
        fun setSources(vararg sources: String) = this.sources.addAll(sources.toList())
        fun setSearchPhrase(phrase: String)    = this.apply { this.searchPhrase = phrase }
        fun setPageSize(pageSize: Int)         = this.apply { this.pageSize = pageSize }
        fun setPage(page: Int)                 = this.apply { this.page = page }

        fun build(): HeadlinesRequest{
            return HeadlinesRequest(
                mCountry      = country,
                mCategory     = category,
                mSources      = sources.joinToString(),
                mSearchPhrase = searchPhrase,
                mPageSize     = pageSize,
                mPage         = page
            )
        }
    }
}