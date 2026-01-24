package frc.robot.commands;

import java.io.NotActiveException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NotAShooter;

public class stopNotShooting extends Command {
    NotAShooter nShooterMotor;

    public stopNotShooting(NotAShooter notaShooter){
        this.nShooterMotor = notaShooter;

        addRequirements(notaShooter);
    }

    public void initialize(){
        nShooterMotor.runElMotor(0);
    }

    public void execute(){
        isFinished();
    }

    public boolean isFinished(){
        return true;   
    }
}
