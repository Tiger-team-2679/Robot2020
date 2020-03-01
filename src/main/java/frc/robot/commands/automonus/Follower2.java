//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package frc.robot.commands.automonus;

import team2679.core.controller.PID;
import team2679.core.graph.Graph;

public class Follower2 {
    private Graph velGraph;
    private Graph headGraph;
    private PID headPid;
    private PID velPid;

    public Follower2(Graph velGraph, Graph headGraph,PID velPid,PID headPid) {
        this.velGraph = velGraph;
        this.headGraph = headGraph;
        this.headPid = headPid;
        this.velPid = velPid;
    }

    public double[] update(double currentTime, double currentVel, double currentHeading) {
        double targetVel = this.velGraph.value(currentTime);
        double targetHead = this.headGraph.derivative(currentTime);
        double velChange = this.velPid.update(currentTime, currentVel, targetVel);
        double headChange = this.headPid.update(currentTime, currentHeading, targetHead);

        return new double[]{targetVel, velChange, headChange};
    }
}
