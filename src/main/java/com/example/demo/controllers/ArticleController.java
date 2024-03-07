package com.example.demo.controllers;

import com.example.demo.dto.ArticleForm;
import com.example.demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/users/{user-id}/article")
    @ResponseBody
    public String addArticle(@PathVariable("user-id") Long id,
                             @RequestBody ArticleForm articleForm) {
         articleService.addArticle(id, articleForm);
         return  "redirect:/users/{user-id}/article";
    }
    @GetMapping("/users/{user-id}/article")
    public String getArticlesOfUser(@PathVariable("user-id") Long id, Model model) {
        model.addAttribute("user_id",id);
        model.addAttribute("articles", articleService.getByUser(id));
        return "article_page";
    }
    @PostMapping("/user/{user-id}/article/{article-id}/like")
    @ResponseBody
    public Object like(@PathVariable("user-id")Long id,
                        @PathVariable("article-id")Long articleId){
        articleService.like(id, articleId);
        // Redirect to the sign-up page
        return new RedirectView("/signUpPage", true);
    }
}
