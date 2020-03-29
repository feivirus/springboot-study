package com.feivirus.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author feivirus
 * 减库存操作
 * 研究分布式锁
 */
@Controller
public class DistributedLockController {
    @Autowired
    private DistributedLockService lockService;

    @GetMapping("/deductStockWithRedis")
    @ResponseBody
    public String deductStockWithRedis() {
        return lockService.deductStockWithRedis();
    }

    @GetMapping("/deductStockWithRedission")
    @ResponseBody
    public String deductStockWithRedission() {
        return lockService.deductStockWithRedission();
    }

    @GetMapping("/deductStockWithZookeeper")
    @ResponseBody
    public String deductStockWithZookeeper() {
        return lockService.deductStockWithZookeeper();
    }
}
