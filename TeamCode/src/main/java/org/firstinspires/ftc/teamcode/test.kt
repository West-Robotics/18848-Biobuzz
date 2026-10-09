package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import kotlin.math.abs

@TeleOp(name = "Mecanum TeleOp")
class MecanumTeleOp : LinearOpMode() {
    override fun runOpMode() {

        val frontLeft = wwwrapper(hardwareMap, "a", Direction.FORWARD, ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "b", Direction.REVERSE, ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "c", Direction.FORWARD, ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "d", Direction.REVERSE, ZeroPowerBehavior.BRAKE)

        waitForStart()

        while (opModeIsActive()) {
            val speedMultiplier = 1.0

            val y = -gamepad1.left_stick_y.toDouble() * speedMultiplier
            val x = gamepad1.left_stick_x.toDouble() * 1.1 * speedMultiplier
            val rx = gamepad1.right_stick_x.toDouble() * speedMultiplier


            val frontLeftPower = y + x + rx
            val backLeftPower = y - x + rx
            val frontRightPower = y - x - rx
            val backRightPower = y + x - rx


            val denominator = maxOf(abs(frontLeftPower), abs(backLeftPower), abs(frontRightPower), abs(backRightPower), 1.0)


            frontLeft.effort = frontLeftPower / denominator
            backLeft.effort = backLeftPower / denominator
            frontRight.effort = frontRightPower / denominator
            backRight.effort = backRightPower / denominator


            frontLeft.write()
            backLeft.write()
            frontRight.write()
            backRight.write()
        }
    }
}