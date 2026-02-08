package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FuelShooter extends SubsystemBase{

    TalonFX fuelShooterMotor = new TalonFX(Constants.FuelShooterConstants.fuelShooterID);

    TalonFX fuelIndexMotor = new TalonFX(Constants.FuelShooterConstants.fuelIndexerID);

    Solenoid LED = new Solenoid(0, PneumaticsModuleType.CTREPCM, 3);


    public FuelShooter()
    {

        //sets a new config object  
        TalonFXConfiguration fuelShooterMotorConfigs = new TalonFXConfiguration();
        TalonFXConfiguration fuelIndexMotorConfigs = new TalonFXConfiguration();


        // Sets the limits for (Shooter) motor
        fuelShooterMotorConfigs.CurrentLimits.withStatorCurrentLimit(20);
        fuelShooterMotorConfigs.Voltage.withPeakForwardVoltage(8);
        fuelShooterMotorConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(20)
            .withPeakReverseTorqueCurrent(-20);
        
        

        // Sets the limits for (indexer) motor
        fuelIndexMotorConfigs.CurrentLimits.withStatorCurrentLimit(20);
        fuelIndexMotorConfigs.Voltage.withPeakForwardVoltage(8);
        fuelIndexMotorConfigs.TorqueCurrent.withPeakForwardTorqueCurrent(20)
            .withPeakReverseTorqueCurrent(-20);
        

        //sets PID numbers
        fuelShooterMotorConfigs.Slot0.kP = Constants.FuelShooterConstants.fuelShooterkP;
        fuelShooterMotorConfigs.Slot0.kI = Constants.FuelShooterConstants.fuelShooterkI;
        fuelShooterMotorConfigs.Slot0.kD = Constants.FuelShooterConstants.fuelShooterkD;


        //apply configurations for the Indexer and Shooter motors :)
        fuelShooterMotor.getConfigurator().apply(fuelShooterMotorConfigs);
        fuelIndexMotor.getConfigurator().apply(fuelIndexMotorConfigs);

    }


    //speed of these things (sets it)
    public void runShooterMotor(double Velocidad){
        Velocidad /= 100;
        fuelShooterMotor.set(Velocidad);
        LED.set(true);
    }

    //More of that
    public void runIndexerMotor(double Velocidad){
        Velocidad /= 100;
        fuelIndexMotor.set(Velocidad);
    }


    //sets the funtion to have the motors stop
    public void stopMotors(){
        fuelShooterMotor.set(0);
        fuelIndexMotor.set(0);
        LED.set(false);
    }
}

