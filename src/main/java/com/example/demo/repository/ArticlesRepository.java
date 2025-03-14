package com.example.demo.repository;

import com.example.demo.model.article.Article;
import com.example.demo.model.article.ArticleId;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a")
    @NotNull
    List<Article> findAll();

    @Query("select a from Article a where a.id = :articleId")
    @NotNull
    Optional<Article> findById(ArticleId articleId);

    @Modifying
    @Query("delete from Article a where a.id = :articleId")
    void deleteArticlesById(Long articleId);

    @Modifying
    @Query("update Article a set a.title = :title where a.id = :articleId")
    void updateArticleTitle(Long articleId, String title);
}
