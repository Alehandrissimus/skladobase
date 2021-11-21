package com.tcpp.skladobase.model.impl;

import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;

public class NodeImpl implements Node {

    private int id;
    private String position;
    private String description;
    private Resource resource;

    public NodeImpl(String position, String description) {
        this.position = position;
        this.description = description;
    }

    public NodeImpl(String position, String description, Resource resource) {
        this.position = position;
        this.description = description;
        this.resource = resource;
    }

    public NodeImpl(int id, String position, String description, Resource resource) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.resource = resource;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String str) {
        this.position = str;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String str) {
        this.description = str;
    }

    @Override
    public Resource getResource() {
        return resource;
    }

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
