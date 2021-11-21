package com.tcpp.skladobase.services.impl;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.dao.ResourceDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.services.NodeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NodeServiceImpl implements NodeService {

    private static final Logger log = Logger.getLogger(SearchServiceImpl.class);
    private final NodeDAO nodeDAO;
    private final ResourceDAO resourceDAO;

    @Autowired
    public NodeServiceImpl(
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
    public void deleteNode(Node node) throws DAOException {
        nodeDAO.deleteNodeById(node.getId());
    }

    @Override
    public void updateNode(Node node) throws DAOException {
        nodeDAO.updateNode(node);
    }

    @Override
    public void createNode(Node node) throws DAOException {
        nodeDAO.createNode(node);
    }

    @Override
    public Node getNodeById(long id) throws DAOException {
        return nodeDAO.getNodeById(id);
    }

    @Override
    public Node getNodeByPosition(String pos) throws DAOException {
        return nodeDAO.getNodeByPosition(pos);
    }

    @Override
    public Collection<Node> getAllNodes() throws DAOException {
        return nodeDAO.getAllNodes();
    }
}
