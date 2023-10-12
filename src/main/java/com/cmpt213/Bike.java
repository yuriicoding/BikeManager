package com.cmpt213;


/**
 * @author Yurii Huba
 * @version 1.0
 * <p>
 * The Bike class represents a bicycle with these attributes: 
 * <br> - owner
 * <br> - wheel size
 * <br> - brake type
 * <br> - type 
 * <br> - serial number
 * <br>It provides methods to access and modify these attributes.
 * And special method toString() that combines into 1 string all
 * attributes and package of each recorded bike.
 * <p>
 */
class Bike {

    private static int nextBikeID = 1;

    private int bikeID;
    private String owner;
    private int wheelSize;
    private String brakeType;
    private String type;
    private String serialNumber;

    public Bike(String owner, int wheelSize, String brakeType, String type, String serialNumber) {
        this.bikeID = nextBikeID++;
        this.owner = owner;
        this.wheelSize = (int) wheelSize;
        this.brakeType = brakeType;
        this.type = type;
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        // Get the class name
        String className = getClass().getName();

        // Get the package name from the class name
        int lastDotIndex = className.lastIndexOf('.');
        String packageName = lastDotIndex != -1 ? className.substring(0, lastDotIndex) : "";

        // Return the formatted string
        return packageName + "." + className + "[Id:" + bikeID + ", Owner:" + owner + ", type:" + type + ", Serial:"
                + serialNumber
                + ", Brake:" + brakeType + ", WheelSize:" + wheelSize + "]";
    }

    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public String getBrakeType() {
        return brakeType;
    }

    public void setBrakeType(String brakeType) {
        this.brakeType = brakeType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
