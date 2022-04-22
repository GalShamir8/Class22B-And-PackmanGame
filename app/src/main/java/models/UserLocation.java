package models;

public class UserLocation {
    private String addressName;
    private double longitude;
    private double latitude;

    public UserLocation() {
        setLongitude(0);
        setLatitude(0);
        setAddressName("UNKNOWN");
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAddressName(String addressName){ this.addressName = addressName; }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

}
