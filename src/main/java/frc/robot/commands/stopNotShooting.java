package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NotAShooter;

public class stopNotShooting extends Command {
    NotAShooter nShooter;

    public stopNotShooting(NotAShooter notaShooter){
        this.nShooter = notaShooter;

        addRequirements(notaShooter);
    }

    public void initialize(){
        nShooter.runShooterMotor(0);
        nShooter.runIndexerMotor(0);
    }

    public void execute(){
        isFinished();
    }

    public boolean isFinished(){
        return true;   
    }
}
