package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@Autonomous(name = "Simple 1ft Auto")
class SimpleAuto : LinearOpMode() {

    override fun runOpMode() {
        val frontLeft = wwwrapper(hardwareMap, "fL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val frontRight = wwwrapper(hardwareMap, "fR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)
        val backLeft = wwwrapper(hardwareMap, "bL", DcMotorSimple.Direction.REVERSE, DcMotor.ZeroPowerBehavior.BRAKE)
        val backRight = wwwrapper(hardwareMap, "bR", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.BRAKE)

        waitForStart()

        if (opModeIsActive()) {
            // 1. Drive Forward (1 foot)
            frontLeft.effort = 0.3
            frontRight.effort = 0.3
            backLeft.effort = 0.3
            backRight.effort = 0.3

            frontLeft.write()
            frontRight.write()
            backLeft.write()
            backRight.write()

            sleep(1000) // Adjust this time (in milliseconds) if it goes too far or too short

            // 2. Stop briefly
            frontLeft.effort = 0.0
            frontRight.effort = 0.0
            backLeft.effort = 0.0
            backRight.effort = 0.0

            frontLeft.write()
            frontRight.write()
            backLeft.write()
            backRight.write()

            sleep(300)

            // 3. Drive Backward (1 foot to return)
            frontLeft.effort = -0.3
            frontRight.effort = -0.3
            backLeft.effort = -0.3
            backRight.effort = -0.3

            frontLeft.write()
            frontRight.write()
            backLeft.write()
            backRight.write()

            sleep(1000)

            // 4. Stop fully
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