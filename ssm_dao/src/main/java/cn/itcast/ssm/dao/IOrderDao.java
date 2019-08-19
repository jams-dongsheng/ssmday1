package cn.itcast.ssm.dao;

import cn.itcast.ssm.domain.Member;
import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {
    @Select("select * from orders")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property= "orderNum",column  = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = "cn.itcast.ssm.dao.IProduct.findById")
            )
    })
    public List<Orders> findAll()throws Exception;
    @Select("select * from orders where id=#{id}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property= "orderNum",column  = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select = "cn.itcast.ssm.dao.IProduct.findById")
            ),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "cn.itcast.ssm.dao.IMemberDao.findById")
            ),
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "cn.itcast.ssm.dao.ITravellerDao.findById")
            )
    })
    public Orders findById(String id) throws Exception;
}
