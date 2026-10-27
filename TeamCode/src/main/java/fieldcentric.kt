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

@TeleOp(name = "Field Centric TeleOp")
class FieldCentric : LinearOpMode() {

    private val dir = 1.0

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        val imu = hardwareMap.get(IMU::class.java, "imu")
        imu.initialize(
            IMU.Parameters(
                RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                    RevHubOrientationOnRobot.UsbFacingDirection.DOWN
                )
            )
        )

        telemetry.addLine("Point the robot the way you want to be 'forward', then press Start. Options resets heading.")
        telemetry.update()
        waitForStart()
        imu.resetYaw()

        while (opModeIsActive()) {
            if (gamepad1.options) imu.resetYaw()

            val heading = imu.robotYawPitchRollAngles.getYaw(AngleUnit.RADIANS)

            val stickY = -gamepad1.left_stick_y.toDouble()
            val stickX = gamepad1.left_stick_x.toDouble()
            val rx = gamepad1.right_stick_x.toDouble()

            // rotate the stick vector by -heading
            val rotX = stickX * cos(heading) + stickY * sin(heading)
            val rotY = -stickX * sin(heading) + stickY * cos(heading)

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

            telemetry.addData("Heading (deg)", Math.toDegrees(heading))
            telemetry.update()
        }
    }
}