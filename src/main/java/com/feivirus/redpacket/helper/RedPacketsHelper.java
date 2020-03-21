package com.feivirus.redpacket.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feivirus
 */
public class RedPacketsHelper {
    /**
     * 拆分红包
     * @param totalMoney 多少钱
     * @param peopleCount 人数
     * @return
     */
    public static List<Double> generateRedPackets(final Double totalMoney, int peopleCount) {
        List<Double> result = new ArrayList<>();

        if (peopleCount <= 0) {
            return result;
        }
        if (peopleCount == 1) {
            result.add(totalMoney);
            return result;
        }

        double average = totalMoney.doubleValue() / peopleCount;
        double totalGeneratedMoney = 0;

        /**
         * 除最后一个人，剩下的拆分掉
         */
        for (int i = 0; i < (peopleCount - 1); i++) {
            result.add(average);
            totalGeneratedMoney += average;
        }
        //剩下的钱给最后一个人,保证分的钱==totalMoney
        result.add(totalMoney.doubleValue() - totalGeneratedMoney);

        return result;
    }
}
