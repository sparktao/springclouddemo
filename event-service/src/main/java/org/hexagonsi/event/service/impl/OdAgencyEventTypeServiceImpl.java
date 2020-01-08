package org.hexagonsi.event.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.hexagonsi.event.dao.OdAgencyEventTypeMapper;
import org.hexagonsi.event.model.OdAgencyEventType;
import org.hexagonsi.event.service.OdAgencyEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdAgencyEventTypeServiceImpl implements OdAgencyEventTypeService {
    @Autowired
    private OdAgencyEventTypeMapper agencyEventTypeMapper;

    @HystrixCommand(fallbackMethod = "selectAllFallback")
    @Override
    public List<OdAgencyEventType> selectAll() {
        return agencyEventTypeMapper.selectAll();
    }

    @Override
    public List<OdAgencyEventType> selectAllFallback() {
        return new ArrayList<>();
    }



    @Override
    public OdAgencyEventType selectById(int id) {
        return agencyEventTypeMapper.selectByPrimaryKey(id);
    }

    @CacheEvict(cacheNames = "agencyEventType", allEntries = true)
    @Override
    public int insert(OdAgencyEventType agencyEventType) {
        return agencyEventTypeMapper.insertSelective(agencyEventType);
    }

    @CacheEvict(cacheNames = "agencyEventType", allEntries = true)
    @Override
    public int update(OdAgencyEventType agencyEventType) {
        return agencyEventTypeMapper.updateByPrimaryKeySelective(agencyEventType);
    }

    @CacheEvict(cacheNames = "agencyEventType", allEntries = true)
    @Override
    public int delete(int key) {
        return agencyEventTypeMapper.deleteByPrimaryKey(key);
    }
}
