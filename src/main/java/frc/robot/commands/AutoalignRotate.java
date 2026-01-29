package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.*;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;

public class AutoalignRotate extends Command {

    Limelight LIMELIGHT;
    CommandSwerveDrivetrain DRIVETRAIN;
    SwerveRequest.RobotCentric robotDrive;


    Double maxAnglSpeed; //Max Angular Speed

    public AutoalignRotate(Limelight limelight, CommandSwerveDrivetrain drivetrain, Double maxAngularSpeed) 
    {
        this.LIMELIGHT = limelight;
        this.DRIVETRAIN = drivetrain;
        robotDrive = new SwerveRequest.RobotCentric().withDriveRequestType(DriveRequestType.OpenLoopVoltage);
        maxAnglSpeed = maxAngularSpeed;

        addRequirements(limelight);
        addRequirements(drivetrain);
    
    }
    //puts the Target ID on the Dashboard
    public void initialize()
    {   
        SmartDashboard.putNumber("ID", LIMELIGHT.getTargetID());
        
    }


    //runs 
    public void execute()
    {
        //Used for do while loop
        boolean isCentered = false;

        //checks for apriltag
        if (LIMELIGHT.checkForTarget() && LIMELIGHT.CheckHubTarget(LIMELIGHT.getTargetID())){
            do {
                //Runs function to get turn speed
                double turnSpeed = LIMELIGHT.limelight_aim_proportional(maxAnglSpeed);
                LIMELIGHT.getTY();
                //Puts target speed onto smartdashboard (move to elastic)
                SmartDashboard.putNumber("Target turn speed", turnSpeed);
                //Uses turn speed to run robot
                DRIVETRAIN.setControl(robotDrive.withRotationalRate(turnSpeed));

                //If crosshair is between these horizontal values , stop
                if (-5.0 < LIMELIGHT.getTX() && LIMELIGHT.getTX() < 5.0){
                    isCentered = true;
                }

            } while (isCentered == false); //stop when tag is centered
        }
        //runs "isFinished" function to say how it is done
        isFinished();
    }


    //sets boolean for its done to stop the comand
    public boolean isFinished()
    {
      return true;
    }


//this closes the code of this part
}