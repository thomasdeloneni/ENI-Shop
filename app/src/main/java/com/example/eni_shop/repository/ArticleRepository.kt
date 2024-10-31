package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType

class ArticleRepository(
    private val articleDaoRoomImpl : ArticleDAO,
    private val articleDAOMemoryImpl : ArticleDAO
) {


    //-------------MEMORY
    fun getArticle(id : Long, type: DaoType = DaoType.MEMORY) : Article? {
        return when(type) {
            DaoType.MEMORY -> articleDAOMemoryImpl.findById(id)
            else -> articleDaoRoomImpl.findById(id)
        }
    }

    fun getAllArticles(type: DaoType = DaoType.MEMORY) : List<Article>{
        return when(type) {
            DaoType.MEMORY -> articleDAOMemoryImpl.findAll()
            else -> articleDaoRoomImpl.findAll()
        }
    }

    fun addArticle(article : Article, type: DaoType = DaoType.MEMORY) : Long{
        return when(type) {
            DaoType.MEMORY -> articleDAOMemoryImpl.insert(article)
            else -> articleDaoRoomImpl.insert(article)
        }
    }

    //--------------ROOM
//    fun addArticleFav(article : Article) : Long{
//        return articleDaoRoomImpl.insert(article)
//    }
//
//    fun getArticleFav(id: Long) : Article? {
//        return articleDaoRoomImpl.findById(id)
//    }
//
    fun deleteArticle(article: Article, type: DaoType = DaoType.MEMORY) {
        when(type) {
            DaoType.MEMORY -> articleDAOMemoryImpl.delete(article)
            else -> articleDaoRoomImpl.delete(article)
        }
    }



}