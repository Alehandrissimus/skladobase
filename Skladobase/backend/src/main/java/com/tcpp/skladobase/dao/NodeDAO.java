package com.tcpp.skladobase.dao;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;

import java.util.Collection;

public interface NodeDAO {

    String URL_PROPERTY = "${spring.datasource.url}";
    String USERNAME_PROPERTY = "${spring.datasource.username}";
    String PASSWORD_PROPERTY = "${spring.datasource.password}";
    String URLTEST_PROPERTY = "${spring.test-datasource.url}";
    String USERNAMETEST_PROPERTY = "${spring.test-datasource.username}";
    String PASSWORDTEST_PROPERTY = "${spring.test-datasource.password}";

    String ERROR_TEST_CONNECTION = "Error while setting test connection ";
    String TEST = "_TEST";

    String SELECT_ALL_NODES = "select * from node";
    String SELECT_NODES_WITH_RESOURCE = "select * from node where resource=?";
    String SELECT_NODE_BY_POS = "select * from node where position = ?";
    String SELECT_NODE_BY_ID = "select * from node where id_node = ?";
    String CREATE_NODE = "insert into node(position, description, resource) values (?, ?, ?)";
    String CREATE_NODE_NO_DESC = "insert into node(position, resource) values (?, ?)";
    String UPDATE_NODE = "update node set position=?, description=?, resource=? where id_node=?";
    String UPDATE_NODE_NO_RES = "update node set position=?, description=? where id_node=?";
    String DELETE_NODE = "delete from node where id_node=?";

    void setTestConnection() throws DAOConfigException;

    Collection<Node> getAllNodes() throws DAOException;

    Node getNodeByPosition(String pos) throws DAOException;

    Node getNodeById(long id) throws DAOException;

    void createNode(Node node) throws DAOException;

    void createNodeNoDesc(Node node) throws DAOException;

    void updateNode(Node node) throws DAOException;

    void updateNodeWithoutResource(Node node) throws DAOException;

    void deleteNodeById(long id) throws DAOException;

    Collection<Node> getNodesByResourceId(long id) throws DAOException;

}
