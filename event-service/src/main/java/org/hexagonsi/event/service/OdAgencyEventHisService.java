package org.hexagonsi.event.service;

import org.hexagonsi.event.model.OdAgencyEventHis;

import java.util.List;

public interface OdAgencyEventHisService {

    List<OdAgencyEventHis> selectByEventId(String agencyEventId);

    int insert(OdAgencyEventHis agencyEvent);
}
