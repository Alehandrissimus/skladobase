package com.tcpp.skladobase.model;

public interface Node {

    int getId();

    void setId(int id);

    String getPosition();

    void setPosition(String str);

    String getDescription();

    void setDescription(String str);

    Resource getResource();

    void setResource(Resource resource);

}
