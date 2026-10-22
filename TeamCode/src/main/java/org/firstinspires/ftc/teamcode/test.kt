package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import kotlin.math.abs

@TeleOp(name = "Mecanum TeleOp")
class test : LinearOpMode() {

    // 1.0 normally. If the robot drives and strafes backwards, set to -1.0
    private val dir = 1.0

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        waitForStart()

        while (opModeIsActive()) {
            val speedMultiplier = 1.0

            val y = -gamepad1.left_stick_y.toDouble() * 1.1 * dir * speedMultiplier // toward intake
            val x = gamepad1.left_stick_x.toDouble() * dir * speedMultiplier        // robot's right
            val rx = gamepad1.right_stick_x.toDouble() * speedMultiplier

            val frontLeftPower = y - x + rx
            val backLeftPower = -y - x + rx
            val frontRightPower = -y - x - rx
            val backRightPower = y - x - rx

            val denominator = maxOf(
                abs(frontLeftPower), abs(backLeftPower),
                abs(frontRightPower), abs(backRightPower), 1.0
            )

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