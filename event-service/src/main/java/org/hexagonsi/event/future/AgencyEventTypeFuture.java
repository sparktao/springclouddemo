package org.hexagonsi.event.future;

import org.hexagonsi.event.model.OdAgencyEventType;
import org.hexagonsi.event.service.OdAgencyEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class AgencyEventTypeFuture {

    @Autowired
    private OdAgencyEventTypeService agentEventTypeService;

    public CompletableFuture<List<OdAgencyEventType>> selectAll() {
        // 使用supplyAysnc方法新建线程来运行Supplier<T>
        return CompletableFuture.supplyAsync(() -> {
           return agentEventTypeService.selectAll();
        });
    }
}
