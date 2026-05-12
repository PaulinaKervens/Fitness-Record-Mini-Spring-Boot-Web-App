package edu.bhcc.kerv.FitnessRecord;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class.
 */
@Controller
public class ActivityController {
    private ActivityRepository activityRepository;

    /**
     * Parametrized constructor to create an Activity Controller.
     * @param activityRepository
     */
    public ActivityController(final ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return displayActivityRecord(model);
    }

    @PostMapping("/add_activity_record")
    private String addNewActivityRecord(String route, double miles, LocalDate date, Model model) {
        ActivityRecord activityRecord = new ActivityRecord(route, miles, date);     // Create a new activity object
        if (valid(activityRecord)) {
            activityRepository.save(activityRecord);  // Query to insert a new record to the db
            model.addAttribute("success", "success");
            model.addAttribute("message", "A new Activity has been added to the tracker app.");
        } else {
            model.addAttribute("danger", "danger");
            model.addAttribute("message", "All fields must be filled.");
        }
        return displayActivityRecord(model);
    }

    /**
     * Method that send all record data to the Thymeleaf.
     * @param model The model.
     * @return The index.html webpage.
     */
    private String displayActivityRecord(Model model) {
        List<ActivityRecord> activityRecordsList = getAllActivity();
        // Check if the list is empty to add a default msg to the table
        if (activityRecordsList.isEmpty()) {
            model.addAttribute("empty", true);
            model.addAttribute("message", "You haven't added any activity record yet.");
        } else {
            model.addAttribute("hasRecord", true);
            model.addAttribute("activityRecordsList", activityRecordsList);
        }
        return "index";
    }

    /**
     * Method that get all activity record from the DataBase.
     * @return A List of ActivityRecord.
     */
    private List<ActivityRecord> getAllActivity() {
        List<ActivityRecord> activityRecordsList = new ArrayList<>();
        for (ActivityRecord activity : activityRepository.findAll()) {
            activityRecordsList.add(activity);
        }
        return activityRecordsList;
    }

    /**
     * Method that double check an Activity Record before execute query.
     * @param activityRecord An object from activity.
     * @return True / False
     */
    private boolean valid(ActivityRecord activityRecord) {
        return (
                activityRecord.getDate() != null &&
                activityRecord.getRouteName() != null &&
                !activityRecord.getRouteName().trim().isEmpty() &&
                activityRecord.getMiles() >= 1.0
        );
    }

}
