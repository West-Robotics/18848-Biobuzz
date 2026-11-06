package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class Intake(hardwareMap: HardwareMap) {

    private val motor = wwwrapper(
        hardwareMap,
        "intake",
        DcMotorSimple.Direction.REVERSE,
        DcMotor.ZeroPowerBehavior.FLOAT
    )

    private val maxSpeedLimit = 0.75

    var power: Double
        get() = motor.effort
        set(value) {
            motor.effort = value * maxSpeedLimit
        }

    fun write() {
        motor.write()
    }
}