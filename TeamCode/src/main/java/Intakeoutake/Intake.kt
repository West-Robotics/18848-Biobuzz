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

    var power: Double
        get() = motor.effort
        set(value) {
            motor.effort = value
        }

    fun write() {
        motor.write()
    }
}