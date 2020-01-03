package org.hexagonsi.event.model;

import lombok.Data;

import java.util.Date;

@Data
public class OdAgencyEventHis {
    private Integer id;
    private String agencyeventid;
    private Date createdtime;
    private String type;
    private String remark;

    public OdAgencyEventHis(String agencyeventid, String type, String remark) {
        this.agencyeventid = agencyeventid;
        this.type = type;
        this.remark = remark;
        this.createdtime = new Date();
    }

}