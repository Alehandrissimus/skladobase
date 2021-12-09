package com.tcpp.skladobase.model;

public interface Resource {

    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String str);

    String getDescription();

    void setDescription(String str);

    float getCount();

    void setCount(float count);

    ResourceType getResourceType();

    void setResourceType(int resourceType);

}
