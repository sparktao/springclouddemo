package org.hexagonsi.event.controller.viewmodel;

import lombok.Data;

import java.util.List;

@Data
public class EventStatByDg {

    private int code;

    private String[] category;

    private int[] legend;

    private List<Integer> sum;


}
