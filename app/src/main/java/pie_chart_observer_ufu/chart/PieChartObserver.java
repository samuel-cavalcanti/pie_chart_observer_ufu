package pie_chart_observer_ufu.chart;

import pie_chart_observer_ufu.CourseRecord;
import pie_chart_observer_ufu.LayoutConstants;
import pie_chart_observer_ufu.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class PieChartObserver extends JPanel implements Observer<Vector<CourseRecord>> {
    private Vector<CourseRecord> records;

    public PieChartObserver(Vector<CourseRecord> records) {
        this.records = records;
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.records.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.setBackground(Color.GRAY);
    }

    @Override
    public void update(Vector<CourseRecord> e) {
        records = e;
        this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
                + (LayoutConstants.barSpacing + LayoutConstants.barWidth)
                * this.records.size(), LayoutConstants.graphHeight + 2
                * LayoutConstants.yOffset));
        this.revalidate();
        this.repaint();
    }

/*
    Here is a code segment that draws a pie chart given a Graphics object and
    an Array containing Integers to be represented in the pie chart. It is drawn
    at location (xOffset, yOffset) and with the radius specified to be of size 100.
 */

    public void paint(Graphics g) {
        super.paint(g);
        int radius = 100;


        //first compute the total number of students
        double total = records.stream().map(record -> record.numOfStudents).reduce(0, Integer::sum).doubleValue();

        //if total == 0 nothing to draw

        if (total == 0)
            return;

        final int length = records.size();
        double startAngle = 0.0;
        for (int i = 0; i < length; i++) {
            double ratio = (records.get(i).numOfStudents / total) * 360.0;
            //draw the arc
            g.setColor(LayoutConstants.courseColours[i % LayoutConstants.courseColours.length]);
            g.fillArc(LayoutConstants.xOffset, LayoutConstants.xOffset / 10, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
            startAngle += ratio;

            g.drawString(records.get(i).name,
                    LayoutConstants.xOffset + (i + 1)
                            * LayoutConstants.barSpacing + i
                            * LayoutConstants.barWidth, LayoutConstants.yOffset
                            + LayoutConstants.graphHeight + 20);
        }

    }
}
