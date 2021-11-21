package com.tcpp.skladobase.services;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;

import java.util.Collection;

public interface NodeService {

    void setTestConnection() throws DAOConfigException;

    void deleteNode(Node node) throws DAOException;

    void updateNode(Node node) throws DAOException;

    void createNode(Node node) throws DAOException;

    Node getNodeById(long id) throws DAOException;

    Node getNodeByPosition(String pos) throws DAOException;

    Collection<Node> getAllNodes() throws DAOException;

}
