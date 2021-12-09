package com.tcpp.skladobase.services.impl;

import com.tcpp.skladobase.dao.impl.ResourceDAOImpl;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;
import com.tcpp.skladobase.model.impl.ResourceImpl;
import com.tcpp.skladobase.services.SearchService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceImplTest {

    private SearchService searchService;
    private static final Logger log = Logger.getLogger(SearchServiceImplTest.class);

    @Autowired
    private void setResourceDAO(SearchServiceImpl searchService) {
        this.searchService = searchService;
//        try {
//            resourceDAO.setTestConnection();
//        } catch (DAOConfigException e) {
//            log.error(LOG_ERROR + e.getMessage());
//        }
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest() {
        long nodeId = -1;
        Resource resource = new ResourceImpl.ResourceBuilder()
                .setTitle("aaaaaa")
                .setResourceType(1)
                .setCount(1)
                .build();
        assertThrows(IllegalArgumentException.class,
                () -> searchService.importResource(resource, nodeId)
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest2() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("")
                        .setResourceType(1)
                        .setCount(1)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest3() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("1234")
                        .setResourceType(1)
                        .setCount(1)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest4() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
                        .setResourceType(1)
                        .setCount(1)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest5() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 400; i++) {
            stringBuilder.append(i);
        }
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("ааааааа")
                        .setDescription(stringBuilder.toString())
                        .setResourceType(1)
                        .setCount(1)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest6() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("111111")
                        .setResourceType(1)
                        .setCount(-1)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getResourceByTitleTest7() {
        assertThrows(IllegalArgumentException.class,
                () -> new ResourceImpl.ResourceBuilder()
                        .setTitle("ааааааа")
                        .setResourceType(1)
                        .setCount(-5)
                        .build()
        );
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getNodesTest() {
        long nodeId = -1;
        try {
            searchService.searchForResourceByNodeId(nodeId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Timeout(value = 10000, unit = TimeUnit.MILLISECONDS)
    void getNodesTest2() {
        try {
            long nodeId = 5;
            Resource resource = searchService.searchForResourceByNodeId(nodeId);
            assertNotNull(resource);
        } catch(DAOException e) {
            log.error(MessagesForException.TEST_ERROR + e.getMessage());
            fail();
        }
    }

}