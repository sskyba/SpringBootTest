package TestAssignment;

public class RoamingCheck {

    private final String subscriberNumber;
    private final String location;

    public RoamingCheck(String subscriberNumber, String location) {
        this.subscriberNumber = subscriberNumber;
        this.location = location;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public String getLocation() {
        return location;
    }
}
