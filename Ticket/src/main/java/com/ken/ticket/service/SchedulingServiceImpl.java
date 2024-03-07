package com.ken.ticket.service;

import com.ken.common.entity.Film;
import com.ken.common.entity.Room;
import com.ken.common.entity.Scheduling;
import com.ken.common.entity.Ticket;
import com.ken.common.entity.http.ResultBase;
import com.ken.ticket.dao.FilmDao;
import com.ken.ticket.dao.RoomDao;
import com.ken.ticket.dao.SchedulingDao;
import com.ken.ticket.dao.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    SchedulingDao schedulingDao;
    @Autowired
    FilmDao filmDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    TicketDao ticketDao;

    @Override
    public ResultBase<ArrayList<Scheduling>> list(Scheduling scheduling) {
        return ResultBase.success(schedulingDao.list(scheduling));
    }

    @Override
    public ResultBase<Scheduling> byId(Integer id) {
        return ResultBase.success(schedulingDao.byId(id));
    }

    @Override
    @Transactional
    public ResultBase<Integer> insert(Scheduling scheduling) {
        if (null == scheduling || null == scheduling.getStart() || null == scheduling.getEnd() ||
        null == scheduling.getFilm() || 0 == scheduling.getFilm().getId() ||
        null == scheduling.getRoom() || 0 == scheduling.getRoom().getId()){
            return ResultBase.fail(1,"非法参数");
        }
        Film film = filmDao.byId(scheduling.getFilm().getId());
        if (film == null)
            return ResultBase.fail(1,"电影不存在");
        scheduling.setFilm(film);
        Room room =roomDao.selectById(scheduling.getRoom().getId());
        if (room==null)
            return ResultBase.fail(1,"放映厅不存在");
        scheduling.setRoom(room);
        //插入电影排期
        Integer sc= schedulingDao.insert(scheduling);
        //生成电影票
        Integer tc= ticketDao.releaseByScheduling(scheduling);
        return ResultBase.success(sc,"插入"+sc.toString()+"个电影排期，生成"+tc.toString()+"张电影票!");
    }
}
