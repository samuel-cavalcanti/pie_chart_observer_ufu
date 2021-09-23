package pie_chart_observer_ufu.chart;

import pie_chart_observer_ufu.CourseRecord;
import pie_chart_observer_ufu.LayoutConstants;
import pie_chart_observer_ufu.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


/**
 * This class represents a bar chart view of a vector of data. Uses the Observer
 * pattern.
 */

public class BarChartObserver extends JPanel implements Observer<Vector<CourseRecord>> {
    private Vector<CourseRecord> courseData;

    /**
     * Creates a BarChartObserver object
     */
    public BarChartObserver(Vector<CourseRecord> initialData) {
        this.courseData = initialData;
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.setBackground(Color.white);
    }

    /**
     * Paint method
     *
     * @param g a Graphics object on which to paint
     */
    public void paint(Graphics g) {
        super.paint(g);
        LayoutConstants.paintBarChartOutline(g, this.courseData.size());
        for (int i = 0; i < courseData.size(); i++) {
            CourseRecord record = courseData.elementAt(i);
            g.setColor(Color.blue);
            g.fillRect(
                    LayoutConstants.xOffset + (i + 1)
                            * LayoutConstants.barSpacing + i
                            * LayoutConstants.barWidth, LayoutConstants.yOffset
                            + LayoutConstants.graphHeight
                            - LayoutConstants.barHeight
                            + 2
                            * (LayoutConstants.maxValue - record
                            .numOfStudents),
                    LayoutConstants.barWidth, 2 * record.numOfStudents);
            g.setColor(Color.red);
            g.drawString(record.name,
                    LayoutConstants.xOffset + (i + 1)
                            * LayoutConstants.barSpacing + i
                            * LayoutConstants.barWidth, LayoutConstants.yOffset
                            + LayoutConstants.graphHeight + 20);
        }
    }

    @Override
    public void update(Vector<CourseRecord> e) {
        this.courseData = e;

        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.courseData.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }
}