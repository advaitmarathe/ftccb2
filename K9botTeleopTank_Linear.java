/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="K9bot: Telop Tank", group="K9bot")
@Disabled
public class K9botTeleopTank_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareK9bot   robot           = new HardwareK9bot();

    final double    CLAW_SPEED      = 0.0 ;                            // sets rate to move servo
    final double    ARM_SPEED       = 0.01 ;
    double top_position = robot.top_home;
    double right_position = robot.right_home;
            double left_position = robot.left_home;
    // sets rate to move servo// Use a K9'shardware
    @Override
    public void runOpMode() {
        double leftback;
        double rightback;
        double rightfront;
        double collector;
        double leftfront;
        double rightside;
        double shooterleft;
        double shooterright;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
      /*  telemetry.addData("top,", "%.2f",robot.top);
        telemetry.addData("right,", "%.2f",robot.right);
        telemetry.addData("left,", "%.2f",robot.left);*/



        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
            rightside = gamepad1.left_stick_x;
            leftback = -gamepad1.left_stick_y;
            rightfront = gamepad1.right_stick_y;
            rightback = gamepad1.right_stick_y;
            leftfront = -gamepad1.left_stick_y;
            collector = -gamepad2.left_stick_y;
            shooterleft = gamepad2.right_stick_y;
            shooterright= -gamepad2.right_stick_y;
            double capball = -(gamepad2.right_trigger - gamepad2.left_trigger);
            robot.collector.setPower(collector);
            robot.capball.setPower(capball);
            robot.shooterleft.setPower(shooterleft*0.75);
            robot.shooterright.setPower(shooterright*0.75);

            rightfront = Range.clip(rightfront,-1,1);
            leftfront = Range.clip(leftfront,-1,1);
            leftback = Range.clip(leftback,-1,1);
            rightback = Range.clip(rightback,-1,1);
            rightside = Range.clip(rightside, -1,1);
            shooterleft= Range.clip(shooterleft, -1,1);
            shooterright= Range.clip(shooterright, -1,1);
            capball =  Range.clip(shooterleft, -1,1);



            if(gamepad1.x) {


                robot.rightBackMotor.setPower(-rightside);
                robot.leftbackMotor.setPower(-rightside);
                robot.rightFrontMotor.setPower(rightside);
                robot.leftFrontMotor.setPower(rightside);
            }
            else
            {
                robot.rightBackMotor.setPower(rightback);
                robot.leftbackMotor.setPower(leftback);
                robot.rightFrontMotor.setPower(rightfront);
                robot.leftFrontMotor.setPower(leftfront);

            }
            if(gamepad2.y)
            {
                robot.hit.setPosition(0.5);
            }




            // Use gamepad X & B to open and close the claw
            /*if (gamepad2.right_bumper) {
                robot.shooterleft.setPower(shooterleft *0.9);
                robot.shooterright.setPower(shooterright*0.9);
            }
            else
            {
                robot.shooterleft.setPower(shooterleft);
                robot.shooterright.setPower(shooterright);
            }*/

            if (gamepad1.b)
            {
                robot.right.setPosition(2.5);
                robot.left.setPosition(2.5);
            }
            // Move both servos to new position.
            /*top_position  = Range.clip(top_position, robot.topmin, robot.topmax);
            robot.top.setPosition(top_position);
            left_position  = Range.clip(left_position, robot.topmin, robot.topmax);
            robot.left.setPosition(left_position);
            right_position  = Range.clip(right_position, robot.topmin, robot.topmax);
            robot.right.setPosition(right_position);*/


            // Send telemetry message to signify robot running;
            //telemetry.addData("arm",   "%.2f", armPosition);
           // telemetry.addData("claw",  "%.2f", clawPosition);
            telemetry.addData("rightback",  "%.2f", rightback);
            telemetry.addData("leftback",  "%.2f", leftback);
          telemetry.addData("rightfront", "%.2f", rightfront);
           telemetry.addData("leftfront", "%.2f", leftfront);


          telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}
