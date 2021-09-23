package pie_chart_observer_ufu;

import pie_chart_observer_ufu.observer.Subject;

import javax.swing.*;
import java.util.Vector;


/**
 * Represents a vector of CourseRecords.
 */
public class CourseData {


    private final Vector<CourseRecord> courseData;
    private final Subject<Vector<CourseRecord>> courseRecordSubject;

    /**
     * Constructs a CourseData object
     */
    public CourseData(Subject<Vector<CourseRecord>> subject) {
        this.courseData = new Vector<>();
        this.courseRecordSubject = subject;
    }

    /**
     * Add a new CourseRecord object
     *
     * @param courseRecord the CourseRecord to be added
     */
    public void addCourseRecord(CourseRecord courseRecord) {
        boolean alreadyExists = false;
        for (int i = 0; i < courseData.size(); i++) {
            CourseRecord record = courseData.elementAt(i);
            if (record.name.equals(courseRecord.name)) {
                alreadyExists = true;
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Warning: Attempt to add new course with an already existing name",
                                "alert", JOptionPane.ERROR_MESSAGE);
                i = courseData.size(); // exit the loop
            }
        }
        if (!alreadyExists)
            this.courseData.addElement(courseRecord);


        this.courseRecordSubject.notifyObservers(this.getUpdate());
    }

    /**
     * Update an existing CourseRecord object
     *
     * @param subjectName   the name CourseRecord to be updated
     * @param numOfStudents the new number of students for this course
     */
    public void changeCourseRecord(String subjectName, int numOfStudents) {
        for (int i = 0; i < courseData.size(); i++) {
            CourseRecord record = courseData.elementAt(i);
            if (record.name.equals(subjectName)) {
                record.numOfStudents = numOfStudents;
                i = courseData.size();
            }
        }
        this.courseRecordSubject.notifyObservers(this.getUpdate());
    }

    /**
     * Return a copy of the vector of course data. Used by Observers to pull
     * data.
     *
     * @return vector of course data
     */
    public Vector<CourseRecord> getUpdate() {
        return new Vector<>(courseData);
    }


}