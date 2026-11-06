package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PwmControl
import com.qualcomm.robotcore.hardware.ServoImplEx

class Gate(hardwareMap: HardwareMap) {
    private val servo: ServoImplEx = hardwareMap.get(ServoImplEx::class.java, "gate")

    private val homePosition = 0.3
    private val targetPosition = 0.4

    init {
        servo.pwmRange = PwmControl.PwmRange(500.0, 2500.0, 5000.0)
        goHome()
    }

    fun goHome() {
        servo.position = homePosition
    }

    fun goTarget() {
        servo.position = targetPosition
    }

    fun write() {
        // Servo position is set directly in goHome/goTarget
    }
}