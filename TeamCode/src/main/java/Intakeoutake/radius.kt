package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PwmControl
import com.qualcomm.robotcore.hardware.ServoImplEx

class Radius(hardwareMap: HardwareMap) {
    private val servo: ServoImplEx = hardwareMap.get(ServoImplEx::class.java, "radius")

    var pollenPosition: Double = 0.6
    var nectarPosition: Double = 0.3

    init {
        servo.pwmRange = PwmControl.PwmRange(500.0, 2500.0, 5000.0)
        pollen()
    }

    fun pollen() {
        servo.position = pollenPosition
    }

    fun nectar() {
        servo.position = nectarPosition
    }

    fun write() {
    }
}