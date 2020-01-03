package org.hexagonsi.event.service.impl;

import org.hexagonsi.event.dao.OdAgencyEventTypeMapper;
import org.hexagonsi.event.model.OdAgencyEventType;
import org.hexagonsi.event.service.OdAgencyEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdAgencyEventTypeServiceImpl implements OdAgencyEventTypeService {
    @Autowired
    private OdAgencyEventTypeMapper agencyEventTypeMapper;

    @Cacheable(cacheNames="agencyEventType")
    @Override
    public List<OdAgencyEventType> selectAll() {
        return agencyEventTypeMapper.selectAll();
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
