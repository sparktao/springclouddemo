package org.hexagonsi.event.service.impl;

import org.hexagonsi.event.dao.OdAgencyEventStatMapper;
import org.hexagonsi.event.model.OdAgencyEventStat;
import org.hexagonsi.event.service.OdAgencyEventStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdAgencyEventStatServiceImpl implements OdAgencyEventStatService {

    @Autowired
    private OdAgencyEventStatMapper eventStatMapper;

    @Override
    public List<OdAgencyEventStat> getStatsByDayAndHour() {
        return eventStatMapper.getStatsByDayAndHour();
    }
}
