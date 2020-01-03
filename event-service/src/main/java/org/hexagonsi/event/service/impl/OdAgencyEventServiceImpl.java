package org.hexagonsi.event.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hexagonsi.event.dao.OdAgencyEventMapper;
import org.hexagonsi.event.model.DGroupSummary;
import org.hexagonsi.event.model.OdAgencyEvent;
import org.hexagonsi.event.model.SeriesData;
import org.hexagonsi.event.service.OdAgencyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OdAgencyEventServiceImpl implements OdAgencyEventService {

    @Autowired
    private OdAgencyEventMapper agencyEventMapper;

    @Override
    public PageInfo<OdAgencyEvent> selectConditionalEvents(int pageNum, int pageSize, String eventDesc) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        //紧跟着的第一个select方法会被分页
        List<OdAgencyEvent> eventList = agencyEventMapper.selectConditionalEvents(eventDesc);
        PageInfo result = new PageInfo(eventList);
        return result;
    }

    @Override
    public List<OdAgencyEvent> selectAll() {
        return agencyEventMapper.selectAll();
    }

    @Override
    public int countByAgencyIdAndCreatedTimeBetween(String agencyid, Date startCreatedtime, Date endCreatedtime) {
        return agencyEventMapper.countByAgencyIdAndCreatedTimeBetween(agencyid, startCreatedtime, endCreatedtime);
    }

    @Override
    public List<DGroupSummary> findDGroupSummary(Date startCreatedtime, Date endCreatedtime) {
        return agencyEventMapper.findDGroupSummary(startCreatedtime, endCreatedtime);
    }

    @Override
    public List<SeriesData> findEventTypeSummary(Date startCreatedtime, Date endCreatedtime) {
        return agencyEventMapper.findEventTypeSummary(startCreatedtime, endCreatedtime);
    }

    @Override
    public int insert(OdAgencyEvent agencyEvent) {
        return agencyEventMapper.insertSelective(agencyEvent);
    }

    @Override
    public int update(OdAgencyEvent agencyEvent) {
        return agencyEventMapper.updateByPrimaryKeySelective(agencyEvent);
    }

    @Override
    public int delete(String agencyEventId) {
        return agencyEventMapper.deleteByPrimaryKey(agencyEventId);
    }
}
