package com.tcpp.skladobase.services;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;

import java.util.Collection;

public interface SearchService {

    void setTestConnection() throws DAOConfigException;

    Resource searchForResourceByNodeId(long id) throws DAOException;

    void importResource(Resource resource, long id) throws DAOException, IllegalArgumentException;

    Collection<Node> getNodes() throws DAOException;

    Collection<Node> searchResource(String str) throws DAOException;

}
