package frc.robot.EquipmentModules.DriveSystem;

//----------------------------------------------------------------
// Libraries
//----------------------------------------------------------------
import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.FrontLeftModule;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontRightModule.FrontRightModule;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.RearLeftModule.RearLeftModule;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.RearRightModule.RearRightModule;
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
    // Objects
    //----------------------------------------------------------------   
    public ProcessValues ProcessValue = new ProcessValues();
    public Setpoints Setpoint         = new Setpoints();
    public Status Status              = new Status();
    public Settings Settings          = new Settings();
    public Parameters Parameters      = new Parameters();
    public Alarms Alarm               = new Alarms();

    public FrontLeftModule FL_Module   = new FrontLeftModule();
    public FrontRightModule FR_Module  = new FrontRightModule();
    public RearLeftModule RL_Module    = new RearLeftModule();
    public RearRightModule RR_Module   = new RearRightModule();
    //----------------------------------------------------------------
    // Control modules
    //----------------------------------------------------------------
    
    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------

}
