package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.dao.IProduct;
import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private  IProduct dao;
    @Override
    public List<Product> findAll()throws Exception {
       return dao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        dao.save(product);
    }

}
