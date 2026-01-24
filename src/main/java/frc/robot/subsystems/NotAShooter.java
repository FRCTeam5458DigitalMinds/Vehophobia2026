package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NotAShooter extends SubsystemBase{
    
    TalonFX notAShooterMotor = new TalonFX(Constants.NotAShooterConstants.notAShooterID);

    public NotAShooter(){
        //notAShooterMotor.getConfigurator().apply(new TalonFXConfiguration());

        TalonFXConfiguration notAShooterMotorConfigs = new TalonFXConfiguration();

        notAShooterMotorConfigs.CurrentLimits.withStatorCurrentLimit(20);
        notAShooterMotorConfigs.Voltage.withPeakForwardVoltage(8);
        notAShooterMotorConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(20)
            .withPeakReverseTorqueCurrent(-20);
        
        notAShooterMotorConfigs.Slot0.kP = Constants.NotAShooterConstants.notAShooterkP;
        notAShooterMotorConfigs.Slot0.kI = Constants.NotAShooterConstants.notAShooterkI;
        notAShooterMotorConfigs.Slot0.kD = Constants.NotAShooterConstants.notAShooterkD;

        notAShooterMotor.getConfigurator().apply(notAShooterMotorConfigs);
    }

    public void runElMotor(double Velocidad){
        Velocidad /= 100;
        notAShooterMotor.set(Velocidad);
    }

    

}
