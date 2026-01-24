package frc.robot.commands;

import java.io.NotActiveException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.NotAShooter;

public class notShooting extends Command {
    NotAShooter nShooterMotor;
    Limelight Limelight;

    public notShooting(NotAShooter notaShooter, Limelight limelight){
        this.nShooterMotor = notaShooter;
        this.Limelight = limelight;

        addRequirements(notaShooter);
        addRequirements(limelight);
    }

    public void initialize(){
        //nShooterMotor.runElMotor(30);
    }

    public void execute(){
        Double dCrntTA = Limelight.getTA();
        
        if(dCrntTA < 0.06){ 
            //set motor to max
            nShooterMotor.runElMotor(70.00);
        }else if(dCrntTA < 0.11){
            //set motor to moderate
            nShooterMotor.runElMotor(50.00);
        }else if(dCrntTA < 0.25){
            //set motor to mid
            nShooterMotor.runElMotor(40.00);
        }else if (dCrntTA < 1.09){
            //set motor to low
            nShooterMotor.runElMotor(30.00);
        }else{
            //set motor to super low
            nShooterMotor.runElMotor(20.00);
        }

        /* 
        switch(dCrntTA){
            case 10.0:
        }
        */
        isFinished();
    }

    public boolean isFinished(){
        return true;   
    }
}
