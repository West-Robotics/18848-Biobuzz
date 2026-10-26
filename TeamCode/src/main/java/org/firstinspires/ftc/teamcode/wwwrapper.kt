package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import kotlin.math.abs

class wwwrapper(
    hardwareMap: HardwareMap,
    name: String,
    dir: Direction,
    zpb: ZeroPowerBehavior,
    private val eps: Double = 0.005,
    currentThresh: Double = 8.0
) {
    private val motor = hardwareMap.get(DcMotorEx::class.java, name)
    private var lastWritten = Double.NaN

    var effort = 0.0

    val isOverCurrent
        get() = motor.isOverCurrent

    val current
        get() = motor.getCurrent(CurrentUnit.AMPS)

    init {
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor.direction = dir
        motor.zeroPowerBehavior = zpb
        motor.setCurrentAlert(currentThresh, CurrentUnit.AMPS)
    }

    fun write() {
        val target = effort.coerceIn(-1.0, 1.0)
        // always send an exact zero so the motor can fully stop
        if (lastWritten.isNaN() || abs(target - lastWritten) > eps ||
            (target == 0.0 && lastWritten != 0.0)
        ) {
            motor.power = target
            lastWritten = target
        }
    }
}