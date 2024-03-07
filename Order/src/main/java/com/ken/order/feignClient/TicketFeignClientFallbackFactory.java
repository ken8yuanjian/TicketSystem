package com.ken.order.feignClient;

import com.ken.common.entity.Scheduling;
import com.ken.common.entity.http.ResultBase;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class TicketFeignClientFallbackFactory implements FallbackFactory<TicketFeignClient> {
    @Override
    public TicketFeignClient create(Throwable cause) {
        return new TicketFeignClient() {
            @Override
            public ResultBase<Scheduling> schedulingById(Integer id) {
                return ResultBase.fail(1,cause.toString());
            }
        };
    }
}
