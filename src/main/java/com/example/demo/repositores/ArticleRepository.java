package com.example.demo.repositores;


import com.example.demo.models.Article;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByArticleIdAndLikeContaining(Long articleId , User user);


}