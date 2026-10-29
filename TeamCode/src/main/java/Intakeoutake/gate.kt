package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx
import org.firstinspires.ftc.teamcode.component.WrappedServo

class Gate(hardwareMap: HardwareMap) {
    private val servo = WrappedServo(
        deviceSupplier = { hardwareMap.get(ServoImplEx::class.java, "gate") },
        pwm = WrappedServo.ModelPWM.AXON_MINI,
        initialPosition = 0.5, // Starts right in the middle (0.0 to 1.0)
        initialDirection = Servo.Direction.FORWARD
    )

    // Tracks the current position (from 0.0 to 1.0)
    var currentPosition: Double = 0.5
        private set

    init {
        // No forced movement on init—it starts wherever the servo naturally sits or holds its position
    }

    // Nudges the position up or down when D-pad is held
    fun move(delta: Double) {
        currentPosition = (currentPosition + delta).coerceIn(0.0, 1.0)
        servo.position = currentPosition
    }

    fun write() {
        servo.write()
    }
}