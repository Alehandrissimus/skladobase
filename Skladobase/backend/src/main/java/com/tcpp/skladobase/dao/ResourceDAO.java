package com.tcpp.skladobase.dao;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

public interface ResourceDAO {

    String URL_PROPERTY = "${spring.datasource.url}";
    String USERNAME_PROPERTY = "${spring.datasource.username}";
    String PASSWORD_PROPERTY = "${spring.datasource.password}";

    String ERROR_TEST_CONNECTION = "Error while setting test connection ";
    String TEST = "_TEST";

    String SELECT_RESOURCE_BY_TITLE = "select * from resource where title=?";
    String SELECT_ALL_RESOURCES = "select * from resource";
    String CREATE_RESOURCE = "insert into resource(title, count, description, resource_type) values (?, ?, ?, ?)";
    String CREATE_RESOURCE_NO_DESC = "insert into node(title, count, resource_type) values (?, ?, ?)";
    String DELETE_RESOURCE = "delete from resource where id_resource=?";
    String UPDATE_RESOURCE = "update resource set title=?, count=?, description=?, resource_type=? where id_resource=?";
    String SELECT_RESOURCE_BY_NODE = "select * from resource where id_resource = " +
            "    (select n.resource from node n where id_node = ?)";

    void setTestConnection() throws DAOConfigException;

    Collection<Resource> getResourceByTitle(String title) throws DAOException;

    Collection<Resource> getAllResources() throws DAOException;

    long createResource(Resource res) throws DAOException;

    void deleteResource(Resource res) throws DAOException;

    void updateResource(Resource res) throws DAOException;

    Resource getResourceByNodeId(long id) throws DAOException;
}
