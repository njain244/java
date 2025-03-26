import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParkingSpace {
    private String spaceId;
    private double latitude;
    private double longitude;
    private boolean isOccupied;
    private LocalDateTime lastOccupiedTime;
    private LocalDateTime lastVacatedTime;
    private double ratePerHour;

    // Constructor for ParkingSpace class
    public ParkingSpace(String spaceId, double latitude, double longitude, double ratePerHour) {
        this.spaceId = spaceId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isOccupied = false;
        this.ratePerHour = ratePerHour;
    }

    // Main method - currently empty
    public static void main(String[] args) {
        // You can add test code here to create an instance of ParkingSpace
        ParkingSpace space1 = new ParkingSpace("A1", 28.6128, 77.2295, 20.0);
        System.out.println(space1); // Example usage of toString() method
    }

    // Getter and setter methods for all attributes can be added if needed
    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public LocalDateTime getLastOccupiedTime() {
        return lastOccupiedTime;
    }

    public void setLastOccupiedTime(LocalDateTime lastOccupiedTime) {
        this.lastOccupiedTime = lastOccupiedTime;
    }

    public LocalDateTime getLastVacatedTime() {
        return lastVacatedTime;
    }

    public void setLastVacatedTime(LocalDateTime lastVacatedTime) {
        this.lastVacatedTime = lastVacatedTime;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    // Method to mark the parking space as occupied
    public void occupy() {
        this.isOccupied = true;
        this.lastOccupiedTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata")); // Using Gwalior's timezone
        this.lastVacatedTime = null;
    }

    // Method to represent the ParkingSpace object as a string
    @Override
    public String toString() {
        return "ParkingSpace{" +
                "spaceId='" + spaceId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
