package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FuelShooter extends SubsystemBase{

    TalonFX fuelShooterMotor = new TalonFX(Constants.FuelShooterConstants.fuelShooterID);

    TalonFX fuelIndexMotor = new TalonFX(Constants.FuelShooterConstants.fuelIndexerID);

    public FuelShooter()
    {

        TalonFXConfiguration fuelShooterMotorConfigs = new TalonFXConfiguration();
        TalonFXConfiguration fuelIndexMotorConfigs = new TalonFXConfiguration();

        fuelShooterMotorConfigs.CurrentLimits.withStatorCurrentLimit(20);
        fuelShooterMotorConfigs.Voltage.withPeakForwardVoltage(8);
        fuelShooterMotorConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(20)
            .withPeakReverseTorqueCurrent(-20);
        
        fuelIndexMotorConfigs.CurrentLimits.withStatorCurrentLimit(20);
        fuelIndexMotorConfigs.Voltage.withPeakForwardVoltage(8);
        fuelIndexMotorConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(20)
            .withPeakReverseTorqueCurrent(-20);
        
        fuelShooterMotorConfigs.Slot0.kP = Constants.FuelShooterConstants.fuelShooterkP;
        fuelShooterMotorConfigs.Slot0.kI = Constants.FuelShooterConstants.fuelShooterkI;
        fuelShooterMotorConfigs.Slot0.kD = Constants.FuelShooterConstants.fuelShooterkD;

        fuelShooterMotor.getConfigurator().apply(fuelShooterMotorConfigs);
        fuelIndexMotor.getConfigurator().apply(fuelIndexMotorConfigs);

    }

    public void runShooterMotor(double Velocidad){
        Velocidad /= 100;
        fuelShooterMotor.set(Velocidad);
    }
    public void runIndexerMotor(double Velocidad){
        Velocidad /= 100;
        fuelIndexMotor.set(Velocidad);
    }

    public void stopMotors(){
        fuelShooterMotor.set(0);
        fuelIndexMotor.set(0);
    }

}

