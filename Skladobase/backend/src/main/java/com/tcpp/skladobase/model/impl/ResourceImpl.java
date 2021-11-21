package com.tcpp.skladobase.model.impl;

import com.tcpp.skladobase.model.Resource;
import com.tcpp.skladobase.model.ResourceType;

public class ResourceImpl implements Resource {

    private long id;
    private String title;
    private float count;
    private String description;
    private ResourceType resourceType;

    public ResourceImpl(long id, String title, float count, String description, ResourceType resourceType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.count = count;
        this.resourceType = resourceType;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id=id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String str) {
        this.title=str;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String str) {
        this.description=str;
    }

    @Override
    public float getCount() {
        return count;
    }

    @Override
    public void setCount(float count) {
        this.count=count;
    }

    @Override
    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void setResourceType(ResourceType resourceType) {
        this.resourceType=resourceType;
    }
}
