package frc.robot.EquipmentModules.General.Functions;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//====================================================================
// General: Measuring
//====================================================================

public class Measuring {
    private double MaxSpeed;

    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------

    /**
    * This is to put a value inside the {@code Smartdashboard} overview 
    * to read the state of the value when robot is enabled.
    *
    * @param Name Name that is shown in the smartdashboard page.
    * @param Value The value your going to read.
    */
    public void ReadValue(String Name,double Value){
        SmartDashboard.putNumber(Name , Value);
    }

    /**
    * This is a temperarly function to measure the highest speed of the controller. 
    * This is because every controller could vary a little bit. 
    * A {@code SmartDashboard} function is added of the {@code MaxSpeed} to see the result.
    * The {@code MaxSpeed} can then later be added inside the {@code NormalizeSpeed()} function
    *
    * @param CurrentSpeed Current calculated speed value.
    */
    public void HighestSpeed(double CurrentSpeed){
        if (CurrentSpeed > this.MaxSpeed){
            this.MaxSpeed = CurrentSpeed;
        }
        SmartDashboard.putNumber("Max speed" , this.MaxSpeed);
    }

}
