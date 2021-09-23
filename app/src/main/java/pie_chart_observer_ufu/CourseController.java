package pie_chart_observer_ufu;

import pie_chart_observer_ufu.observer.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


/**
 * Presents a given view of a set of courses and their marks. Uses the Observer
 * pattern to be notified when a new course has been added.
 */

public class CourseController extends JPanel implements Observer<Vector<CourseRecord>>, ChangeListener, ActionListener {

    private CourseData courseData;

    private Vector<JSlider> sliders;

    private JPanel coursePanel;

    /**
     * Constructs a CourseController object
     *
     * @param courses a set of courses and their marks
     */
    public CourseController(CourseData courses) {
        this.courseData = courses;
        this.sliders = new Vector<>();
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);

        coursePanel = new JPanel();
        coursePanel.setBorder(new TitledBorder("Courses"));
        coursePanel.setLayout(new GridLayout(0, 1));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        Vector<CourseRecord> state = courses.getUpdate();

        for (int i = 0; i < state.size(); i++)
            this.addCourse(state.elementAt(i));

        JScrollPane scrollPane = new JScrollPane(coursePanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JButton button = new JButton("New Courses");
        button.addActionListener(this);

        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(scrollPane, constraints);

        constraints.weightx = 0.5;
        constraints.weighty = 0;
        constraints.gridy = 1;
        this.add(button, constraints);
    }

    /**
     * Add a new course
     *
     * @param record the new course record to be added
     */
    public void addCourse(CourseRecord record) {
        JSlider slider = new JSlider();
        slider.setBackground(Color.white);
        slider.setName(record.name);
        slider.setValue(record.numOfStudents);
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(new TitledBorder(record.name));
        slider.addChangeListener(this);
        coursePanel.add(slider);
        coursePanel.revalidate();
        sliders.addElement(slider);
    }

    /**
     * Manages the creation of a new course. Called when the "New Course" button is pressed.
     *
     * @param arg0 not used
     */
    public void actionPerformed(ActionEvent arg0) {
        String input = JOptionPane.showInputDialog("Please enter new course name:");
        if (input != null) {
            courseData.addCourseRecord(new CourseRecord(input, 50));
            // leave it up notify/update mechanism to invoke this.addCourse
        }
    }

    /**
     * Handles the changing of the marks for a course (changing of a JSlider)
     *
     * @param arg0 the JSlider that has changed
     */
    public void stateChanged(ChangeEvent arg0) {
        JSlider slider = (JSlider) arg0.getSource();
        courseData.changeCourseRecord(slider.getName(), slider.getValue());
    }




    @Override
    public void update(Vector<CourseRecord> e) {

        for (int i = sliders.size(); i < e.size(); i++) {
            this.addCourse(e.elementAt(i));
        }
    }
}