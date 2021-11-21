package com.tcpp.skladobase.rest;

import com.tcpp.skladobase.dao.NodeDAO;
import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
            return searchService.getNodes();
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createResource")
    public void createResource(Resource res) {
        try {
            searchService.importResource(res);
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getResource")
    public void getResourceByNode(Node node) {
        try {
            searchService.searchForResourceByNodeId(node.getId());
        } catch (DAOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
