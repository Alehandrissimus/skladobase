package com.tcpp.skladobase.dao.impl;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.impl.NodeImpl;
import com.tcpp.skladobase.util.DAOUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import static com.tcpp.skladobase.exception.MessagesForException.NODE_HAS_NOT_BEEN_RECEIVED;

@Repository
public class NodeDAOImpl implements NodeDAO {

    private Connection connection;
    private static final Logger log = Logger.getLogger(NodeDAOImpl.class);

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    @Autowired
    public NodeDAOImpl(
            @Value(URL_PROPERTY) String URL,
            @Value(USERNAME_PROPERTY) String USERNAME,
            @Value(PASSWORD_PROPERTY) String PASSWORD
    ) throws DAOConfigException {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

        connection = DAOUtil.getDataSource(URL, USERNAME, PASSWORD);
    }


    public void setTestConnection() throws DAOConfigException {
        try {
            connection = DAOUtil.getDataSource(URL, USERNAME + TEST, PASSWORD);
        } catch (DAOConfigException e) {
            log.error(ERROR_TEST_CONNECTION + e.getMessage());
            throw new DAOConfigException(ERROR_TEST_CONNECTION, e);
        }
    }

    public Collection<Node> getAllNodes() throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NODES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Collection<Node> nodes = new ArrayList<>();

            if (!resultSet.isBeforeFirst()) {
                log.error(NODE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(NODE_HAS_NOT_BEEN_RECEIVED);
            }

            while (resultSet.next()) {
                Node node = new NodeImpl(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
                nodes.add(node);
            }
            return nodes;

        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public Node getNodeByPosition(String pos) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NODE_BY_POS)) {
            preparedStatement.setString(1, pos);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                log.error(NODE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(NODE_HAS_NOT_BEEN_RECEIVED);
            }
            resultSet.next();
            return new NodeImpl(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public Node getNodeById(long id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NODE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                log.error(NODE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(NODE_HAS_NOT_BEEN_RECEIVED);
            }
            resultSet.next();
            return new NodeImpl(
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public long createNode(Node node) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NODE)) {
            preparedStatement.setString(1, node.getPosition());
            preparedStatement.setString(2, node.getDescription());
            preparedStatement.setLong(3, node.getResource().getId());
            int idNode = preparedStatement.executeUpdate();
            if (idNode > 0) {
                ResultSet resultSets = preparedStatement.getGeneratedKeys();
                resultSets.next();
                return resultSets.getLong(1);
            }
            throw new DAOException("DAO_LOGIC_EXCEPTION");
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public long createNodeNoDesc(Node node) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NODE_NO_DESC)) {
            preparedStatement.setString(1, node.getPosition());
            preparedStatement.setLong(2, node.getResource().getId());
            int idNode = preparedStatement.executeUpdate();
            if (idNode > 0) {
                ResultSet resultSets = preparedStatement.getGeneratedKeys();
                resultSets.next();
                return resultSets.getLong(1);
            }
            throw new DAOException("DAO_LOGIC_EXCEPTION");
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public void updateNode(Node node) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NODE)) {
            preparedStatement.setString(1, node.getPosition());
            preparedStatement.setString(2, node.getDescription());
            preparedStatement.setLong(3, node.getResource().getId());
            preparedStatement.setLong(4, node.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public void deleteNodeById(long id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NODE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }
}
