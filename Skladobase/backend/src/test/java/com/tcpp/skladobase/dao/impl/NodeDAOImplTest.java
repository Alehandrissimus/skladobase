package com.tcpp.skladobase.dao.impl;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.ResourceType;
import com.tcpp.skladobase.model.impl.NodeImpl;
import com.tcpp.skladobase.model.impl.ResourceImpl;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class NodeDAOImplTest {

    private static final String LOG_ERROR = "Error while setting test connection" + " ";

    private NodeDAOImpl nodeDAO;
    private static final Logger log = Logger.getLogger(NodeDAOImplTest.class);

    @Autowired
    private void setAnnouncementDAO(NodeDAOImpl nodeDAO) {
        this.nodeDAO = nodeDAO;
//        try {
//            nodeDAO.setTestConnection();
//        } catch (DAOConfigException e) {
//            log.error(LOG_ERROR + e.getMessage());
//        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getAllNodes() {
        try {
            Collection<Node> nodes = nodeDAO.getAllNodes();
            assertNotNull(nodes);
            for(Node node : nodes)
                assertNotNull(node);
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getNodeByPositionTest() {
        try {
            Node node = nodeDAO.getNodeByPosition("421,241");
            assertNotNull(node);
            assertNotNull(node.getPosition());
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void getNodeByIdTest() {
        try {
            Node node = nodeDAO.getNodeById(5L);
            assertNotNull(node);
            assertNotNull(node.getPosition());
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void createNodeTest() {
        try {
            Random rand = new Random();
            String position = rand.nextInt(500) + "," + rand.nextInt(500);
            Node expected = new NodeImpl(
                    position,
                    "testdesc",
                    new ResourceImpl(
                            1,
                            "",
                            1,
                            "",
                            1
                    )
            );
            nodeDAO.createNode(expected);

            Node actual = nodeDAO.getNodeByPosition(position);
            assertEquals(expected.getPosition(), actual.getPosition());
            assertEquals(expected.getDescription(), actual.getDescription());
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void updateNodeTest() {
        try {
            Random rand = new Random();
            String position = rand.nextInt(500) + "," + rand.nextInt(500);
            Node expected = new NodeImpl(
                    16L,
                    position,
                    "testdesc",
                    new ResourceImpl(
                            1,
                            "",
                            1,
                            "",
                            1
                    )
            );
            nodeDAO.updateNodeWithoutResource(expected);

            Node actual = nodeDAO.getNodeByPosition(position);
            assertEquals(expected.getPosition(), actual.getPosition());
            assertEquals(expected.getDescription(), actual.getDescription());
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

    @Test
    @Timeout(value = 10000, unit= TimeUnit.MILLISECONDS)
    void deleteNodeByIdTest() {
        try {
            ArrayList<Node> nodes = (ArrayList<Node>) nodeDAO.getAllNodes();

            Node expected = nodes.get(nodes.size()-1);
            nodeDAO.deleteNodeById(expected.getId());
            assertThrows(DAOException.class,
                    () -> nodeDAO.getNodeById(expected.getId()));
        } catch (DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

}