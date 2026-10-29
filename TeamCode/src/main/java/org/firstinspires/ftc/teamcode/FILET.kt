package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import kotlin.math.abs

@TeleOp(name = "TEST teleOp")
class FILET : LinearOpMode() {

    private val dir = 1.0

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        val intake = Intake(hardwareMap)
        val flywheel = Flywheel(hardwareMap)

        waitForStart()

        while (opModeIsActive()) {
            val speedMultiplier = 1.0

            val y = -gamepad1.left_stick_y.toDouble() * dir * speedMultiplier
            val x = gamepad1.left_stick_x.toDouble() * dir * speedMultiplier
            val rx = gamepad1.right_stick_x.toDouble() * speedMultiplier

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
                flywheel.power = 1.0
            } else {
                flywheel.power = 0.0
            }

            frontLeft.write()
            backLeft.write()
            frontRight.write()
            backRight.write()
            intake.write()
            flywheel.write()

            telemetry.addData("Intake power", intake.power)
            telemetry.addData("Flywheel power", flywheel.power)
            telemetry.update()
        }
    }
}