package com.itheima.dao;

import com.itheima.domain.Items;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ItemsDao {

    @Select("SELECT * FROM items")
    public List<Items> findAll111111111();

    @Select("SELECT * FROM items WHERE id=#{id}")
    public Items findById(String id);

    @Update("UPDATE items SET name=#{name},price=#{price},pic=#{pic},createtime=#{createtime},detail=#{detail} WHERE id=#{id}")
    public void updateItems(Items items);
}
