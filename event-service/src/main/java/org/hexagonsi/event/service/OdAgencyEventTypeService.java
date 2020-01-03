package org.hexagonsi.event.service;

import org.hexagonsi.event.model.OdAgencyEventType;

import java.util.List;

public interface OdAgencyEventTypeService {

    List<OdAgencyEventType> selectAll();

    OdAgencyEventType selectById(int id);

    int insert(OdAgencyEventType agencyEventType);
    int update(OdAgencyEventType agencyEventType);
    int delete(int key);


}
