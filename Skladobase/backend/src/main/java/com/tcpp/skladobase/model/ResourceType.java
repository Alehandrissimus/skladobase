package com.tcpp.skladobase.model;

public enum ResourceType {

    INTACT,
    CRUMBLY,
    FLUID;

    public static ResourceType convertToRole(int typeId) {
        switch (typeId) {
            case 0:
                return INTACT;
            case 1:
                return CRUMBLY;
            case 2:
                return FLUID;
            default:
                throw new IllegalArgumentException("Convert to role received invalid argument roleId = " + typeId);
        }
    }

}
