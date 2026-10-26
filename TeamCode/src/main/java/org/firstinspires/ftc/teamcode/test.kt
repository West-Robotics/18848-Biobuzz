package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.IMU
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin


@TeleOp(name = "Field centric 1 TeleOp")
class test : LinearOpMode() {

    private val dir = 1.0

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        val imu = hardwareMap.get<IMU?>(IMU::class.java, "imu")

        val parameters = IMU.Parameters(
            RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
            )
        )

        imu.initialize(parameters)

        waitForStart()

        while (opModeIsActive()) {
            val speedMultiplier = 1.0

            val stickY = -gamepad1.left_stick_y.toDouble() * 1.1 * dir * speedMultiplier
            val stickX = gamepad1.left_stick_x.toDouble() * dir * speedMultiplier
            val rx = gamepad1.right_stick_x.toDouble() * speedMultiplier

            val botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS)



            // Rotate the movement direction counter to the bot's rotation
            val rotX = stickX * cos(-botHeading) - stickY * sin(-botHeading)
            val rotY = stickX * sin(-botHeading) + stickY * cos(-botHeading)

            val y = rotY * 1.1 * dir
            val x = rotX * dir

            val frontLeftPower = y + x + rx
            val backLeftPower = y - x + rx
            val frontRightPower = y + x - rx
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