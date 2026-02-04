package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.RawFiducial;
import frc.robot.subsystems.*;

import java.util.ArrayList;
import java.util.List;

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
        int crtTargetID = 0;
        double currentTXNC = 0.0;

        RawFiducial[] currentFiducials = LIMELIGHT.getFiducialData();
        List<Integer> alCurrentTargetsIDs = new ArrayList<>();

        for (RawFiducial fiducial : currentFiducials) {

            alCurrentTargetsIDs.add(fiducial.id);
        }

        if (alCurrentTargetsIDs.contains(5) && alCurrentTargetsIDs.contains(8) && alCurrentTargetsIDs.contains(9) && alCurrentTargetsIDs.contains(10)){
            crtTargetID = 9;
        }
        else if (alCurrentTargetsIDs.contains(2) && alCurrentTargetsIDs.contains(9) && alCurrentTargetsIDs.contains(10) && alCurrentTargetsIDs.contains(11)){
            crtTargetID = 11;
        }
        else if (alCurrentTargetsIDs.contains(8) && alCurrentTargetsIDs.contains(9) && alCurrentTargetsIDs.contains(10)){
            crtTargetID = 9;
        }
        else if (alCurrentTargetsIDs.contains(10) && alCurrentTargetsIDs.contains(11) && alCurrentTargetsIDs.contains(2)){
            crtTargetID = 11;
        }
        else if (alCurrentTargetsIDs.contains(9) && alCurrentTargetsIDs.contains(10) && alCurrentTargetsIDs.contains(11)){
            crtTargetID = 10;
        }
        else if (alCurrentTargetsIDs.contains(9) && alCurrentTargetsIDs.contains(10)){
            crtTargetID = 10;
        }
        else if (alCurrentTargetsIDs.contains(11) && alCurrentTargetsIDs.contains(2)){
            crtTargetID = 11;
        }
        else if (alCurrentTargetsIDs.contains(10)){
            crtTargetID = 10;
        }


        do{
            RawFiducial[] crtFiducials = LIMELIGHT.getFiducialData();
           
            for (RawFiducial fiducial : crtFiducials) {

                if (fiducial.id == crtTargetID){
                    currentTXNC = fiducial.txnc; // X offset (no crosshair)
                }
            }

            if (crtTargetID != 0){

                    //Runs function to get turn speed
                    double turnSpeed = LIMELIGHT.limelight_aim_proportional(maxAnglSpeed);

                    SmartDashboard.putNumber("TX", LIMELIGHT.getTX());
                    SmartDashboard.putNumber("TXNC", currentTXNC);

                    //Puts target speed onto smartdashboard (move to elastic)
                    SmartDashboard.putNumber("Target turn speed", turnSpeed);
                    //Uses turn speed to run robot
                    DRIVETRAIN.setControl(robotDrive.withRotationalRate(turnSpeed));

                    //If crosshair is between these horizontal values , stop
                    if (-7.0 < currentTXNC && currentTXNC < 7.0){
                        isCentered = true;
                    }

                
            }

        } while (isCentered == false); //stop when tag is centered

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