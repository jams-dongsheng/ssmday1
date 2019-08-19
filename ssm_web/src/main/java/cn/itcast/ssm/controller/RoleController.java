package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.domain.Role;
import cn.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService service;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = service.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        service.save(role);
        return "redirect:findAll.do";
    }
   /* @RequestMapping("/findById.do")
    @ResponseBody
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv  = new ModelAndView();
        Role role = service.findById(roleId);
        mv.addObject("role",role);
        return mv;
    }*/
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = service.findById(roleId);
        List<Permission> permissionList = service.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addRoleToPermission.do")
    public String addRoleToPermission(String roleId,@RequestParam(name = "ids",required = true) String[] permissionIds){
        service.addRoleToPermission(roleId,permissionIds);
        return "redirect:/user/findAll.do";
    }
}
