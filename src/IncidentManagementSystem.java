
import java.util.*;

class Incident {
    private int id;
    private String type;
    private String location;
    private String severity;
    private String status;
    private String reporterName;

    public Incident(int id, String type, String location, String severity, String status, String reporterName) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.severity = severity;
        this.status = status;
        this.reporterName = reporterName;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public String getReporterName() { return reporterName; }

    public void setStatus(String status) { this.status = status; }

    public void displayInfo() {
        System.out.println("Incident ID: " + id + ", Type: " + type + ", Location: " + location +
                ", Severity: " + severity + ", Status: " + status + ", Reporter: " + reporterName);
    }
}

public class IncidentManagementSystem {
    private static List<Incident> incidentList = new ArrayList<>();
    private static int incidentIdCounter = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Emergency Incident Reporting System ---");
            System.out.println("1. Report an Incident");
            System.out.println("2. View All Incidents");
            System.out.println("3. Search Incident by ID");
            System.out.println("4. Update Incident Status");
            System.out.println("5. Delete Incident");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: reportIncident(); break;
                case 2: viewIncidents(); break;
                case 3: searchIncident(); break;
                case 4: updateIncidentStatus(); break;
                case 5: deleteIncident(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void reportIncident() {
        System.out.print("Enter Incident Type (Fire/Flood/Earthquake/etc.): ");
        String type = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Severity (Low/Medium/High): ");
        String severity = scanner.nextLine();
        System.out.print("Enter Reporter Name: ");
        String reporterName = scanner.nextLine();

        Incident newIncident = new Incident(incidentIdCounter++, type, location, severity, "Reported", reporterName);
        incidentList.add(newIncident);
        System.out.println("Incident Reported Successfully!");
    }

    private static void viewIncidents() {
        if (incidentList.isEmpty()) {
            System.out.println("No incident records found.");
            return;
        }
        System.out.println("\n--- Incident Records ---");
        for (Incident incident : incidentList) {
            incident.displayInfo();
        }
    }

    private static void searchIncident() {
        System.out.print("Enter Incident ID to search: ");
        int id = scanner.nextInt();

        for (Incident incident : incidentList) {
            if (incident.getId() == id) {
                System.out.println("Incident Found:");
                incident.displayInfo();
                return;
            }
        }
        System.out.println("Incident Not Found.");
    }

    private static void updateIncidentStatus() {
        System.out.print("Enter Incident ID to update status: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Incident incident : incidentList) {
            if (incident.getId() == id) {
                System.out.print("Enter New Status (In Progress/Resolved): ");
                String status = scanner.nextLine();
                incident.setStatus(status);
                System.out.println("Incident Status Updated Successfully!");
                return;
            }
        }
        System.out.println("Incident Not Found.");
    }

    private static void deleteIncident() {
        System.out.print("Enter Incident ID to delete: ");
        int id = scanner.nextInt();

        Iterator<Incident> iterator = incidentList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                System.out.println("Incident Deleted Successfully!");
                return;
            }
        }
        System.out.println("Incident Not Found.");
    }
}
