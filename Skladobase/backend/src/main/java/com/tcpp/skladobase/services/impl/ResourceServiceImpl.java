package com.tcpp.skladobase.services.impl;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.dao.ResourceDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.services.ResourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static final Logger log = Logger.getLogger(SearchServiceImpl.class);
    private final NodeDAO nodeDAO;
    private final ResourceDAO resourceDAO;

    @Autowired
    public ResourceServiceImpl(
            NodeDAO nodeDAO,
            ResourceDAO resourceDAO
    ) {
        this.nodeDAO=nodeDAO;
        this.resourceDAO=resourceDAO;
    }

    @Override
    public void setTestConnection() throws DAOConfigException {
        nodeDAO.setTestConnection();
        resourceDAO.setTestConnection();
    }

    @Override
    public Collection<Resource> getResourceByTitle(String str) throws DAOException {
        return resourceDAO.getResourceByTitle(str);
    }

    @Override
    public void updateResource(Resource res) throws DAOException {
        resourceDAO.updateResource(res);
    }

    @Override
    public void deleteResource(Resource res) throws DAOException {
        resourceDAO.deleteResource(res);
    }

    @Override
    public long createResource(Resource res) throws DAOException {
        return resourceDAO.createResource(res);
    }

    @Override
    public Collection<Resource> getAllResources() throws DAOException {
        return resourceDAO.getAllResources();
    }

    @Override
    public Resource getResourceByNodeId(long id) throws DAOException {
        return resourceDAO.getResourceByNodeId(id);
    }
}
