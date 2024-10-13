package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class MoveForwardPractice extends LinearOpMode{
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() {
        // Initialize the motors
        leftFrontDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        leftBackDrive = hardwareMap.get(DcMotor.class, "rearLeft");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontRight");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rearRight");

        // TODO Map the top hat

        // Set the directions for the motors
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Set up the telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Waiting for start
        waitForStart();
        runtime.reset();
        //TODO Check button state
        //TODO Do appropriate movement for button state

        
    }
}
