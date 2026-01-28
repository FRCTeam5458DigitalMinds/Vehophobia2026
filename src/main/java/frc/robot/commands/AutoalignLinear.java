package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.*;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

public class AutoalignLinear extends Command {

    Limelight LIMELIGHT;
    CommandSwerveDrivetrain DRIVETRAIN;
    SwerveRequest.RobotCentric robotDrive;

    Double maxLineSpeed; //Max Line Speed

    public AutoalignLinear(Limelight limelight, CommandSwerveDrivetrain drivetrain, Double maxLinearSpeed) 
    {
        this.LIMELIGHT = limelight;
        this.DRIVETRAIN = drivetrain;
        robotDrive = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);
        maxLineSpeed = maxLinearSpeed;

        addRequirements(limelight);
        addRequirements(drivetrain);
    
    }
    //sets the roller speed for every level except L1
    public void initialize()
    {   
        SmartDashboard.putNumber("ID", LIMELIGHT.getTargetID());
    }

    public void execute()
    {
        //Used for do while loop
        boolean isClose = false;

        //checks for apriltag
        if (LIMELIGHT.checkForTarget()){
            do {
                //Runs function to get turn speed
                double lineSpeed = LIMELIGHT.limelight_range_proportional(maxLineSpeed);
                //Puts target speed onto smartdashboard (move to elastic)
                SmartDashboard.putNumber("Target line speed", lineSpeed);
                //Uses turn speed to run robot
                DRIVETRAIN.setControl(robotDrive.withVelocityX(lineSpeed));

                //If crosshair is between these horizontal values , stop
                if (-2.3 < LIMELIGHT.getTY() && LIMELIGHT.getTY() < -2.0){
                    isClose = true;
                }

            } while (isClose == false); //stop when tag is centered
        }

        isFinished();
    }

    public boolean isFinished()
    {
      return true;
    }
}