package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import kotlin.math.abs

@TeleOp(name = "Mecanum TeleOp")
class Test : LinearOpMode() {
    override fun runOpMode() {
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


        while (opModeIsActive()) {

            val y = -gamepad1.left_stick_y.toDouble()
            val x = gamepad1.left_stick_x.toDouble() * 1.1
            val rx = gamepad1.right_stick_x.toDouble()


            val frontLeftPower = y + x + rx
            val backLeftPower = y - x + rx
            val frontRightPower = y - x - rx
            val backRightPower = y + x - rx


            val denominator = maxOf(abs(frontLeftPower), abs(backLeftPower), abs(frontRightPower), abs(backRightPower), 1.0)


            frontleft.power = frontLeftPower / denominator
            backleft.power = backLeftPower / denominator
            frontright.power = frontRightPower / denominator
            backright.power = backRightPower / denominator
    }
}}
