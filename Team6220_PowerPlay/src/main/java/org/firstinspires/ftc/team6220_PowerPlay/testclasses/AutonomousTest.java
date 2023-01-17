package org.firstinspires.ftc.team6220_PowerPlay.testclasses;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team6220_PowerPlay.BaseAutonomous;
import org.firstinspires.ftc.team6220_PowerPlay.Constants;

//@Disabled
@Autonomous(name = "AutonomousTest", group = "Test")
public class AutonomousTest extends BaseAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        initializeCameras(Constants.LOWER_YELLOW, Constants.UPPER_YELLOW);

        waitForStart();

        centerJunctionTop(grabberCameraPipeline);
    }
}
