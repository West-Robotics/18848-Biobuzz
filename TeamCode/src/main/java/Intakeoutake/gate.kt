package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx
import org.firstinspires.ftc.teamcode.component.WrappedServo

class Gate(
    hardwareMap: HardwareMap,
    initialAngle: Double = 315.0
) {
    private val servo = WrappedServo(
        deviceSupplier = { hardwareMap.get(ServoImplEx::class.java, "gate") },
        pwm = WrappedServo.ModelPWM.AXON_MINI,
        initialPosition = angleToPosition(initialAngle),
        initialDirection = Servo.Direction.FORWARD
    )

    // Tracks the current live angle of the gate
    var currentAngle: Double = initialAngle
        private set

    init {
        setAngle(initialAngle)
    }

    private fun angleToPosition(angle: Double): Double {
        val clampedAngle = angle.coerceIn(0.0, 360.0)
        return clampedAngle / 360.0
    }

    // Adjusts the angle by a specific step (e.g., +1° or -1°)
    fun changeAngle(delta: Double) {
        currentAngle = (currentAngle + delta).coerceIn(0.0, 360.0)
        servo.position = angleToPosition(currentAngle)
    }

    // Sets the angle directly
    fun setAngle(angle: Double) {
        currentAngle = angle.coerceIn(0.0, 360.0)
        servo.position = angleToPosition(currentAngle)
    }

    fun write() {
        servo.write()
    }
}