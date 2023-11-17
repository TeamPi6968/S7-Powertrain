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
    public ControllerVar ControllerVar = new ControllerVar(); 
    public FakePS4Controller Driving = new FakePS4Controller(ControllerVar.Parameters.DrivingPort);

    //----------------------------------------------------------------
    // Functions
    //---------------------------------------------------------------- 

}
