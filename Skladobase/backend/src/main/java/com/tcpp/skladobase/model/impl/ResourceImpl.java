package com.tcpp.skladobase.model.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcpp.skladobase.exception.MessagesForException;
import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ResourceImpl implements Resource, MessagesForException {

    @JsonProperty("id")
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("count")
    private float count;
    @JsonProperty("description")
    private String description;
    @JsonProperty("resourceType")
    private ResourceType resourceType = ResourceType.CRUMBLY;

    public ResourceImpl() {}

    public ResourceImpl(long id, String title, float count, String description, int resourceType) {
        this.setId(id);
        this.setTitle(title);
        this.setDescription(description);
        this.setCount(count);
        this.setResourceType(resourceType);
    }


    public static class ResourceBuilder {

        private final ResourceImpl resource;

        public ResourceBuilder() {
            this.resource = new ResourceImpl();
        }

        public ResourceBuilder setId(long id) {
            if(id < 1) {
                throw new IllegalArgumentException(INVALID_ID);
            }
            resource.id=id;
            return this;
        }

        public ResourceBuilder setTitle(String str) {
            if(StringUtils.isBlank(str)) {
                throw new IllegalArgumentException(INVALID_TITLE);
            }
            if(str.length() < 6 || str.length() > 32) {
                throw new IllegalArgumentException(INVALID_TITLE);
            }
            resource.title=str;
            return this;
        }

        public ResourceBuilder setDescription(String str) {
            if(StringUtils.isBlank(str)) {
                throw new IllegalArgumentException(INVALID_DESC);
            }
            if(str.length() > 300) {
                throw new IllegalArgumentException(INVALID_DESC);
            }
            resource.description=str;
            return this;
        }

        public ResourceBuilder setCount(float count) {
            if(count < 0) {
                throw new IllegalArgumentException(INVALID_RESOURCE_COUNT);
            }
            resource.count=count;
            return this;
        }

        public ResourceBuilder setResourceType(int rt) {
            //resource.resourceType=ResourceType.convertToRole(rt);
            resource.resourceType=ResourceType.CRUMBLY;
            return this;
        }

        public Resource build() {
            return resource;
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
        this.id=id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String str) {
        if(StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(INVALID_TITLE);
        }
        if(str.length() < 6 || str.length() > 32) {
            throw new IllegalArgumentException(INVALID_TITLE);
        }
        this.title=str;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String str) {
        if(str.length() > 300) {
            throw new IllegalArgumentException(INVALID_DESC);
        }
        this.description=str;
    }

    @Override
    public float getCount() {
        return count;
    }

    @Override
    public void setCount(float count) {
        if(count < 0) {
            throw new IllegalArgumentException(INVALID_RESOURCE_COUNT);
        }
        this.count=count;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void setResourceType(int resourceType) {
        this.resourceType=ResourceType.convertToRole(resourceType);
    }

    @Override
    public String toString() {
        return "ResourceImpl{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", resourceType=" + resourceType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceImpl resource = (ResourceImpl) o;
        return id == resource.id &&
                Float.compare(resource.count, count) == 0 &&
                Objects.equals(title, resource.title) &&
                Objects.equals(description, resource.description) &&
                resourceType == resource.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, count, description, resourceType);
    }
}
