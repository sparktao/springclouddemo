package org.hexagonsi.event.service;

import org.hexagonsi.event.model.OdAgencyEventStat;

import java.util.List;

public interface OdAgencyEventStatService {
    List<OdAgencyEventStat> getStatsByDayAndHour();
}
