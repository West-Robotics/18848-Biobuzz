package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class Flywheel(hardwareMap: HardwareMap) {


    private val motor = wwwrapper(
        hardwareMap,
        "flywheel",
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