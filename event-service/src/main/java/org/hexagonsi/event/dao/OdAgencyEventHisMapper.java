package org.hexagonsi.event.dao;

import org.hexagonsi.event.model.OdAgencyEventHis;

import java.util.List;

public interface OdAgencyEventHisMapper {

    List<OdAgencyEventHis> selectByAgencyEventId(String agencyeventid);

    int deleteByPrimaryKey(Integer id);

    int insert(OdAgencyEventHis record);

    int insertSelective(OdAgencyEventHis record);

    OdAgencyEventHis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OdAgencyEventHis record);

    int updateByPrimaryKey(OdAgencyEventHis record);
}