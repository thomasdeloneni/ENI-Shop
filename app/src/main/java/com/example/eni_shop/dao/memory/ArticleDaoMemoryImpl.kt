package com.example.eni_shop.dao.memory

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDao
import java.util.Date

class ArticleDaoMemoryImpl : ArticleDao{

    private val articlesInMemory : MutableList<Article> = mutableListOf(
        Article(1, "Processeur", "Description 1", 10.0, "url1", "Ordinateur de Bureau", Date()),
        Article(2, "Ecran", "Description 2", 20.0, "url2", "Ordinateur de Bureau", Date()),
        Article(3, "Unité centrale", "Description 3", 30.0, "url3", "Ordinateur de Bureau", Date())
    )

    override fun findById(id: Long): Article? {
        return articlesInMemory.find { it.id == id }
    }

    override fun insert(article: Article): Long {
        article.id = articlesInMemory.size.toLong() + 1
        articlesInMemory.add(article)
        return article.id
    }

    override fun findAll(): List<Article> {
        return articlesInMemory
    }
}