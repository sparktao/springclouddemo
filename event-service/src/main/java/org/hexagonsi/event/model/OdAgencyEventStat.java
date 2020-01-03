package org.hexagonsi.event.model;

import lombok.Data;

@Data
public class OdAgencyEventStat {

    private Integer id;

    private String dayOfWeek;

    private String hourOfDay;

    private Integer Count;
}
