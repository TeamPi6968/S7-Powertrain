package frc.robot.EquipmentModules.General.Controller;

//import edu.wpi.first.wpilibj.PS4Controller;
import frc.robot.EquipmentModules.General.Controller.ControllerVar.ControllerVar;

//--------------------------------------------------------------------
// Libraries
//--------------------------------------------------------------------

public class Controller{
    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------

    //----------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------

    //----------------------------------------------------------------
    // Objects
    //----------------------------------------------------------------   
    public ControllerVar ControllerVar = new ControllerVar(); 
    public FakePS4Controller Driving = new FakePS4Controller(ControllerVar.Parameters.DrivingPort);
    //public PS4Controller Driving = new PS4Controller(ControllerVar.Parameters.DrivingPort);
    public FakePS4Controller Operator = new FakePS4Controller(ControllerVar.Parameters.DrivingPort);
    //public PS4Controller Operator = new PS4Controller(ControllerVar.Parameters.DrivingPort);

    //----------------------------------------------------------------
    // Functions
    //---------------------------------------------------------------- 

}
