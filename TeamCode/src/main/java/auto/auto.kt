package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@Autonomous(name = "Simple Auto")
class SimpleAuto : LinearOpMode() {

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        // Initialize mechanisms
        val intake = Intake(hardwareMap)
        val flywheel = Flywheel(hardwareMap)
        val gate = Gate(hardwareMap)

        waitForStart()

        if (opModeIsActive()) {
            // --- 1. START INTAKE & FLYWHEEL ---
            intake.power = 0.8
            flywheel.power = 0.98
            intake.write()
            flywheel.write()

            // --- 2. WAIT 5 SECONDS, THEN OPEN GATE ---
            val startTime = runtime

            // Sleep for 5 seconds while keeping the motors updated
            while (opModeIsActive() && (runtime - startTime < 5.0)) {
                intake.write()
                flywheel.write()
                sleep(50)
            }

            // Open the gate after 5 seconds while everything is still spinning
            gate.goTarget()

            // --- 3. FINISH THE REST OF THE 10-SECOND WINDOW ---
            while (opModeIsActive() && (runtime - startTime < 10.0)) {
                intake.write()
                flywheel.write()
                sleep(50)
            }

            // --- 4. STOP MECHANISMS & CLOSE GATE ---
            intake.power = 0.0
            flywheel.power = 0.0
            intake.write()
            flywheel.write()
            gate.goHome()

            // --- 5. MOVE FORWARD ---
            frontLeft.effort = 0.3
            frontRight.effort = 0.3
            backLeft.effort = 0.3
            backRight.effort = 0.3

            frontLeft.write()
            frontRight.write()
            backLeft.write()
            backRight.write()

            sleep(1200)

            // --- 6. STOP ROBOT ---
            frontLeft.effort = 0.0
            frontRight.effort = 0.0
            backLeft.effort = 0.0
            backRight.effort = 0.0

            frontLeft.write()
            frontRight.write()
            backLeft.write()
            backRight.write()
        }
    }
}