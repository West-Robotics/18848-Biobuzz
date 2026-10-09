package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

import org.firstinspires.ftc.teamcode.wwwrapper
import kotlin.math.abs // <-- Added for absolute value math

@TeleOp(name = "Mecanum TeleOp")

/*class test(hardwareMap: HardwareMap, private val voltageMultiplier: Double = 1.0) {
    private val frontLeft = wwwrapper(hardwareMap, "frontLeft", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
    private val backLeft = wwwrapper(hardwareMap, "backLeft", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
    private val backRight = wwwrapper(hardwareMap, "backRight", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
    private val frontRight = wwwrapper (hardwareMap, "frontRight", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
    }*/

class test : LinearOpMode() { // Note: Capitalized class names are standard practice in Kotlin
    override fun runOpMode() {
        // Fix: Changed RUN_WITHOUT_ENCODERS to RUN_WITHOUT_ENCODER
        val frontleft = hardwareMap.get("a") as DcMotorEx
        frontleft.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        frontleft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontleft.direction = DcMotorSimple.Direction.FORWARD

        val frontright = hardwareMap.get("b") as DcMotorEx
        frontright.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        frontright.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        frontright.direction = DcMotorSimple.Direction.REVERSE

        val backleft = hardwareMap.get("c") as DcMotorEx
        backleft.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backleft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backleft.direction = DcMotorSimple.Direction.FORWARD

        val backright = hardwareMap.get("d") as DcMotorEx
        backright.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        backright.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        backright.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while (opModeIsActive()) {
            // SWYFT Tip: If the robot is too twitchy/fast, change 1.0 to something like 0.6 or 0.7
            val speedMultiplier = 1.0

            val y = -gamepad1.left_stick_y.toDouble() * speedMultiplier
            val x = gamepad1.left_stick_x.toDouble() * 1.1 * speedMultiplier // 1.1 counteracts strafe friction
            val rx = gamepad1.right_stick_x.toDouble() * speedMultiplier

            // Mecanum vector math
            val frontLeftPower = y + x + rx
            val backLeftPower = y - x + rx
            val frontRightPower = y - x - rx
            val backRightPower = y + x - rx

            // Normalizes vector inputs so they never exceed -1.0 to 1.0 bounds
            val denominator = maxOf(abs(frontLeftPower), abs(backLeftPower), abs(frontRightPower), abs(backRightPower), 1.0)

            frontleft.power = frontLeftPower / denominator
            backleft.power = backLeftPower / denominator
            frontright.power = frontRightPower / denominator
            backright.power = backRightPower / denominator
        }
    }
}
