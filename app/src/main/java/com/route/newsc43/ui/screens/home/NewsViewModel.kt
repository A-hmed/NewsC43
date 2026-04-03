package com.route.newsc43.ui.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsc43.data.api.ApiManager
import com.route.newsc43.data.api.model.ArticleDM
import com.route.newsc43.data.api.model.SourceDM
import com.route.newsc43.domain.model.Source
import com.route.newsc43.domain.usecase.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(val getSourcesUseCase: GetSourcesUseCase) : ViewModel() {

    //View -> ViewModel -> UseCase -> Repo -> DataSource {

    var tabs: MutableLiveData<List<Source>?> = MutableLiveData(null)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoadingArticles: MutableLiveData<Boolean> = MutableLiveData(false)
    var errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    var articlesErrorMessage: MutableLiveData<String?> = MutableLiveData(null)

    var articles: MutableLiveData<List<ArticleDM>?> = MutableLiveData(null)


    fun getSources(category: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                tabs.value = getSourcesUseCase.execute(category = category)
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