package org.hexagonsi.event.controller.viewmodel;

import lombok.Data;

@Data
public class EventStatTotal {

    protected int code;
    /**
     * 24小时内发生的警情总数
     */
    private int countIn24Hours;

    /**
     * 早8点到晚8点
     *
     */
    private int countBetween08To20;

    /**
     * 晚8点到早8点
     *
     */
    private int countBetween20To08;
}
