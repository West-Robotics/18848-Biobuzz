package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap

class Flywheel(hardwareMap: HardwareMap) {

    private val motor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "flywheel")

    var ticksPerRev: Double = 28.0

    var maxRpm: Double = 6000.0


    var targetRpm: Double = 0.0


    var power: Double
        get() = if (targetRpm == 0.0) 0.0 else targetRpm / maxRpm
        set(value) {
            targetRpm = (value.coerceIn(0.0, 1.0)) * maxRpm
        }

    init {
        motor.direction = DcMotorSimple.Direction.FORWARD
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }


    val currentRpm: Double
        get() = (motor.velocity * 60.0) / ticksPerRev

    fun write() {
        if (targetRpm > 0.0) {
            val targetTicksPerSec = (targetRpm * ticksPerRev) / 60.0
            motor.velocity = targetTicksPerSec
        } else {
            motor.power = 0.0
        }
    }
}