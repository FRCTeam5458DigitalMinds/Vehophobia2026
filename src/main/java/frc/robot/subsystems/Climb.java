package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase{

    TalonFX climbMotor = new TalonFX(Constants.ClimbConstants.climbID1);


}
