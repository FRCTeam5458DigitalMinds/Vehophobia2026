package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase{
    Solenoid LED = new Solenoid(0, PneumaticsModuleType.CTREPCM, 3);
    
    public Command switchLED(){
        return this.runOnce(() -> LED.toggle());
    }
}
