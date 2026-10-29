package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PwmControl
import com.qualcomm.robotcore.hardware.ServoImplEx

class Gate(hardwareMap: HardwareMap) {
    private val servo: ServoImplEx = hardwareMap.get(ServoImplEx::class.java, "gate")

    init {
        servo.pwmRange = PwmControl.PwmRange(500.0, 2500.0, 5000.0)

    }

    // Gets or sets the position directly (0.0 to 1.0)
    var position: Double
        get() = servo.position
        set(value) {
            servo.position = value.coerceIn(0.0, 1.0)
        }

    // Nudges the servo position up or down
    fun move(delta: Double) {
        position += delta
    }
}