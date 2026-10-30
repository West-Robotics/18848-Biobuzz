package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PwmControl
import com.qualcomm.robotcore.hardware.ServoImplEx

class Gate(hardwareMap: HardwareMap) {
    private val servo: ServoImplEx = hardwareMap.get(ServoImplEx::class.java, "gate")


    private var isOpen = false

    private val homePosition = 0.3
    val targetPosition = 0.4

    init {
        servo.pwmRange = PwmControl.PwmRange(500.0, 2500.0, 5000.0)
        goHome()
    }

    fun goHome() {
        isOpen = false
        servo.position = homePosition
    }

    fun goTarget() {
        isOpen = true
        servo.position = targetPosition
    }


    fun toggle() {
        if (isOpen) {
            goHome()
        } else {
            goTarget()
        }
    }

    fun isAtTarget(): Boolean = isOpen
}