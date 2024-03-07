package com.ken.ticket;

import com.ken.common.entity.Ticket;
import com.ken.ticket.dao.TicketDao;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class TicketApplicationTests {

    @Autowired
    TicketDao ticketDao;

    @Test
    void contextLoads() throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setSchedulingid( 19 );
        ticket.setMemberid(0);
        List<Ticket> r =ticketDao.list(ticket);
        r = r;
    }

}
