package com.example.demo.services;
import com.example.demo.dto.ArticleDto;
import com.example.demo.dto.ArticleForm;
import com.example.demo.models.Article;
import com.example.demo.models.User;
import com.example.demo.repositores.ArticleRepository;
import com.example.demo.repositores.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private ArticleRepository articleRepository;

    public ArticleServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<ArticleDto> getByUser(Long userId) {
        User user = usersRepository.getOne(userId);
        List<Article> articlesOfUser = user.getCreateArticles();
        return ArticleDto.articleList(articlesOfUser);
    }

    @Override
    public ArticleDto addArticle(Long userId, ArticleForm articleForm) {
        User author = usersRepository.getOne(userId);

        Article newArticle = Article.builder()
                .author(author)
                .text(articleForm.getText())
                .type(articleForm.getType())
                .build();

        articleRepository.save(newArticle);
        return ArticleDto.from(newArticle);
    }
    @Override
    public ArticleDto like(Long userId, Long articleId ){
        User user = usersRepository.getOne(userId);// action num 3
        Article article = articleRepository.getOne(articleId);//action num 3 , coming back from the sql to the backend and send it to the front.
        if (articleRepository.existsByArticleIdAndLikeContaining(articleId,user)){
            article.getLike().remove(user);

        }else {
            article.getLike().add(user);
        }
        articleRepository.save(article);//action num 4 bring from the front to the front.
        return ArticleDto.from(article);

    }
    public static ArticleDto from(Article article) {
        Integer likeCount = 0;
        if (article.getLike() != null) {
            likeCount = article.getLike().size();
        }
        return ArticleDto.builder()
                .id(article.getArticleId())
                .text(article.getText())
                .authorName(article.getAuthor().getEmail())
                .likesCount(likeCount)
                .build();
    }
}