package org.hexagonsi.event.service.impl;

import org.hexagonsi.event.dao.OdAgencyEventHisMapper;
import org.hexagonsi.event.model.OdAgencyEventHis;
import org.hexagonsi.event.service.OdAgencyEventHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdAgencyEventHisServiceImpl implements OdAgencyEventHisService {

    @Autowired
    private OdAgencyEventHisMapper eventHisMapper;

    @Override
    public List<OdAgencyEventHis> selectByEventId(String agencyEventId) {
        return eventHisMapper.selectByAgencyEventId(agencyEventId);
    }

    @Override
    public int insert(OdAgencyEventHis agencyEvent) {
        return eventHisMapper.insertSelective(agencyEvent);
    }
}
