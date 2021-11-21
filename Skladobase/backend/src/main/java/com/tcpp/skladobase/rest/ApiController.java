package com.tcpp.skladobase.rest;

import com.tcpp.skladobase.exception.DAOConfigException;
import com.tcpp.skladobase.exception.DAOException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.services.NodeService;
import com.tcpp.skladobase.services.ResourceService;
import com.tcpp.skladobase.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ApiController {

    private final NodeService nodeService;
    private final ResourceService resourceService;

    @Autowired
    private ApiController(
            NodeService nodeService,
            ResourceService resourceService
    ) {
        this.nodeService = nodeService;
        this.resourceService = resourceService;
    }

    public void setTestConnection() throws DAOConfigException {
        nodeService.setTestConnection();
        resourceService.setTestConnection();
    }

    @PostMapping("/api/node/update")
    public void updateNode(Node node) {
        try {
            nodeService.updateNode(node);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/api/node/delete")
    public void deleteNode(Node node) {
        try {
            nodeService.deleteNode(node);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/api/node/create")
    public void createNode(Node node) {
        try {
            nodeService.createNode(node);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/api/resource/update")
    public void updateResource(Resource resource) {
        try {
            resourceService.updateResource(resource);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/api/resources/getAll")
    public Collection<Resource> getAllResources() {
        try {
            return resourceService.getAllResources();
        } catch (DAOException e) {
            return null;
        }
    }

    @PostMapping("/api/resource/create")
    public void createResource(Resource resource) {
        try {
            resourceService.createResource(resource);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/api/resource/delete")
    public void deleteResource(Resource resource) {
        try {
            resourceService.deleteResource(resource);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
