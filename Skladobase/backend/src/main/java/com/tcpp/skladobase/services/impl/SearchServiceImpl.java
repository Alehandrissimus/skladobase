package com.tcpp.skladobase.services.impl;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.dao.ResourceDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.services.SearchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SearchServiceImpl implements SearchService, MessagesForException {

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
    public Resource searchForResourceByNodeId(long id) throws DAOException {
        if(id < 1) {
            throw new IllegalArgumentException(INVALID_ID);
        }
        return resourceDAO.getResourceByNodeId(id);
    }

    @Override
    public void importResource(Resource resource, long id) throws DAOException, IllegalArgumentException {
        if(id <= 0) {
            throw new IllegalArgumentException(INVALID_ID);
        }
        resourceDAO.createResource(resource);
        Resource gotResource = resourceDAO.getLastResourceByTitle(resource.getTitle());
        Node node = nodeDAO.getNodeById(id);
        node.setResource(gotResource);
        nodeDAO.updateNode(node);
    }

    @Override
    public Collection<Node> getNodes() throws DAOException {
        Collection<Node> nodes = nodeDAO.getAllNodes();
        for (Node node : nodes) {
            node.setResource(resourceDAO.getResourceByNodeId(node.getId()));
        }
        return nodes;
    }

    @Override
    public Collection<Node> searchResource(String str) throws DAOException {
        Collection<Node> nodes = new ArrayList<>();
        Collection<Resource> resources = resourceDAO.getResourceByTitle(str);
        for(Resource resource : resources) {
            try {
                nodes.addAll(nodeDAO.getNodesByResourceId(resource.getId()));
            } catch (DAOException e) {
                log.info("Did not found nodes for resource: " + resource.getId());
            }
        }
        return nodes;
    }
}
