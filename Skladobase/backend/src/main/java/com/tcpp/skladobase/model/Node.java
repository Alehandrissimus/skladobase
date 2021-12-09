package com.tcpp.skladobase.model;

public interface Node {

    long getId();

    void setId(long id);

    String getPosition();

    void setPosition(String str);

    String getDescription();

    void setDescription(String str);

    Resource getResource();

    void setResource(Resource resource);

}
