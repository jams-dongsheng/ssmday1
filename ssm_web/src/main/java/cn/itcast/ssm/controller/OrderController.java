package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
   @Autowired
   private IOderService service;

   @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page, @RequestParam(name = "size",required = true,defaultValue = "4") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
       List<Orders> ordersList = service.findAll(page,size);
       //设置分页bean
       PageInfo pageInfo = new PageInfo(ordersList);
       mv.addObject("pageInfo",pageInfo);
       mv.setViewName("orders-list");
       return mv;
   }
   @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
       ModelAndView mv = new ModelAndView();
       Orders orders = service.findById(id);
       mv.addObject("orders",orders);
       mv.setViewName("orders-show");
       return mv;
   }
}
