package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo

class Gate(
    hardwareMap: HardwareMap,
    private val originalAngle: Double = 315.0,
    private val targetAngle: Double = 340.0
) {
    //
    private val servo: Servo = hardwareMap.get(Servo::class.java, "gate")

    init {

        servo.scaleRange(0.0, 1.0)
    }

    //
    private fun angleToPosition(angle: Double): Double {
        val clampedAngle = angle.coerceIn(0.0, 180.0)
        return clampedAngle / 180.0
    }

    // M
    fun goHome() {
        servo.position = angleToPosition(originalAngle)
    }

    //
    fun goTarget() {
        servo.position = angleToPosition(targetAngle)
    }
}
//axon mini mk2