package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.Thread;


/**
 * Created by Mark on 3/21/2017.
 */
public class RobotCommon {
    Servo doorServo, liftServo;
    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    String ProgramVersion;




    public RobotCommon() {

    }
public void intHardware(LinearOpMode op)
{
    leftMotor = op.hardwareMap.dcMotor.get("left_motor");
    rightMotor = op.hardwareMap.dcMotor.get("right_motor");
    intakeMotor = op.hardwareMap.dcMotor.get("intake_motor");
    shooterMotor = op.hardwareMap.dcMotor.get("shooter_motor");
    liftMotor1 = op.hardwareMap.dcMotor.get("lift_motor1");
    liftMotor2 = op.hardwareMap.dcMotor.get("lift_motor2");
    doorServo = op.hardwareMap.servo.get("door_servo");
    //beaconServo = hardwareMap.servo.get("beacon_servo");
    //beaconColor = hardwareMap.colorSensor.get("beacon_color");
    liftServo = op.hardwareMap.servo.get("lift_servo");
    rightMotor.setDirection(DcMotor.Direction.REVERSE);
    shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    ProgramVersion = "MRB_3_24_2017_2";
}
    public void liftServoIn() {
        liftServo.setPosition(0.5);
    }

    public void liftServoOut() {
        liftServo.setPosition(Servo.MIN_POSITION);
    }

    public void chooperClose() {
        doorServo.setPosition(Servo.MAX_POSITION);
    }

    public void chooperOpen() {
        doorServo.setPosition(Servo.MIN_POSITION);
    }

    public void intakeIn() {
        intakeMotor.setPower(1);
    }

    public void intakeOut() {

        intakeMotor.setPower(-1);
    }

    public void intakeStop() {

        intakeMotor.setPower(0);
    }

    public void intakeJog(LinearOpMode op) {
        intakeMotor.setPower(-1);
        op.sleep(20);
        intakeMotor.setPower(1);
    }

    public void fire() {
        shooterMotor.setPower(-1);
        while (shooterMotor.getCurrentPosition() <= 1000);
        shooterMotor.setPower(0);
    }


    public void ceaseFire() {
        shooterMotor.setPower(0);
    }

    public void liftUp() {
        liftServoIn();
        liftMotor1.setPower(1);
        liftMotor2.setPower(1);
    }

    public void liftDown() {
        liftMotor1.setPower(-0.75);
        liftMotor2.setPower(-0.75);
    }

    public void liftStop() {
        liftMotor1.setPower(0);
        liftMotor2.setPower(0);

    }
public float  Slewdrive(float stick){
    float output;
    float slew;
    float change;

    if (stick != 0) {
        if (stick <= .2 && stick >= -0.2)
            output = 0;
        else
            output = stick;
    } else {
        output = stick;
    }
    float newstick = output;
    float oldstick = newstick;
    change = java.lang.Math.abs(output) - java.lang.Math.abs(oldstick);
    if (output == 0) {
        newstick = 0;
    }
    else if (output >= 0)
    {
        if (change >= 0.05)
        {
            newstick = oldstick + (float) 0.05;
        }
        else if (change <= -0.05)
        {
            newstick = oldstick - (float) 0.05;
        }
        else newstick = output;
    }
    else if (output <= 0)
    {
        if (change >= 0.05)
        {
            newstick = oldstick - (float) 0.05;
        }
        else if (change <= -0.05)
        {
            newstick = oldstick + (float) 0.05;
        }
        else
        {
            newstick = output;
        }
    }
    else
        newstick = output;
    oldstick = newstick;
    return newstick;
}
    public void tankdrive(float left, float right, boolean corrected)
    {
        float leftOutput, rightOutput;
        if (left != 0) {
            if (left <= .2 && left >= -0.2)
                leftOutput = 0;
            else
                leftOutput = left;
        } else {
            leftOutput = left;
        }
        if (right != 0) {
            if (right <= .2 && right >= -0.2)
                rightOutput = 0;
            else
                rightOutput = right;
        } else {
            rightOutput = right;
        }

        if (corrected)
        {
            leftMotor.setPower(Slewdrive(leftOutput));
            rightMotor.setPower(Slewdrive(rightOutput));
        }
        else
        {
            leftMotor.setPower(leftOutput);
            rightMotor.setPower(rightOutput);
        }
    }
}

