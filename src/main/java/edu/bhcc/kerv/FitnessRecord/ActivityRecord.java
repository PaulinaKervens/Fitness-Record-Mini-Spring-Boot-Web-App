package edu.bhcc.kerv.FitnessRecord;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "record") // The table name
public class ActivityRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long activityID;          // The activity record
    private String routeName;       // The route name ran
    private double miles;           // Number of miles ran
    private LocalDate dateRunning;  // The activity date MM-DD-YYYY

    /**
     * Default (empty) constructor, used by JPA...
     */
    protected ActivityRecord() {
        new ActivityRecord("", 0.0, LocalDate.now());
    }

    /**
     * Parametrized constructor, used to create new object.
     * @param routeName The name of route ran into.
     * @param miles Number of miles ran.
     * @param date The activity date.
     */
    public ActivityRecord(final String routeName, final double miles, LocalDate date) {
        setMiles(miles);
        setRouteName(routeName.trim());
        setDate(date);
    }


    /**
     *
     * @param date The new value to initialise the attribute Date.
     */
    public void setDate(LocalDate date) throws RuntimeException {
        if (date != null) this.dateRunning = date;
        else throw new RuntimeException("Date cannot be null");
    }

    /**
     *
     * @param miles The new value to assign to the miles attribute.
     */
    public void setMiles(double miles) {
        this.miles = miles;
    }

    /**
     *
     * @param routeName The new value to assign to the routeName attribute.
     */
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Long getActivityID() { return activityID; }

    /**
     * Method that access to the miles attribute class member.
     * @return miles attribute value.
     */
    public double getMiles() { return miles; }

    /**
     * Method that access to the Route attribute class member.
     * @return Route attribute value.
     */
    public String getRouteName() { return routeName; }

    /**
     * Method that access to the Date attribute class member.
     * @return date attribute value
     */
    public LocalDate getDate() { return dateRunning; }

}
