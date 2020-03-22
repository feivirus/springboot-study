package com.feivirus.redpacket.service.impl;

import com.feivirus.redpacket.dao.RpRecordsMapper;
import com.feivirus.redpacket.domain.RpRecords;
import com.feivirus.redpacket.domain.RpRecordsExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author feivirus
 * 对t_rp_records表相关的操作
 */
@Service
public class RpRecordsService {
    @Autowired
    private RpRecordsMapper rpRecordsMapper;

    /**
     * 查询红包记录数
     * @param rid
     * @param uid
     * @return
     */
    public Integer countRpRecords(Integer rid, Integer uid) {
        RpRecordsExample example = new RpRecordsExample();
        RpRecordsExample.Criteria criteria = example.createCriteria();

        criteria.andRidEqualTo(rid);
        criteria.andUidEqualTo(uid);
        List<RpRecords> recordsList = rpRecordsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(recordsList)) {
            return 0;
        }
        return recordsList.size();
    }

    public int updateByPrimaryKey(RpRecords rpRecords) {
        return rpRecordsMapper.updateByPrimaryKey(rpRecords);
    }

    public int insertSelective(RpRecords rpRecords) {
        return rpRecordsMapper.insertSelective(rpRecords);
    }

    public int insert(RpRecords rpRecords) {
        return rpRecordsMapper.insert(rpRecords);
    }
}
