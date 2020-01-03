package org.hexagonsi.event.dao;

import org.hexagonsi.event.model.OdAgencyEventStat;

import java.util.List;

public interface OdAgencyEventStatMapper {

    List<OdAgencyEventStat> getStatsByDayAndHour();
}
