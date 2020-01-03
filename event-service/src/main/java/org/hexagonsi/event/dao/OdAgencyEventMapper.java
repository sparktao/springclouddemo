package org.hexagonsi.event.dao;

import org.apache.ibatis.annotations.Param;
import org.hexagonsi.event.model.DGroupSummary;
import org.hexagonsi.event.model.OdAgencyEvent;
import org.hexagonsi.event.model.SeriesData;


import java.util.Date;
import java.util.List;

public interface OdAgencyEventMapper {

    List<OdAgencyEvent> selectConditionalEvents(String eventdesc);

    List<OdAgencyEvent> selectAll();

    int countByAgencyIdAndCreatedTimeBetween(@Param("agencyid") String agencyid,
                                             @Param("startCreatedtime") Date startCreatedtime,
                                             @Param("endCreatedtime") Date endCreatedtime);

    List<DGroupSummary> findDGroupSummary(@Param("startCreatedtime") Date startCreatedtime,
                                          @Param("endCreatedtime") Date endCreatedtime);

    List<SeriesData> findEventTypeSummary(@Param("startCreatedtime") Date startCreatedtime,
                                          @Param("endCreatedtime") Date endCreatedtime);

    int deleteByPrimaryKey(String agencyeventid);

    int insert(OdAgencyEvent record);

    int insertSelective(OdAgencyEvent record);

    OdAgencyEvent selectByPrimaryKey(String agencyeventid);

    int updateByPrimaryKeySelective(OdAgencyEvent record);

    int updateByPrimaryKey(OdAgencyEvent record);
}