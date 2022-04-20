package com.site.blog.controllers;

import com.site.blog.models.Page;
import com.site.blog.repositories.PageRepository;
import com.site.blog.util.RandomIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Executable;

@Controller
public class PageController {

    Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private RandomIdGenerator randomIdGenerator;

    @GetMapping("/")
    public String main(Model model){
        return "home"; //view name (home.html)
    }

    @GetMapping("/posts")
    public String posts(Model model){
        String view = "posts";
        try {
            model.addAttribute("posts", pageRepository.findAll());
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }
        return view;
    }

    @GetMapping("/posts/{postId}")
    public String post(@PathVariable(value="postId") String postId, Model model){
        String view = "post";
        try {
            Page post = pageRepository.findByPostId(postId);
            model.addAttribute("post", post);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }
        return view;
    }

    @GetMapping("/posts/add")
    public String addPost(Model model){
        String view = "add";
        try {
            model.addAttribute("page", new Page());
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }

        return view;
    }

    @PostMapping(path="/edit/add",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String add(@ModelAttribute Page page, Model model){
        String view = "redirect:/posts/";
        try {
            page.setPostId(randomIdGenerator.generateUUID());
            Page savedPage = pageRepository.save(page);
            model.addAttribute("post", savedPage);
            view += savedPage.postId;
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }
        return view;
    }

    @PostMapping(path="/edit/update",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(Page page){
        try {

        } catch (Exception e){

        }
        return null;
    }
}
