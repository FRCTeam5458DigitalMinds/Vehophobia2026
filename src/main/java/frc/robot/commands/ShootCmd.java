package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;

public class ShootCmd extends Command {

    LED LED;
    FuelShooter SHOOTER;
    
    public ShootCmd(LED led, FuelShooter shooter){
        this.LED = led;
        this.SHOOTER = shooter;

        addRequirements(led);
        addRequirements(shooter);
    }

    public void initialize(){
        SHOOTER.runShooterMotor(60);
    }

    public void execute(){
        
    }

    public boolean isFinished(){
        return true;
    }
}
