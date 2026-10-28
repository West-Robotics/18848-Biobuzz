package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name = "Intake Test")
class IntakeTest : LinearOpMode() {

    override fun runOpMode() {
        // If the intake spins counterclockwise, change FORWARD to REVERSE here
        val intake = wwwrapper(hardwareMap, "intake", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT)

        waitForStart()

        while (opModeIsActive()) {
            // right trigger = intake
            intake.effort = gamepad1.right_trigger.toDouble()
            intake.write()

            telemetry.addData("Intake power", intake.effort)
            telemetry.update()
        }
    }
}