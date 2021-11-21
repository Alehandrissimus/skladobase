package com.tcpp.skladobase.services;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;

import java.util.Collection;

public interface SearchService {

    void setTestConnection() throws DAOConfigException;

    Resource searchForResourceByNodeId(int id) throws DAOException;

    void importResource(Resource resource) throws DAOException;

    Collection<Node> getNodes() throws DAOException;


}
