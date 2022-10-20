package com.reljicd.controller;

import com.reljicd.model.Comment;
import com.reljicd.model.Post;
import com.reljicd.model.ReferenciaLink;
import com.reljicd.model.User;
import com.reljicd.service.PostService;
import com.reljicd.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class DirectPasseiController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public DirectPasseiController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/registration2", method = RequestMethod.POST)
    public String newPost(Principal principal,
                          Model model,
                          ReferenciaLink referenciaLink) throws IOException {


       //"https://www.passeidireto.com/arquivo/51280157/relatorio-de-estagio-em-alimentos"
        Elements elements = Jsoup.connect(referenciaLink.getLink()).get().getElementsByTag("pre");
        String result = elements.get(0).outerHtml();

        if (!StringUtils.isEmpty(result)) {

            model.addAttribute("questoes", result);
            return "/registration2";
        } else {
            return "/post2";
        }
    }



    @RequestMapping(value = "/post2", method = RequestMethod.GET)
    public String registration(ReferenciaLink referenciaLink,
                               Model model) {

        model.addAttribute("referenciaLink", new ReferenciaLink());
        return "/post2";
    }




    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
}
