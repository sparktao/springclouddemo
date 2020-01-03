package org.hexagonsi.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hexagonsi.event.controller.viewmodel.EventStatByDg;
import org.hexagonsi.event.controller.viewmodel.EventStatByType;
import org.hexagonsi.event.controller.viewmodel.EventStatTotal;
import org.hexagonsi.event.controller.viewmodel.ReturnResult;
import org.hexagonsi.event.model.DGroupSummary;
import org.hexagonsi.event.model.OdAgencyEventStat;
import org.hexagonsi.event.model.SeriesData;
import org.hexagonsi.event.service.OdAgencyEventService;
import org.hexagonsi.event.service.OdAgencyEventStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/event/stats")
@CrossOrigin
public class AgencyEventStatController {
    @Autowired
    private OdAgencyEventStatService agencyEventStatService;

    @Autowired
    private OdAgencyEventService agencyEventService;

    @Value("${event.default.dispatchGroupNames}")
    private String dispatcheGroupNames;


    private String[] hours = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    @Value("${event.default.offsetDay}")
    private  Integer defaultOffsetDay;

    @GetMapping
    public ResponseEntity<EventStatListResult> selectAll() {
        List<OdAgencyEventStat> ls = agencyEventStatService.getStatsByDayAndHour();

        int[][] eventStats = new int[days.length * hours.length][3];

        for(int i = 0; i < days.length; i++) {
            for(int j = 0; j < hours.length; j++) {
                String dayofweek = days[i];
                String hourofday = hours[j];
                OdAgencyEventStat eventStat = ls.stream()
                    .filter(event -> dayofweek.equals(event.getDayOfWeek()) && hourofday.equals(event.getHourOfDay()))
                        .findAny()
                        .orElse(null);

                eventStats[i * hours.length + j] = new int[]{i, j, (eventStat != null) ? eventStat.getCount() : 0};
            }
        }

        return new ResponseEntity<>(new EventStatListResult(days, hours, eventStats, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping(path = "/total")
    public ResponseEntity<EventStatTotal> countAgencyEvents(@RequestParam String agencyId) {
        EventStatTotal total = new EventStatTotal();
        try {
            log.info("请求昨天晚20点到今天晚20点之间的数据求和");
            //取当前日期早8点
            Calendar current8 = Calendar.getInstance();
            current8.set(Calendar.HOUR_OF_DAY, 8);
            current8.set(Calendar.MINUTE, 0);
            current8.set(Calendar.SECOND, 0);
            current8.add(Calendar.DAY_OF_MONTH, defaultOffsetDay);

            //取当前日期晚20点
            Calendar current20 = Calendar.getInstance();
            current20.set(Calendar.HOUR_OF_DAY, 20);
            current20.set(Calendar.MINUTE, 0);
            current20.set(Calendar.SECOND, 0);
            current20.add(Calendar.DAY_OF_MONTH, defaultOffsetDay);

            //获取前一天晚20点
            Calendar last20 = Calendar.getInstance();
            last20.setTime(current20.getTime());
            last20.add(Calendar.DAY_OF_MONTH, -1);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.debug(String.format("前一天晚上20点当前时区: %s",
                    dateFormat.format(last20.getTime())));
            log.debug(String.format("当天早上8点当前时区:%s",
                    dateFormat.format(current8.getTime())));
            log.debug(String.format("当天晚上20点当前时区:%s",
                    dateFormat.format(current20.getTime())));

            if(!StringUtils.isNoneEmpty(agencyId)) {
                agencyId = null;
            }
            //计算前一天20点到当天20点的案件总数
            int countIn24Hours = agencyEventService.countByAgencyIdAndCreatedTimeBetween(
                    agencyId, last20.getTime(), current20.getTime());

            int countBetween20To08 = agencyEventService.countByAgencyIdAndCreatedTimeBetween(
                    agencyId, last20.getTime(), current8.getTime());

            int countBetween08To20 = agencyEventService.countByAgencyIdAndCreatedTimeBetween(
                    agencyId, current8.getTime(), current20.getTime());


            total.setCountIn24Hours(countIn24Hours);
            total.setCode(HttpStatus.OK.value());
            total.setCountBetween20To08(countBetween20To08);
            total.setCountBetween08To20(countBetween08To20);
        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            total.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new ResponseEntity<>(total, HttpStatus.OK);
    }


    @GetMapping(path = "/dg")
    public ResponseEntity<EventStatByDg> dispatchGroupSummary() {
        EventStatByDg statByDg = new EventStatByDg();
        //取当前日期晚20点
        Calendar current20 = Calendar.getInstance();
        current20.set(Calendar.HOUR_OF_DAY, 20);
        current20.set(Calendar.MINUTE, 0);
        current20.set(Calendar.SECOND, 0);
        current20.add(Calendar.DAY_OF_MONTH, defaultOffsetDay);

        //获取前一天晚20点
        Calendar last20 = Calendar.getInstance();
        last20.setTime(current20.getTime());
        last20.add(Calendar.DAY_OF_MONTH, -1);

        List<DGroupSummary> dGroupSummaries = agencyEventService.findDGroupSummary(last20.getTime(), current20.getTime());
        String[] dispatchGroupNameList = dispatcheGroupNames.split(",");
        List<Integer> sumArray = new ArrayList<>();
        Arrays.stream(dispatchGroupNameList).forEach(dgName -> {
            sumArray.add(dGroupSummaries.stream().filter(dg -> dgName.equals(dg.getDispatchGroup()))
                    .map(dg -> dg.getSum()).findFirst().orElse(0l).intValue());
        });

        statByDg.setCode(HttpStatus.OK.value());
        statByDg.setCategory(dispatchGroupNameList);
        statByDg.setSum(sumArray);
        return new ResponseEntity<>(statByDg, HttpStatus.OK);
    }

    @GetMapping(path = "/et")
    public ResponseEntity<EventStatByType> eventTypeSummary() {
        EventStatByType statByType = new EventStatByType();
        //取当前日期晚20点
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        //获取前一天晚20点
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, 29);
        endDate.set(Calendar.HOUR_OF_DAY, 23);
        endDate.set(Calendar.MINUTE,59);
        endDate.set(Calendar.SECOND, 55);

        List<SeriesData> seriesData = agencyEventService.findEventTypeSummary(startDate.getTime(), endDate.getTime());
        List<String> eventTypeList = seriesData.stream().map(sd -> sd.getName()).collect(Collectors.toList());

        statByType.setCode(HttpStatus.OK.value());
        statByType.setLegend(eventTypeList);
        statByType.setSeriesData(seriesData);

        return new ResponseEntity<>(statByType, HttpStatus.OK);
    }
}

class EventStatListResult extends ReturnResult {
    private String[] hours;
    private String[] days;
    private int[][] eventStat;

    public EventStatListResult(String[] pDays, String[] pHours, int[][] pEventStat, int pCode) {
        this.setHours(pHours);
        this.setDays(pDays);
        this.setEventStat(pEventStat);
        this.code = pCode;
    }



    public int[][] getEventStat() {
        return eventStat;
    }

    public void setEventStat(int[][] eventStat) {
        this.eventStat = eventStat;
    }

    public String[] getHours() {
        return hours;
    }

    public void setHours(String[] hours) {
        this.hours = hours;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }
}
