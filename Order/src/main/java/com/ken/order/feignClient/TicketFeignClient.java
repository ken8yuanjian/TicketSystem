package com.ken.order.feignClient;


import com.ken.common.entity.Scheduling;
import com.ken.common.entity.http.ResultBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ticketservice",
fallbackFactory = TicketFeignClientFallbackFactory.class
)
public interface TicketFeignClient {

    @GetMapping("/scheduling/{id}")
    public ResultBase<Scheduling> schedulingById(@PathVariable("id") Integer id);
}
