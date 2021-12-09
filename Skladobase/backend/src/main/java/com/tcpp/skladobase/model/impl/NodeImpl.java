package com.tcpp.skladobase.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Node;
import com.tcpp.skladobase.model.Resource;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class NodeImpl implements Node, MessagesForException {

    @JsonProperty("id")
    private long id;
    @JsonProperty("position")
    private String position;
    @JsonProperty("description")
    private String description;
    @JsonProperty("resource")
    private Resource resource;


    public NodeImpl(){}

    public NodeImpl(String position, String description) {
        this.position = position;
        this.description = description;
    }

    public NodeImpl(String position, String description, Resource resource) {
        this.position = position;
        this.description = description;
        this.resource = resource;
    }

    public NodeImpl(long id, String position, String description) {
        this.id = id;
        this.position = position;
        this.description = description;
    }

    public NodeImpl(long id, String position, String description, Resource resource) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.resource = resource;
    }
    
    @JsonPOJOBuilder
    public static class NodeBuilder {

        private final NodeImpl node;

        public NodeBuilder() {
            this.node = new NodeImpl();
        }

        public NodeBuilder setId(long id) {
            if(id < 1) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            node.id = id;
            return this;
        }

        public NodeBuilder setPosition(String str) {
            String[] pos = str.split(",");
            if(Integer.parseInt(pos[0]) < 0 || Integer.parseInt(pos[1]) < 0) {
                throw new IllegalArgumentException(INVALID_POSITION);
            }
            node.position = str;
            return this;
        }

        public NodeBuilder setResource(Resource resource) {
            node.resource = resource;
            return this;
        }

        public NodeBuilder setDescription(String str) {
            node.description = str;
            return this;
        }

        public Node Build() {
            return node;
        }

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        if(id < 1) {
            throw new IllegalArgumentException(INVALID_ID);
        }
        this.id = id;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String str) {
        String[] pos = str.split(",");
        if(Integer.parseInt(pos[0]) < 0 || Integer.parseInt(pos[1]) < 0) {
            throw new IllegalArgumentException(INVALID_POSITION);
        }
        this.position = str;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String str) {
        if(StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(INVALID_DESC);
        }
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

    @Override
    public String toString() {
        return "NodeImpl{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", resource=" + resource +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeImpl node = (NodeImpl) o;
        return id == node.id &&
                Objects.equals(position, node.position) &&
                Objects.equals(description, node.description) &&
                Objects.equals(resource, node.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, description, resource);
    }
}
