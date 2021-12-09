package com.tcpp.skladobase.rest;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.impl.NodeImpl;
import com.tcpp.skladobase.model.impl.ResourceImpl;
import com.tcpp.skladobase.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.Collection;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    private SearchController(
            SearchService searchService
    ) {
        this.searchService = searchService;
    }

    public void setTestConnection() {
        try {
            searchService.setTestConnection();
        } catch (DAOConfigException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nodes")
    public Collection<Node> getAllNodes() {
        try {
            Collection<Node> nodes = searchService.getNodes();
            if(nodes.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return searchService.getNodes();
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createResource/{nodeId}")
    public void createResource(@RequestBody ResourceImpl res, @PathVariable long nodeId) {
        try {
            searchService.importResource(res, nodeId);
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getResource")
    public Resource getResourceByNode(@RequestBody NodeImpl node) {
        try {
            return searchService.searchForResourceByNodeId(node.getId());
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public Collection<Node> searchResource() {
        try {
            return searchService.searchResource("Resource 1");
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
