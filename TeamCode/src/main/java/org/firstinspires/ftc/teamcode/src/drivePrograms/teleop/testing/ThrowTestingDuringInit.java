package org.firstinspires.ftc.teamcode.src.drivePrograms.teleop.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.src.utills.TeleOpTemplate;

@Disabled
@Autonomous(name = "throwTest")
/**
 * A Autonomous to test that utility in {@link org.firstinspires.ftc.teamcode.src.utills.GenericOpModeTemplate} for extra info from stack traces works properly
 */
public class ThrowTestingDuringInit extends TeleOpTemplate {
    public void opModeMain() throws InterruptedException {
        this.initAll();
    }
}
