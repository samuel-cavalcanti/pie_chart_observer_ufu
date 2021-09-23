package pie_chart_observer_ufu;

/**
 * Represents the name of a course and the number of students taking it.
 */
public class CourseRecord {
    public String name;
    public int numOfStudents;

    public CourseRecord(String courseName, int numOfStudents) {
        this.name = courseName;
        this.numOfStudents = numOfStudents;
    }

    public String toString() {
        return "Course = " + this.name + ", Number of Students = "
                + this.numOfStudents;
    }

}