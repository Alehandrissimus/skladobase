package com.tcpp.skladobase.services;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Resource;

import java.util.Collection;

public interface ResourceService {



    void setTestConnection() throws DAOConfigException;

    Collection<Resource> getResourceByTitle(String str) throws DAOException;

    void updateResource(Resource res) throws DAOException;

    void deleteResource(Resource res) throws DAOException;

    void createResource(Resource res) throws DAOException;

    Collection<Resource> getAllResources() throws DAOException;

    Resource getResourceByNodeId(long id) throws DAOException;

}
