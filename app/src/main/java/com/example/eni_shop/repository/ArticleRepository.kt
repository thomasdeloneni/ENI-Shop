package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDao
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType

object ArticleRepository
{
    private val articleDao: ArticleDao = DaoFactory.createArticleDao(DaoType.MEMORY)


    fun getArticleById(id: Long) : Article? {
        return articleDao.findById(id)
    }

    fun addArticle(article: Article) :Long {
        return articleDao.insert(article)
    }

    fun getAllArticles() : List<Article> {
        return articleDao.findAll()
    }
}