package org.hexagonsi.event.service;

import com.github.pagehelper.PageInfo;
import org.hexagonsi.event.model.DGroupSummary;
import org.hexagonsi.event.model.OdAgencyEvent;
import org.hexagonsi.event.model.SeriesData;

import java.util.Date;
import java.util.List;

public interface OdAgencyEventService {

    // 分页查询
    PageInfo<OdAgencyEvent> selectConditionalEvents(int pageNum, int pageSize, String eventDesc);

    List<OdAgencyEvent> selectAll();

    int countByAgencyIdAndCreatedTimeBetween(String agencyid, Date startCreatedtime, Date endCreatedtime);

    List<DGroupSummary> findDGroupSummary(Date startCreatedtime, Date endCreatedtime);

    List<SeriesData> findEventTypeSummary(Date startCreatedtime, Date endCreatedtime);

    int insert(OdAgencyEvent agencyEvent);

    int update(OdAgencyEvent agencyEvent);

    int delete(String agencyEventId);

}
