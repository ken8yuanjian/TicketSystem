package com.ken.ticket.service;

import com.ken.common.entity.Film;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.dao.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService{

    @Autowired
    FilmDao filmDao;

    @Override
    public ResultBase<ArrayList<Film>> list(Film film) {
        return ResultBase.success(filmDao.list(film));
    }

    @Override
    public ResultBase< Film > byId(Integer id) {
        return ResultBase.success(filmDao.byId(id));
    }

    @Override
    public ResultBase<Integer> insert(Film film) {
        return ResultBase.success( filmDao.insert(film) );
    }
}
