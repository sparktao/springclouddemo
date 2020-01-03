package org.hexagonsi.event.model;

import lombok.Data;

@Data
public class OdAgencyEventType implements java.io.Serializable{
    private Integer typeid;
    private String agencyeventsubtypecode;
    private String agencyeventtypecode;
    private String agencyeventsubtypecodedesc;
    private String agencyeventtypecodedesc;
    private String agencyid;
    private Integer priority;
}