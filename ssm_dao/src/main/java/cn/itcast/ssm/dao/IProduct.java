package cn.itcast.ssm.dao;


import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProduct {
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product)throws Exception;
    @Select("select * from product where id = #{id}")
    public Product findById(String id)throws Exception;
}
