package org.firstinspires.ftc.teamcode.Vision;

import org.firstinspires.ftc.robotcore.external.Telemetry; // TODO: Integrate this file with the rest of the codebase

import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import org.openftc.easyopencv.OpenCvPipeline;


public class DetectMarker extends OpenCvPipeline {
    Telemetry telemetry;
    public enum MarkerLocation {
        LEFT,
        MIDDLE,
        RIGHT,
        NOT_FOUND
    }

    public enum SearchStatus {
        INITIALIZING,
        SEARCHING,
        FOUND
    }

    private Robot.AllianceColor allianceColor;
    private MarkerLocation markerLocation = MarkerLocation.NOT_FOUND;
    private SearchStatus searchStatus = SearchStatus.INITIALIZING;


    static final Rect LEFT_RECT = new Rect(
            new Point(60, 35),
            new Point(110, 75));

    static final Rect MIDDLE_RECT = new Rect(
            new Point(110, 35),
            new Point(150, 75));

    static final Rect RIGHT_RECT = new Rect(
            new Point(150, 35),
            new Point(200, 75));

    static double PERCENT_COLOR_THRESHOLD = 0.4;


    Mat mat = new Mat();

    public DetectMarker(Robot robot, Robot.AllianceColor ac) {
        telemetry = robot.getOpmode().telemetry;
        this.allianceColor = ac;
    }

    @Override
    public Mat processFrame(Mat input) {
        this.searchStatus = SearchStatus.SEARCHING;
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV); // TODO: Change COLOR_RGB2HSV to something more useful.
        Scalar lowHSV = new Scalar(23, 50, 70);
        Scalar highHSV = new Scalar(32, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat left = mat.submat(LEFT_RECT);
        Mat middle = mat.submat(MIDDLE_RECT);
        Mat right = mat.submat(RIGHT_RECT);

        double leftValue = Core.sumElems(left).val[0] / LEFT_RECT.area() / 255;
        double middleValue = Core.sumElems(left).val[0] / MIDDLE_RECT.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_RECT.area() / 255;

        left.release();
        middle.release();
        right.release();

        telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(left).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);

        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        boolean markerLeft = leftValue > PERCENT_COLOR_THRESHOLD;
        boolean markerMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        boolean markerRight = rightValue > PERCENT_COLOR_THRESHOLD;


        if (markerLeft) {
            markerLocation = MarkerLocation.LEFT;
            telemetry.addData("Marker Location", "right");
        } else if (markerMiddle) {
            markerLocation = MarkerLocation.MIDDLE;
            telemetry.addData("Marker Location", "middle");
        } else if (markerRight) {
            markerLocation = MarkerLocation.RIGHT;
            telemetry.addData("Marker Location", "left");
        } else {
            markerLocation = MarkerLocation.NOT_FOUND;
            telemetry.addData("Marker Location", "not found");
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB); // TODO: Change COLOR_GRAY2RGB to something more useful.

        Scalar colorNormal;

        if (this.allianceColor == Robot.AllianceColor.RED) {
            colorNormal = new Scalar(255, 0, 0);
        }
        else {
            colorNormal = new Scalar(0, 0, 255);
        }

        Scalar colorMarker = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, LEFT_RECT, markerLocation == MarkerLocation.LEFT ? colorMarker : colorNormal);
        Imgproc.rectangle(mat, MIDDLE_RECT, markerLocation == MarkerLocation.MIDDLE ? colorMarker : colorNormal);
        Imgproc.rectangle(mat, RIGHT_RECT, markerLocation == MarkerLocation.RIGHT ? colorMarker : colorNormal);

        this.searchStatus = SearchStatus.FOUND;
        return mat;
    }

    public MarkerLocation getMarkerLocation() {
        return markerLocation;
    }

    public SearchStatus getSearchStatus() {
        return searchStatus;
    }
}
