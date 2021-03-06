package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Date;

@Autonomous(name="BasicAutonomous")
public class basicAutonomous extends OpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    long waitTime = 0;
    long nextWaitTime = 0;
    int phase = 0;

    public void basicAutonomous() {

    }

    @Override
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("leftMotor");
        rightDrive = hardwareMap.dcMotor.get("rightMotor");
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        telemetry.addData("Status:", "Loaded variables and ready for loop");
        telemetry.update();
    }

    public void loop() {
        Date date = new Date();
        long timeMilli = date.getTime();
        if (waitTime == 0) {
            waitTime = date.getTime()+nextWaitTime;
        } else {
            if (timeMilli >= waitTime) {
                switch (phase) {
                    case 0:
                        leftDrive.setPower(1.0);
                        rightDrive.setPower(1.0);
                        nextWaitTime = 2000;
                        break;
                    case 1:
                        leftDrive.setPower(-1.0);
                        nextWaitTime = 1000;
                        break;
                    case 2:
                        leftDrive.setPower(1.0);
                        nextWaitTime = 2000;
                        break;
                    case 3:
                        leftDrive.setPower(0.0);
                        rightDrive.setPower(0.0);
                        break;
                    default:
                        break;
                }
                waitTime = 0;
                phase++;
            }
        }
    }
}
