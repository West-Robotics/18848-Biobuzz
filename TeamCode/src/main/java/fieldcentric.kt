package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

val GoBildaPinpointDriver.heading: Double
    get() = getHeading(AngleUnit.RADIANS)

@TeleOp(name = "Field Centric TeleOp")
class FieldCentric : LinearOpMode() {

    private val dir = 1.0

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        val intake = Intake(hardwareMap)
        val flywheel = Flywheel(hardwareMap)
        val gate = Gate(hardwareMap)

        val pinpoint = hardwareMap.get(GoBildaPinpointDriver::class.java, "pinpoint")
        pinpoint.recalibrateIMU()
        pinpoint.resetPosAndIMU()

        telemetry.addLine("Pinpoint Initialized. Point forward and press Start. Options resets heading.")
        telemetry.update()

        waitForStart()
        pinpoint.resetPosAndIMU()

        while (opModeIsActive()) {

            pinpoint.update()

            if (gamepad1.options) {
                pinpoint.resetPosAndIMU()
            }

            val heading = pinpoint.heading

            val stickY = -gamepad1.left_stick_y.toDouble()
            val stickX = gamepad1.left_stick_x.toDouble()
            val rx = gamepad1.right_stick_x.toDouble()

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

            intake.power = gamepad1.right_trigger.toDouble() - gamepad1.left_trigger.toDouble()

            if (gamepad1.right_bumper) {
                flywheel.targetRpm = 3500.0
            } else {
                flywheel.targetRpm = 0.0
            }

            if (gamepad1.left_bumper) {
                gate.goTarget()
            } else {
                gate.goHome()
            }

            frontLeft.write()
            backLeft.write()
            frontRight.write()
            backRight.write()
            intake.write()
            flywheel.write()
            gate.write()

            telemetry.addData("Heading (deg)", Math.toDegrees(heading))
            telemetry.addData("Pinpoint Status", pinpoint.deviceStatus)
            telemetry.addData("Intake power", intake.power)
            telemetry.addData("Flywheel Target RPM", flywheel.targetRpm)
            telemetry.addData("Flywheel Measured RPM", flywheel.currentRpm)
            telemetry.addData("Gate Holding", gamepad1.left_bumper)
            telemetry.update()
        }
    }
}