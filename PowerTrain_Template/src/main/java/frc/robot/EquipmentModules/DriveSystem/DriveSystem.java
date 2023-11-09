package frc.robot.EquipmentModules.DriveSystem;

import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.FrontLeftModule;

import frc.robot.EquipmentModules.DriveSystem.DriveSystemVar.*;


//====================================================================
// Equipment Module: Drive system
//====================================================================

public class DriveSystem {
    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------
    
    //----------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------
    public DriveSystem(){

    }

    //----------------------------------------------------------------
    // Variable structure
    //----------------------------------------------------------------   
    public ProcessValues ProcessValue = new ProcessValues();
    public Setpoints Setpoint         = new Setpoints();
    public Status Status              = new Status();
    public Settings Settings          = new Settings();
    public Parameters Parameters      = new Parameters();
    public Alarms Alarm               = new Alarms();

    public FrontLeftModule FL_Module    = new FrontLeftModule();


    //----------------------------------------------------------------
    // Control modules
    //----------------------------------------------------------------
    
    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------

}
