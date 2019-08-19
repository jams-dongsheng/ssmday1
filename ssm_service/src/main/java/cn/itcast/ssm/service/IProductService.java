package cn.itcast.ssm.service;


import cn.itcast.ssm.domain.Product;

import java.util.List;

public interface IProductService {
    //查询
    public List<Product> findAll()throws Exception;
    //添加
    public void save(Product product)throws Exception;
}
