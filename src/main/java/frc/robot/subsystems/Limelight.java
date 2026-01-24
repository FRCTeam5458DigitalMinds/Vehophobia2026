package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.RawFiducial;
import frc.robot.Constants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class Limelight extends SubsystemBase{
   //SwerveDrive class
   CommandSwerveDrivetrain drivetrain;
   SwerveRequest.RobotCentric robotDrive;
   
   //name of limelight (don't forget)
   String name = Constants.LimelightConstants.limeName;
   
   //info from tag
   int id;
   
   //Change smartdashboard to elastic later
    // Basic targeting data
   public Double getTX(){
      double tx = LimelightHelpers.getTX(name);
      SmartDashboard.putNumber("TX", tx);
      return tx; // Horizontal offset from crosshair to target in degrees
   }
   public Double getTY(){
      return LimelightHelpers.getTY(name); // Vertical offset from crosshair to target in degrees
   }
   public Double getTA(){
      return LimelightHelpers.getTA(name);
   }
   public boolean checkForTarget(){
      return LimelightHelpers.getTV(name); // Do you have a valid target?
   }
   public Double getTargetID(){

      Double dTarget = Double.parseDouble("0");

      dTarget = LimelightHelpers.getFiducialID(name);
      return dTarget;
   }
   
   //
  public double limelight_aim_proportional(Double robotMaxAngularSpeed)
  {    
    // kP (constant of proportionality)
    // this is a hand-tuned number that determines the aggressiveness of our proportional control loop
    // if it is too high, the robot will oscillate around.
    // if it is too low, the robot will never reach its target
    // if the robot never turns in the correct direction, kP should be inverted.
    double AutoalignkP = 0.01; //0.035

    // tx ranges from (-hfov/2) to (hfov/2) in degrees. If your target is on the rightmost edge of 
    // your limelight 3 feed, tx should return roughly 31 degrees.
    double targetingAngularVelocity = getTX() * AutoalignkP;

    // convert to radians per second for our drive method
    targetingAngularVelocity *= robotMaxAngularSpeed;

    //invert since tx is positive when the target is to the right of the crosshair
    targetingAngularVelocity *= -1.0;

    
    return targetingAngularVelocity;
  }
}

