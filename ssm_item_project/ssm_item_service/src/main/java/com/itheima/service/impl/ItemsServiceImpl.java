package com.itheima.service.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;
import com.itheima.service.ItemsService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsDao itemsDao;

    @Override
    public List<Items> findAll() {
        List<Items> all = itemsDao.findAll();
        return all;
    }

    @Override
    public Items findById(String id) {
        Items byId = itemsDao.findById(id);
        return byId;
    }

    @Override
    public void updateItems(Items items) {
        itemsDao.updateItems(items);
    }
}
