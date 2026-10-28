package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class Intake(hardwareMap: HardwareMap) {

    // If the intake spins the wrong way, change FORWARD to REVERSE here.
    // This is the only place you need to change it.
    private val motor = wwwrapper(
        hardwareMap,
        "intake",
        DcMotorSimple.Direction.FORWARD,
        DcMotor.ZeroPowerBehavior.FLOAT
    )

    var power: Double
        get() = motor.effort
        set(value) {
            motor.effort = value
        }

    fun write() {
        motor.write()
    }
}