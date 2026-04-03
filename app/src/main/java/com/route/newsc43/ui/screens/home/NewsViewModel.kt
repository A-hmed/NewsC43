package com.route.newsc43.ui.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc43.data.api.ApiManager
import com.route.newsc43.data.api.model.ArticleDM
import com.route.newsc43.data.api.model.SourceDM
import com.route.newsc43.data.repositories.news_repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    var newsRepository = NewsRepository()
    var tabs: MutableLiveData<List<SourceDM>?> = MutableLiveData(null)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadingArticles: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    var articlesErrorMessage: MutableLiveData<String?> = MutableLiveData(null)

    var articles: MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)


    fun getSources(category: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tabs.value = newsRepository.getSources(category = category)
                isLoading.value = false
            } catch (t: Throwable) {
                isLoading.value = false
                Log.e("getSources - onFailure", "code = ${t.message}")
                errorMessage.value = t.message ?: "Something went please try again later"
            }
        }
    }

    fun getArticles(source: String) {
        viewModelScope.launch {
            try {
                isLoadingArticles.value = true
                var articleResponse = ApiManager.getWebServices().getArticles(source = source)
                isLoadingArticles.value = false
                articles.value = articleResponse.articles
            } catch (t: Throwable) {
                Log.e("getArticles - onFailure", "body = ${t}")
                isLoadingArticles.value = false
                articlesErrorMessage.value =
                    t.message ?: "Something went wrong please try again later"
            }

        }
    }
}