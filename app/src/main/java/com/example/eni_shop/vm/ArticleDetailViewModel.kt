package com.example.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleDetailViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _article: MutableStateFlow<Article?> = MutableStateFlow(null)
    val article: StateFlow<Article?> = _article

    fun getArticleById(id: Long) {
        _article.value = articleRepository.getArticle(id)
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                //val application = checkNotNull(extras[APPLICATION_KEY])

                return ArticleDetailViewModel(ArticleRepository()) as T
            }
        }
    }
}