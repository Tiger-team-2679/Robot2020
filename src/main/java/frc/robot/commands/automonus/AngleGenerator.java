//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frc.robot.commands.automonus;

import java.util.ArrayList;

import team2679.core.graph.IntervalGraph;
import team2679.core.spline.SplineWrapper;
import team2679.core.spline.WayPoint;

public class AngleGenerator{
    public AngleGenerator() {
    }

    public IntervalGraph<Double> generate(SplineWrapper spline, IntervalGraph<Double> speed) {
        ArrayList<Double> headings = new ArrayList();

        headings.add(0.0);
        for(int i = 1; i < speed.list.size(); ++i) {
            double dY = spline.getIntervalGraph().list.get(i).y - spline.getIntervalGraph().list.get(i-1).y;
            double dX = spline.getIntervalGraph().list.get(i).x - spline.getIntervalGraph().list.get(i-1).x;
            headings.add(Math.atan2(dY, dX));
        }

        return new IntervalGraph(headings, speed.step);
    }
}
