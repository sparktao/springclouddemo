package org.hexagonsi.event.dao;

import org.hexagonsi.event.model.OdAgencyEventType;

import java.util.List;

public interface OdAgencyEventTypeMapper {

    List<OdAgencyEventType> selectAll();

    int deleteByPrimaryKey(Integer typeid);

    int insert(OdAgencyEventType record);

    int insertSelective(OdAgencyEventType record);

    OdAgencyEventType selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(OdAgencyEventType record);

    int updateByPrimaryKey(OdAgencyEventType record);
}