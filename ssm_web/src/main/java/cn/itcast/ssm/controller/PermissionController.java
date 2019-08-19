package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = service.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        service.save(permission);
        return "redirect:findAll.do";
    }
}
