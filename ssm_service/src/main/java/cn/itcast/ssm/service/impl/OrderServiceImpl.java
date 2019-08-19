package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IOrderDao;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.service.IOderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOderService {
    @Autowired
    private IOrderDao dao;
    @Override
    public List<Orders> findAll(int page,int size) throws Exception{
        //设置分页
        PageHelper.startPage(page,size);
        return dao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return dao.findById(id);
    }
}
