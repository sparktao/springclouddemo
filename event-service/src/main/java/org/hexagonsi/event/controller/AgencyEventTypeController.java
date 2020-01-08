package org.hexagonsi.event.controller;

import lombok.extern.slf4j.Slf4j;
import org.hexagonsi.event.controller.viewmodel.ReturnResult;
import org.hexagonsi.event.future.AgencyEventTypeFuture;
import org.hexagonsi.event.model.OdAgencyEventType;
import org.hexagonsi.event.service.OdAgencyEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/event/types")
@CrossOrigin
public class AgencyEventTypeController {

    @Autowired
    private OdAgencyEventTypeService agentEventTypeService;

    @Autowired
    private AgencyEventTypeFuture eventTypeFuture;

    @GetMapping
    public ResponseEntity<EventTypeResult> selectAll() {
        log.info("select all agency event types.");
        List<OdAgencyEventType> ls = agentEventTypeService.selectAll();
        return new ResponseEntity<>(new EventTypeResult(ls, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public CompletableFuture<ResponseEntity<EventTypeResult>> selectAllFuture() {
        log.info("select all agency event types.");
        return eventTypeFuture.selectAll().thenApply(ls -> new ResponseEntity<>(new EventTypeResult(ls, HttpStatus.OK.value()), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ReturnResult> insertAgencyEventType(@RequestBody OdAgencyEventType agencyEventType) {
        log.info("insert agency event type");
        int result = agentEventTypeService.insert(agencyEventType);
        if (result > 0) {
            return new ResponseEntity<>(new ReturnResult(HttpStatus.OK.value()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new ReturnResult(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReturnResult> updateAgencyEventType(@PathVariable int id,
                                                              @RequestBody OdAgencyEventType agencyEventType) {
        log.info("update agency event type {0}", id);
        agencyEventType.setTypeid(id);
        int result = agentEventTypeService.update(agencyEventType);
        if (result > 0) {
            return new ResponseEntity<>(new ReturnResult(HttpStatus.OK.value()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new ReturnResult(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReturnResult> deleteAgencyEventType(@PathVariable int id) {
        log.info("delete agency event type {0}", id);
        int result = agentEventTypeService.delete(id);
        if (result > 0) {
            return new ResponseEntity<>(new ReturnResult(HttpStatus.OK.value()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new ReturnResult(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

class EventTypeResult extends ReturnResult {
    public List<OdAgencyEventType> eventTypes;

    public EventTypeResult(List<OdAgencyEventType> pEventTypes, int pCode) {
        this.eventTypes = pEventTypes;
        this.code = pCode;
    }
}
