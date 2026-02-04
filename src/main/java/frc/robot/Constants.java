package frc.robot;

public final class Constants {
    

    
    public static final class LimelightConstants
    {
        public static final String limeName = "limelight-digital";
        public static final double distThreshold = 5; //Distance from center of tag 
        public static final int pipeline = 1;    /// Night in shope (1), day in shop (0), ...
        
        public static final double[] redHubTags = {7.0,10.0};
        public static final double[] blueHubTags = {25.0,26.0};
    }

    public static final class LEDConstants
    {
        public static final double ledNum = 3; 
    }

    public static final class IntakeConstants
    {
        public static final int intakeID1 = 13;

    }
    public static final class ClimbConstants
    {
        public static final int climbID1 = 10;
    }
    
    public static final class FuelShooterConstants
    {
        public static final int fuelShooterID = 13;

        public static final double fuelShooterkP = 0.05;
        public static final double fuelShooterkI = 0;
        public static final double fuelShooterkD = 0;

        public static final int fuelIndexerID = 12;
    }

}