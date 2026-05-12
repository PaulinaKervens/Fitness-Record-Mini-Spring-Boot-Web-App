package edu.bhcc.kerv.FitnessRecord;

import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;

/**
 * Comment Create/Update/Read/Delete Repository.
 */
public interface ActivityRepository extends CrudRepository<ActivityRecord, Long> {
    /**
     * Find activity by activityId.
     * @param activityID The activityId target to find.
     * @return An activityRecord object that has been matched.
     */
    Record findByActivityID(long activityID);

    /**
     * Find activity by route name.
     * @param routeName Route name target to find.
     * @return A record that matched.
     */
    Record findByRouteName(String routeName);

    /**
     * Find activity by date.
     * @return A record object that matched.
     */
    Record findByDate(LocalDate date);

}
