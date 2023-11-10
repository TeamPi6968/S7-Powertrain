package frc.robot.EquipmentModules.DriveSystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.SwerveModule;
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
    // Control modules
    //---------------------------------------------------------------- 
    //Front left wheel
    public TalonFX FL_TranslationMotor  = new TalonFX(Parameters.FLT_Can_id);
    public CANSparkMax FL_RotationMotor = new CANSparkMax(Parameters.FLR_Can_id, MotorType.kBrushless);

    //Front Right wheel
    public TalonFX FR_TranslationMotor  = new TalonFX(Parameters.FRT_Can_id);
    public CANSparkMax FR_RotationMotor = new CANSparkMax(Parameters.FRR_Can_id, MotorType.kBrushless);

    //Rear left wheel
    public TalonFX RL_TranslationMotor  = new TalonFX(Parameters.RLT_Can_id);
    public CANSparkMax RL_RotationMotor = new CANSparkMax(Parameters.RLR_Can_id, MotorType.kBrushless);

    //Rear Right wheel
    public TalonFX RR_TranslationMotor  = new TalonFX(Parameters.RRT_Can_id);
    public CANSparkMax RR_RotationMotor = new CANSparkMax(Parameters.RRR_Can_id, MotorType.kBrushless);
    
    // Swerve modules
    public SwerveModule FL_Module  = new SwerveModule(FL_TranslationMotor, FL_RotationMotor);
    public SwerveModule FR_Module  = new SwerveModule(FR_TranslationMotor, FR_RotationMotor);
    public SwerveModule RL_Module  = new SwerveModule(RL_TranslationMotor, RL_RotationMotor);
    public SwerveModule RR_Module  = new SwerveModule(RR_TranslationMotor, RR_RotationMotor);
    
    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------
    private double ResultingVector(double X, double Y){
        return Math.sqrt((X * X)+(Y * Y));
    }

    private double ResultingAngle(double X, double Y){
        return Math.atan2 (Y, X) * (180 / Math.PI);
    }

    public void Swerve(double Left_X, double Left_Y, double Right_X){
        // Connect controller inputs with meaning of swerve inputs
        ProcessValue.Straffe_X  = Left_X;
        ProcessValue.Straffe_Y  = Left_Y * -1;
        ProcessValue.RotationLength = Right_X;

        // Calculate Diagonal to calculate the X and Y of Rotation vector.
        ProcessValue.DiagonalLength = ResultingVector(Settings.Width, Settings.Length);
        ProcessValue.Rotation_X = ProcessValue.RotationLength * (Settings.Length / ProcessValue.DiagonalLength);
        ProcessValue.Rotation_Y = ProcessValue.RotationLength * (Settings.Width  / ProcessValue.DiagonalLength);

        // Calculate resulting X and Y
        FL_Module.ProcessValues.X = ProcessValue.Straffe_X + ProcessValue.Rotation_X;
        FL_Module.ProcessValues.Y = ProcessValue.Straffe_Y + ProcessValue.Rotation_Y;
        FR_Module.ProcessValues.X = ProcessValue.Straffe_X + ProcessValue.Rotation_X;
        FR_Module.ProcessValues.Y = ProcessValue.Straffe_Y - ProcessValue.Rotation_Y;
        RL_Module.ProcessValues.X = ProcessValue.Straffe_X - ProcessValue.Rotation_X;
        RL_Module.ProcessValues.Y = ProcessValue.Straffe_Y + ProcessValue.Rotation_Y;
        RR_Module.ProcessValues.X = ProcessValue.Straffe_X - ProcessValue.Rotation_X;
        RR_Module.ProcessValues.Y = ProcessValue.Straffe_Y - ProcessValue.Rotation_Y;

        // Calculate translation speed
        FL_Module.TranslationVar.ProcessValue.Speed = ResultingVector(FL_Module.ProcessValues.X, FL_Module.ProcessValues.Y);
        FR_Module.TranslationVar.ProcessValue.Speed = ResultingVector(FR_Module.ProcessValues.X, FR_Module.ProcessValues.Y);
        RL_Module.TranslationVar.ProcessValue.Speed = ResultingVector(RL_Module.ProcessValues.X, RL_Module.ProcessValues.Y);
        RR_Module.TranslationVar.ProcessValue.Speed = ResultingVector(RR_Module.ProcessValues.X, RR_Module.ProcessValues.Y);

        // Calculate Rotation angle
        FL_Module.RotationVar.ProcessValue.Angle = ResultingAngle(FL_Module.ProcessValues.X, FL_Module.ProcessValues.Y);
        FR_Module.RotationVar.ProcessValue.Angle = ResultingAngle(FR_Module.ProcessValues.X, FR_Module.ProcessValues.Y);
        RL_Module.RotationVar.ProcessValue.Angle = ResultingAngle(RL_Module.ProcessValues.X, RL_Module.ProcessValues.Y);
        RR_Module.RotationVar.ProcessValue.Angle = ResultingAngle(RR_Module.ProcessValues.X, RR_Module.ProcessValues.Y);

    }

}
