package org.hexagonsi.event.controller.viewmodel;

import lombok.Data;
import org.hexagonsi.event.model.SeriesData;

import java.util.List;

@Data
public class EventStatByType {
    private int code;

    private List<String> legend;

    private List<SeriesData> seriesData;
}
