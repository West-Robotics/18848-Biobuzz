package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name = "Yes ")
class yeehaw : LinearOpMode() {
    override fun runOpMode(){

        val frontleft = hardwareMap.get("a") as DcMotorEx
        frontleft.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        frontleft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontleft.direction = DcMotorSimple.Direction.FORWARD
        val frontright = hardwareMap.get("b") as DcMotorEx
        frontright.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        frontright.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontright.direction = DcMotorSimple.Direction.REVERSE
        val backleft = hardwareMap.get("c") as DcMotorEx
        backleft.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        backleft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backleft.direction = DcMotorSimple.Direction.FORWARD
        val backright = hardwareMap.get("d") as DcMotorEx
        backright.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        backright.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backright.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while(opModeIsActive()){

        }
    }
}