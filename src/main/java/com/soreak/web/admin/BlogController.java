package com.soreak.web.admin;


import com.soreak.dao.BlogRepository;
import com.soreak.pojo.Blog;
import com.soreak.pojo.User;
import com.soreak.service.BlogService;
import com.soreak.service.TagService;
import com.soreak.service.TypeService;
import com.soreak.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/blogs")
    public String list(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                       BlogQuery blog, Model model)
    {
        blog.setTitle("");
        System.out.println("blog == >" +blog);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }


    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                       BlogQuery blog, Model model)
    {
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){

        setTypeAndTag(model);

        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types",typeService.listType());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){

        setTypeAndTag(model);
        Blog blog= blogService.getBlog(id);
        blog.init();

        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        if ("".equals(blog.getFlag())){
            blog.setFlag("原创");
        }
        Blog b;
        if (blog.getId() == null){
            b =blogService.saveBlog(blog);
        }else {
            b =blogService.updateBlog(blog.getId(),blog);
        }

        if (b == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT;
    }



    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT;
    }

}
