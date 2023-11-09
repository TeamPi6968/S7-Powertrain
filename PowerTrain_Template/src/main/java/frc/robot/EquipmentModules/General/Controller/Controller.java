package frc.robot.EquipmentModules.General.Controller;

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
    public ControllerVar Controller = new ControllerVar(); 
    public FakePS4Controller Driving = new FakePS4Controller(Controller.Parameters.DrivingPort);

    //----------------------------------------------------------------
    // Functions
    //---------------------------------------------------------------- 

}
