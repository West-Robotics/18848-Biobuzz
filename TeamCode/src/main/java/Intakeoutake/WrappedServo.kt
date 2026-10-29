package org.firstinspires.ftc.teamcode.component

import com.qualcomm.robotcore.hardware.PwmControl
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.ServoImplEx
import kotlin.math.abs

class WrappedServo(
    private val deviceSupplier: () -> ServoImplEx?,
    private val pwm: ModelPWM,
    var thresh: Double = 0.002,
    private val usFrame: Double = 5000.0,
    initialPosition: Double = 0.0,
    // Explicitly defining initialDirection here fixes the unresolved reference error
    private val initialDirection: Servo.Direction = Servo.Direction.FORWARD
) : Component() {

    enum class ModelPWM(val min: Double, val max: Double) {
        AXON_MAX(510.0, 2490.0), AXON_MINI(510.0, 2490.0), AXON_MICRO(510.0, 2490.0),
        GOBILDA_TORQUE(500.0, 2500.0), GOBILDA_SPEED(500.0, 2500.0), GOBILDA_SUPER(500.0, 2500.0),
        GENERIC(500.0, 2500.0),
    }

    private var _servo: ServoImplEx? = null
    private val servo: ServoImplEx
        get() {
            if (_servo == null) {
                _servo = deviceSupplier() ?: error(
                    "tried to access device before OpMode init"
                )
                _servo!!.pwmRange = PwmControl.PwmRange(pwm.min, pwm.max, usFrame)
                _servo!!.direction = initialDirection
            }
            return _servo!!
        }

    private var lastWrittenPosition = Double.NaN

    var direction: Servo.Direction
        get() = servo.direction
        set(value) {
            servo.direction = value
        }

    var position: Double = initialPosition
        set(value) {
            val clamped = value.coerceIn(0.0, 1.0)
            if (abs(clamped - field) > thresh) {
                field = clamped
            }
        }

    override fun write() {
        if (lastWrittenPosition.isNaN() || abs(position - lastWrittenPosition) > thresh) {
            servo.position = position
            lastWrittenPosition = position
        }
    }

    override fun update(dt: Double) {}

    override fun reset() {
        _servo = null
        lastWrittenPosition = Double.NaN
    }
}