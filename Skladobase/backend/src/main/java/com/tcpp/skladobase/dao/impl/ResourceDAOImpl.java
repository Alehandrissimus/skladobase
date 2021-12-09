package com.tcpp.skladobase.dao.impl;

import com.tcpp.skladobase.dao.ResourceDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;
import com.tcpp.skladobase.model.impl.NodeImpl;
import com.tcpp.skladobase.model.impl.ResourceImpl;
import com.tcpp.skladobase.util.DAOUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static com.tcpp.skladobase.exception.MessagesForException.NODE_HAS_NOT_BEEN_RECEIVED;
import static com.tcpp.skladobase.exception.MessagesForException.RESOURCE_HAS_NOT_BEEN_RECEIVED;


@Repository
public class ResourceDAOImpl implements ResourceDAO {

    private Connection connection;
    private static final Logger log = Logger.getLogger(ResourceDAOImpl.class);

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    @Autowired
    public ResourceDAOImpl(
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

    @Override
    public Collection<Resource> getResourceByTitle(String title) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESOURCE_BY_TITLE)) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            Collection<Resource> resources = new ArrayList<>();

            if (!resultSet.isBeforeFirst()) {
                log.error(RESOURCE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(RESOURCE_HAS_NOT_BEEN_RECEIVED);
            }

            while (resultSet.next()) {
                Resource resource = new ResourceImpl(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                resources.add(resource);
            }
            return resources;

        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public Resource getResourceByNodeId(long id) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESOURCE_BY_NODE)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                log.error(RESOURCE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(RESOURCE_HAS_NOT_BEEN_RECEIVED);
            }

            resultSet.next();
            return new ResourceImpl(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public Resource getLastResourceByTitle(String str) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_BY_TITLE)) {
            preparedStatement.setString(1, str);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                log.error(RESOURCE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(RESOURCE_HAS_NOT_BEEN_RECEIVED);
            }

            resultSet.next();
            return new ResourceImpl(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getFloat(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public Collection<Resource> getAllResources() throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESOURCES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            Collection<Resource> resources = new ArrayList<>();

            if (!resultSet.isBeforeFirst()) {
                log.error(RESOURCE_HAS_NOT_BEEN_RECEIVED);
                throw new DAOException(RESOURCE_HAS_NOT_BEEN_RECEIVED);
            }

            while (resultSet.next()) {
                Resource resource = new ResourceImpl(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                );
                resources.add(resource);
            }
            return resources;

        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public void createResource(Resource resource) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESOURCE)) {
            preparedStatement.setString(1, resource.getTitle());
            preparedStatement.setFloat(2, resource.getCount());
            preparedStatement.setString(3, resource.getDescription());
            preparedStatement.setInt(4, resource.getResourceType().ordinal());
            int idResource = preparedStatement.executeUpdate();
            if (idResource > 0) {
                return;
            }
            throw new DAOException("DAO_LOGIC_EXCEPTION");
        } catch (SQLException | DAOException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public void deleteResource(Resource res) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESOURCE)) {
            preparedStatement.setLong(1, res.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }

    @Override
    public void updateResource(Resource res) throws DAOException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESOURCE)) {
            preparedStatement.setString(1, res.getTitle());
            preparedStatement.setFloat(2, res.getCount());
            preparedStatement.setString(3, res.getDescription());
            preparedStatement.setInt(4, res.getResourceType().ordinal());
            preparedStatement.setLong(5, res.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("DAO_LOGIC_EXCEPTION" + e.getMessage());
            throw new DAOException("DAO_LOGIC_EXCEPTION", e);
        }
    }
}
