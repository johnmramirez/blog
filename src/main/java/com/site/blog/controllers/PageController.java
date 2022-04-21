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
import java.util.Optional;

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

    @GetMapping(value = {
            "/posts",
            "/posts/all"
    })
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

    @GetMapping("/posts/view/{id}")
    public String post(@PathVariable(value="id") String id, Model model){
        String view = "post";
        try {
            Optional<Page> post = pageRepository.findById(id);
            model.addAttribute("post", post.get());
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }
        return view;
    }

    @GetMapping(value = {
            "/posts/update/",
            "/posts/update/{id}"
    })
    public String add(@PathVariable(value="id", required = false) String id, Model model){
        String view = "add";
        System.out.println("Post ID = " + id);
        try {
            if(id != null && !id.isEmpty()){
                Optional<Page> post = pageRepository.findById(id);
                if(post.isPresent()){
                    model.addAttribute("post", post.get());
                } else {
                    throw new Exception("No post with id '" + id + "' exists.");
                }
            } else {
                model.addAttribute("post", new Page());
            }
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }

        return view;
    }

    @PostMapping(path="/edit/update",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String add(@ModelAttribute Page page, Model model){
        String view = "redirect:/posts/view/";
        try {
            //form validation is required
            Page savedPage = pageRepository.save(page);
            System.out.println("Saved Page: " + savedPage);
            model.addAttribute("post", savedPage);
            view += savedPage.getId();
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            view = "redirect:/error";
        }
        return view;
    }

}
