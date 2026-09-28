package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name = "Yes ")
class yeehaw : LinearOpMode() {
    override fun runOpMode(){

        val front_left = hardwareMap.get("a") as DcMotorEx
        front_left.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        front_left.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        front_left.direction = DcMotorSimple.Direction.FORWARD
        val front_right = hardwareMap.get("b") as DcMotorEx
        front_right.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        front_right.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        front_right.direction = DcMotorSimple.Direction.REVERSE
        val back_left = hardwareMap.get("c") as DcMotorEx
        back_left.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        back_left.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        back_left.direction = DcMotorSimple.Direction.FORWARD
        val back_right = hardwareMap.get("d") as DcMotorEx
        back_right.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODERS
        back_right.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        back_right.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while(opModeIsActive()){

        }
    }
}