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
        ProcessValue.Straffe_X  = Left_X;
        ProcessValue.Straffe_Y  = Left_Y * -1;
        ProcessValue.Rotation_X = Right_X;

        final double DiagonalLength = Math.sqrt((Settings.Length * Settings.Length)+(Settings.Width * Settings.Width));

        // resulting vectors
        double FL_x = ProcessValue.Straffe_X + ProcessValue.Rotation_X * (Settings.Length / DiagonalLength);
        double FL_y = ProcessValue.Straffe_Y - ProcessValue.Rotation_X * (Settings.Width  / DiagonalLength);
        double FR_x = ProcessValue.Straffe_X + ProcessValue.Rotation_X * (Settings.Length / DiagonalLength);
        double FR_y = ProcessValue.Straffe_Y + ProcessValue.Rotation_X * (Settings.Width  / DiagonalLength);
        double RL_x = ProcessValue.Straffe_X - ProcessValue.Rotation_X * (Settings.Length / DiagonalLength);
        double RL_y = ProcessValue.Straffe_Y - ProcessValue.Rotation_X * (Settings.Width  / DiagonalLength); 
        double RR_x = ProcessValue.Straffe_X - ProcessValue.Rotation_X * (Settings.Length / DiagonalLength); 
        double RR_y = ProcessValue.Straffe_Y + ProcessValue.Rotation_X * (Settings.Width  / DiagonalLength); 

        // Set translation speed
        FL_Module.TranslationVar.ProcessValue.Speed = Math.sqrt ((FL_x * FL_x) + (FL_y * FL_y)); //[%]
        FR_Module.TranslationVar.ProcessValue.Speed = Math.sqrt ((FR_x * FR_x) + (FR_y * FR_y)); //[%]
        RL_Module.TranslationVar.ProcessValue.Speed = Math.sqrt ((RL_x * RL_x) + (RL_y * RL_y)); //[%]
        RR_Module.TranslationVar.ProcessValue.Speed = Math.sqrt ((RR_x * RR_x) + (RR_y * RR_y)); //[%]

        // Set Rotation angle
        FL_Module.RotationVar.ProcessValue.Angle = Math.atan2 (FL_y, FL_x) * (180 / Math.PI); //[°]
        FR_Module.RotationVar.ProcessValue.Angle = Math.atan2 (FR_y, FR_x) * (180 / Math.PI); //[°]
        RL_Module.RotationVar.ProcessValue.Angle = Math.atan2 (RL_y, RL_x) * (180 / Math.PI); //[°]
        RR_Module.RotationVar.ProcessValue.Angle = Math.atan2 (RR_y, RR_x) * (180 / Math.PI); //[°]

    }

}
