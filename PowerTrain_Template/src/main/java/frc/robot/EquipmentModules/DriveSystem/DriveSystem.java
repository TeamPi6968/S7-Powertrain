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
    public ProcessValues ProcessValue = new ProcessValues();
    public Setpoints Setpoint         = new Setpoints();
    public Status Status              = new Status();
    public Settings Settings          = new Settings();
    public Parameters Parameters      = new Parameters();
    public Alarms Alarm               = new Alarms();
    
    
    //----------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------
    public DriveSystem(){}

    //----------------------------------------------------------------
    // Objects
    //----------------------------------------------------------------   
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
    public void Swerve(double Left_X, double Left_Y, double Right_X){
        ProcessValue.Left_X  = Left_X;
        ProcessValue.Left_Y  = Left_Y;
        ProcessValue.Right_X = Right_X;
    }

}
