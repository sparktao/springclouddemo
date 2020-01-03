package org.hexagonsi.event.model;

import lombok.Data;

import java.util.Date;

@Data
public class OdAgencyEvent {
    private String agencyeventid;

    private String agencyeventdesc;

    private String agencyeventsubtypecode;

    private String agencyeventsubtypecodedesc;

    private String agencyeventtypecode;

    private String agencyeventtypecodedesc;

    private String agencyid;

    private Date addedtime;

    private Integer alarmlevel;

    private Double latitude;

    private Double longitude;

    private String location;

    private Date createdtime;

    private String createdterminal;

    private Date updatedtime;

    private String updatedterminal;

    private Date closetime;

    private String closingterminal;

    private String dispatchgroup;

    private String callername;

    private String callerphone;

    private String dispatchvehicles;


}