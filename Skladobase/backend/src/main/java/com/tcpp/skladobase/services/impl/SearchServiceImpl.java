package com.tcpp.skladobase.services.impl;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.dao.ResourceDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.services.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger log = Logger.getLogger(SearchServiceImpl.class);
    private final NodeDAO nodeDAO;
    private final ResourceDAO resourceDAO;

    @Autowired
    public SearchServiceImpl(
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
    public Resource searchForResourceByNodeId(int id) throws DAOException {
        Node node = nodeDAO.getNodeById(id);
        return node.getResource();
    }

    @Override
    public void importResource(Resource resource) throws DAOException {
        resourceDAO.createResource(resource);
    }

    @Override
    public Collection<Node> getNodes() throws DAOException {
        return nodeDAO.getAllNodes();
    }
}
