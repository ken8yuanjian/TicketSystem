package com.ken.order.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ken.common.entity.User;
import com.ken.common.entity.Order;
import com.ken.common.entity.Scheduling;
import com.ken.common.entity.Ticket;
import com.ken.common.entity.http.ResultBase;
import com.ken.order.dao.CommpanyDao;
import com.ken.order.dao.MemberDao;
import com.ken.order.dao.OrderDao;
import com.ken.order.feignClient.TicketFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDao orderDao;
    @Autowired
    MemberDao memberDao;

    @Autowired
    CommpanyDao commpanyDao;

    @Autowired
    TicketFeignClient ticketFeignClient;

    @Override
    public ResultBase< List<Order> > list(Order order) {
        return ResultBase.success( orderDao.list(order) );
    }

    /*
    购票接口，
    1、本质上是涉及到微服务间的数据库事务同步，没有采用分布式事务方案
    2、在数据库层面直接跨服务数据库操作，做事务同步

    成功返回订单ID，失败返回0
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBase< Integer> buy(Integer memberId, Integer schedulingId) {
        if (memberId==null || schedulingId==null)
            return ResultBase.fail(1,"参数无效");
        //查询排期
        ResultBase<Scheduling> schedulingRes = ticketFeignClient.schedulingById(schedulingId);
        if (schedulingRes.getData()==null)
            return ResultBase.fail(1,"电影排期无效");
        //查询会员
        User member = memberDao.byIdForUpdate(memberId);
        if (member == null)
            return ResultBase.fail(1,"会员无效");
        //先锁定一张票
        Ticket ticket = orderDao.ticketByIdforUpdate(schedulingId);
        if (null == ticket)
            return ResultBase.fail(1,"无剩余票");
        //扣除会员票款
        float amount = schedulingRes.getData().getPrice() *1;
        if (member.getAmount()< amount )
            return ResultBase.fail(1,"余额不足");
        LambdaUpdateWrapper<User> wrapperMember=new LambdaUpdateWrapper<>();
        wrapperMember.set(User::getAmount,member.getAmount() - amount).eq(User::getId,memberId);
        memberDao.update(member,wrapperMember);
        //将票款汇入公司账号
        commpanyDao.payTo(1,amount);
        //售出电影票
        ticket.setMemberid(memberId);
        orderDao.updateTicket(ticket.getId(), memberId);
        //生成一个订单
        orderDao.insert(memberId,schedulingId);

        return ResultBase.success( ticket );


    }
}
