package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.NotAShooter;


public class notShooting extends Command {
    NotAShooter nShooter;
    Limelight Limelight;
    Timer timer = new Timer();

    CommandXboxController JOYSTICK;

    public notShooting(NotAShooter notaShooter, Limelight limelight, CommandXboxController joystick){
        this.nShooter = notaShooter;
        this.Limelight = limelight;
        this.JOYSTICK = joystick;

        addRequirements(notaShooter);
        addRequirements(limelight);

    }

    public void initialize(){
        //nShooterMotor.runElMotor(30);
        timer.restart();
    }

    public void execute(){
        SmartDashboard.putNumber("time", timer.get());

        //Get percent of visable tag
        Double dCrntTA = Limelight.getTA();
        Boolean bKeepRunning = true;

        if(dCrntTA < 0.06){ 
            //set motor to max
            nShooter.runShooterMotor(70.00);
        }else if(dCrntTA < 0.11){
            //set motor to moderate
            nShooter.runShooterMotor(50.00);
        }else if(dCrntTA < 0.25){
            //set motor to mid
            nShooter.runShooterMotor(40.00);
        }else if (dCrntTA < 1.09){
            //set motor to low
            nShooter.runShooterMotor(30.00);
        }else{
            //set motor to super low
            nShooter.runShooterMotor(20.00);
        }  

        while (bKeepRunning == true){

            if (JOYSTICK.getHID().getStartButtonReleased() == true){
                bKeepRunning = false;
                
            }
           SmartDashboard.putNumber("timer", timer.get()); 
           //SmartDashboard.putBoolean("Startbutton", JOYSTICK.getHID().getStartButtonReleased());
        }
        

        isFinished();
    }

    public boolean isFinished(){
        timer.reset();
        nShooter.runShooterMotor(0);
        nShooter.runIndexerMotor(0);

        return true;   
    }
}
