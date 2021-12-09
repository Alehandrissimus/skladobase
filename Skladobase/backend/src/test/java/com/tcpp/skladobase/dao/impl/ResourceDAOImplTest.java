package com.tcpp.skladobase.dao.impl;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;
import com.tcpp.skladobase.model.impl.ResourceImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceDAOImplTest {

    private ResourceDAOImpl resourceDAO;
    private static final Logger log = Logger.getLogger(ResourceDAOImplTest.class);

    @Autowired
    private void setResourceDAO(ResourceDAOImpl resourceDAO) {
        this.resourceDAO = resourceDAO;
//        try {
//            resourceDAO.setTestConnection();
//        } catch (DAOConfigException e) {
//            log.error(LOG_ERROR + e.getMessage());
//        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getResourceByTitleTest() {
        try {
            Collection<Resource> resources = resourceDAO.getResourceByTitle("Resource 1");
            assertNotNull(resources);
            for(Resource resource: resources)
                assertNotNull(resource);
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getResourceByNodeIdTest() {
        try {
            Resource resource = resourceDAO.getResourceByNodeId(5);
            assertNotNull(resource);
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }


    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getAllResourcesTest() {
        try {
            ArrayList<Resource> resource = (ArrayList<Resource>) resourceDAO.getAllResources();
            assertNotNull(resource);
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void createResourceTest() {
        try {
            Resource expected = new ResourceImpl(
                    1,
                    "Sand",
                    1123,
                    "sand maybe",
                    1
            );
            resourceDAO.createResource(expected);
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }



}