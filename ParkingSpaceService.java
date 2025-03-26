import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingSpaceService {
    private final Map<String, ParkingSpace> parkingSpaces = new ConcurrentHashMap<>();

    public ParkingSpaceService() {
        // Initialize some parking spaces (replace with database interaction in a real app)
        parkingSpaces.put("PS001", new ParkingSpace("PS001", 26.2183, 78.1828, 20.0)); // Example Gwalior coordinates
        parkingSpaces.put("PS002", new ParkingSpace("PS002", 26.2190, 78.1835, 25.0));
        parkingSpaces.put("PS003", new ParkingSpace("PS003", 26.2175, 78.1820, 15.0));
    }

    public List<ParkingSpace> getAllParkingSpaces() {
        return new ArrayList<>(parkingSpaces.values());
    }

    public ParkingSpace getParkingSpace(String spaceId) {
        return parkingSpaces.get(spaceId);
    }

    public List<ParkingSpace> getAvailableParkingSpaces(double latitude, double longitude, double radius) {
        // In a real application, you'd use spatial queries on your database
        return parkingSpaces.values().stream()
                .filter(space -> !space.isOccupied() &&
                                 calculateDistance(latitude, longitude, space.getLatitude(), space.getLongitude()) <= radius)
                .collect(Collectors.toList());
    }

    public void updateOccupancy(String spaceId, boolean isOccupied) {
        ParkingSpace space = parkingSpaces.get(spaceId);
        if (space != null) {
            if (isOccupied) {
                space.occupy();
            } else {
                space.vacate();
            }
        } else {
            System.out.println("Parking space with ID " + spaceId + " not found.");
        }
    }

    // Simple Haversine formula for distance calculation (replace with more robust spatial libraries)
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // Convert to meters
    }
    
    public static void main(String[] args) {
        ParkingSpaceService service = new ParkingSpaceService();
        
        // Test update occupancy
        service.updateOccupancy("PS001", true); // Should mark PS001 as occupied
        service.updateOccupancy("PS004", false); // Should print "Parking space with ID PS004 not found."
        
        // Test get available parking spaces
        List<ParkingSpace> availableSpaces = service.getAvailableParkingSpaces(26.2183, 78.1828, 500); // Test with some coordinates
        System.out.println("Available Spaces: " + availableSpaces.size());
    }
}

class ParkingSpace {
    private final String id;
    private final double latitude;
    private final double longitude;
    private final double radius;
    private boolean occupied;

    public ParkingSpace(String id, double latitude, double longitude, double radius) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        this.occupied = false;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        this.occupied = true;
    }

    public void vacate() {
        this.occupied = false;
    }
}
