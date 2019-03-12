package com.itheima.controller;

import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<Items> all = itemsService.findAll();
        modelAndView.addObject("itemsList", all);
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }

    @RequestMapping("/editItems")
    public ModelAndView editItems(String id) {

        ModelAndView modelAndView = new ModelAndView();
        Items byId = itemsService.findById(id);
        modelAndView.addObject("items", byId);
        modelAndView.setViewName("editItems");
        return modelAndView;

    }

    @RequestMapping("/updateItems")
    public String updateItems(Integer id, String name, Float price, String createtime, String detail, HttpServletRequest request, MultipartFile upload) throws IOException, ParseException {
        String path = request.getSession().getServletContext().getRealPath("/pic");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if(file.exists()){
            file.delete();
            file.mkdirs();
        }
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        filename = uuid + "_" + filename;
        upload.transferTo(new File(file, filename));
        Items items = new Items();
        items.setId(id);
        items.setName(name);
        items.setPrice(price);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(createtime);
        items.setCreatetime(parse);
        items.setDetail(detail);
        items.setPic(filename);
        System.out.println(items);
        itemsService.updateItems(items);

        return "redirect:findAll";
    }
}
