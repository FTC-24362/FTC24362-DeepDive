package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Components.BasicChassis;
import org.firstinspires.ftc.teamcode.Robot;
//@Disabled

@Autonomous(name= "BlueRightP", preselectTeleOp = "OneGPTeleop")
public class BlueRightP extends LinearOpMode {
    @Override
    public void runOpMode(){
        Robot robot = new Robot(this, BasicChassis.ChassisType.ENCODER, false, false,90);
//       robot.rotateToPosition(-15);
//       robot.rotateToPosition(-7.5);
        sleep(1000);
//        double[] turretTarget = {12+10.6,-24+16.2,0}; //{hubx-position*3/2,huby-position*3/2,1+7*position}
        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }
        robot.setPosition(0,0,0);
//        robot.toggleTSEPosition();
//        robot.rotateToPosition(-80);
        robot.rotateToPosition(-88);
        sleep(500);
//        int position = robot.BlueElemTest(this,0,0);
        waitForStart();
        robot.tseToPosition(0.6);
        resetStartTime();
        robot.rotateToPosition(-9);
        sleep(2000);
        int position = robot.BlueElemTest(this,0,0);
        while(getRuntime()<8){
            sleep(100);
        }
        position=2-position;
        if(position==1) {
            robot.TurretSlidesToPosition(-9.5, 5, 0, 0.5);
            robot.goToPosition(0, -20, 0, 0, 0.5);
            robot.FlipBasketArmToPosition(0.6);
            sleep(900);
            robot.FlipBasketToPosition(0.2);
            sleep(400);
        }
        else if (position==0) {
            robot.TurretSlidesToPosition(-8, 4.5, 0, 0.5);
            robot.goToPosition(0, -20, 0, 0, 0.5);
            robot.FlipBasketArmToPosition(0.8);
            sleep(900);
            robot.FlipBasketToPosition(0.2);
            sleep(500);
        }
        else {
            robot.TurretSlidesToPosition(-16.0, 9.5, 2, 0.5);
            robot.goToPosition(0, -20, 0, 0, 0.5);
            robot.FlipBasketArmToPosition(0.4);
            sleep(800);
            robot.FlipBasketToPosition(0.2);
            sleep(300);
        }
        robot.TurretSlidesToPosition(0 ,0,0,0.5);
        robot.FlipBasketArmToPosition(0.0);
        robot.turnInPlace(-60,1.0);
        robot.FlipBasketToPosition(0.8);
        robot.goToPosition(1,-7,-17,-42,0.5);
        robot.setMotorPowers(0.1);
        robot.spinCarouselAutonomousBlue();

        robot.goToPosition(0,-28,-20,0,0.5);
        robot.rotateToPosition(-90);
        sleep(3000);

        stop();
    }
}