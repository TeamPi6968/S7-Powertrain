package frc.robot.EquipmentModules.DriveSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.*;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.SwerveModule;
import frc.robot.EquipmentModules.DriveSystem.DriveSystemVar.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    
    // Encoders of Swerve modules
    public SparkMaxAbsoluteEncoder FL_encoder = FL_Module.RotationMotor.getAbsoluteEncoder(Type.kDutyCycle);
    public SparkMaxAbsoluteEncoder FR_encoder = FR_Module.RotationMotor.getAbsoluteEncoder(Type.kDutyCycle);
    public SparkMaxAbsoluteEncoder RL_encoder = RL_Module.RotationMotor.getAbsoluteEncoder(Type.kDutyCycle);
    public SparkMaxAbsoluteEncoder RR_encoder = RR_Module.RotationMotor.getAbsoluteEncoder(Type.kDutyCycle);
    
    // PID controllers of Swerve modules
    public SparkMaxPIDController FL_PID = FL_Module.RotationMotor.getPIDController();
    public SparkMaxPIDController FR_PID = FR_Module.RotationMotor.getPIDController();
    public SparkMaxPIDController RL_PID = RL_Module.RotationMotor.getPIDController();
    public SparkMaxPIDController RR_PID = RR_Module.RotationMotor.getPIDController();

    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------
    private double ResultingVector(double X, double Y){
        return Math.sqrt((X * X)+(Y * Y));
    }

    private double ResultingAngle(double X, double Y){
        return Math.atan2 (Y, X) * (180 / Math.PI);
    }

    // If controller does nothing there are minimal values still. So with deadspot this will be set to 0.
    public double Deadspot(double input, double deadspot){

        if(Math.abs(input) < deadspot) {
            input = 0;
        }
        return input;
    }

    public void SwerveInit(){
        // Set inverted can be used if the logic of the motor is the oposite. 
        // Since the direction could defer for every motor.
        FL_Module.TranslationMotor.setInverted(false);
        FR_Module.TranslationMotor.setInverted(false);
        RL_Module.TranslationMotor.setInverted(false);
        RR_Module.TranslationMotor.setInverted(false);

        FL_Module.RotationMotor.setInverted(false);
        FR_Module.RotationMotor.setInverted(false);
        RL_Module.RotationMotor.setInverted(false);
        RR_Module.RotationMotor.setInverted(false);

        // Apply position and velocity conversion factors for the turning encoder. We
        // want these in radians and radians per second to use with WPILib's swerve
        // APIs. 
        // NOTE: Not sure what the value should be or if it's needed.
        FL_encoder.setPositionConversionFactor(Settings.DegreeFactor); 
        FR_encoder.setPositionConversionFactor(Settings.DegreeFactor); 
        RL_encoder.setPositionConversionFactor(Settings.DegreeFactor); 
        RR_encoder.setPositionConversionFactor(Settings.DegreeFactor); 

        FL_encoder.setVelocityConversionFactor(Settings.RotationPerSecondsFactor); 
        FR_encoder.setVelocityConversionFactor(Settings.RotationPerSecondsFactor); 
        RL_encoder.setVelocityConversionFactor(Settings.RotationPerSecondsFactor); 
        RR_encoder.setVelocityConversionFactor(Settings.RotationPerSecondsFactor); 

        // Enable PID wrap around for the turning motor. This will allow the PID
        // controller to go through 0 to get to the setpoint i.e. going from 350
        // degrees to 10 degrees will go through 0 rather than the other direction
        // which is a longer route.
        FL_PID.setPositionPIDWrappingEnabled(true);
        FL_PID.setPositionPIDWrappingMinInput(Settings.MinimalAngle);
        FL_PID.setPositionPIDWrappingMaxInput(Settings.MaximumAngle);

        FR_PID.setPositionPIDWrappingEnabled(true);
        FR_PID.setPositionPIDWrappingMinInput(Settings.MinimalAngle);
        FR_PID.setPositionPIDWrappingMaxInput(Settings.MaximumAngle);

        RL_PID.setPositionPIDWrappingEnabled(true);
        RL_PID.setPositionPIDWrappingMinInput(Settings.MinimalAngle);
        RL_PID.setPositionPIDWrappingMaxInput(Settings.MaximumAngle);

        RR_PID.setPositionPIDWrappingEnabled(true);
        RR_PID.setPositionPIDWrappingMinInput(Settings.MinimalAngle);
        RR_PID.setPositionPIDWrappingMaxInput(Settings.MaximumAngle);

        // Set the PID Controller to use the duty cycle encoder on the swerve
        // module instead of the built in NEO550 encoder.
        FL_PID.setFeedbackDevice(FL_encoder);
        FR_PID.setFeedbackDevice(FR_encoder);
        RL_PID.setFeedbackDevice(RL_encoder);
        RR_PID.setFeedbackDevice(RR_encoder);

        // Set the PID gains for the turning motor.
        FL_PID.setP(FL_Module.RotationVar.Settings.P);
        FL_PID.setI(FL_Module.RotationVar.Settings.I);
        FL_PID.setD(FL_Module.RotationVar.Settings.D);
        FL_PID.setOutputRange(FL_Module.RotationVar.Settings.MinimalOutput, FL_Module.RotationVar.Settings.MaximalOutput);

        FR_PID.setP(FR_Module.RotationVar.Settings.P);
        FR_PID.setI(FR_Module.RotationVar.Settings.I);
        FR_PID.setD(FR_Module.RotationVar.Settings.D);
        FR_PID.setOutputRange(FR_Module.RotationVar.Settings.MinimalOutput, FR_Module.RotationVar.Settings.MaximalOutput);

        RL_PID.setP(RL_Module.RotationVar.Settings.P);
        RL_PID.setI(RL_Module.RotationVar.Settings.I);
        RL_PID.setD(RL_Module.RotationVar.Settings.D);
        RL_PID.setOutputRange(RL_Module.RotationVar.Settings.MinimalOutput, RL_Module.RotationVar.Settings.MaximalOutput);

        RR_PID.setP(RR_Module.RotationVar.Settings.P);
        RR_PID.setI(RR_Module.RotationVar.Settings.I);
        RR_PID.setD(RR_Module.RotationVar.Settings.D);
        RR_PID.setOutputRange(RR_Module.RotationVar.Settings.MinimalOutput, RR_Module.RotationVar.Settings.MaximalOutput);

        FL_Module.RotationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        FR_Module.RotationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        RL_Module.RotationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        RR_Module.RotationMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        FL_Module.RotationMotor.setSmartCurrentLimit(FL_Module.RotationVar.Settings.CurrentLimit);
        FR_Module.RotationMotor.setSmartCurrentLimit(FR_Module.RotationVar.Settings.CurrentLimit);
        RL_Module.RotationMotor.setSmartCurrentLimit(RL_Module.RotationVar.Settings.CurrentLimit);
        RR_Module.RotationMotor.setSmartCurrentLimit(RR_Module.RotationVar.Settings.CurrentLimit);

        FL_Module.RotationMotor.burnFlash();
        FR_Module.RotationMotor.burnFlash();
        RL_Module.RotationMotor.burnFlash();
        RR_Module.RotationMotor.burnFlash();
    }

    public void SwerveLoop(double Left_X, double Left_Y, double Right_X){

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

        FL_Module.TranslationVar.ProcessValue.Speed = Deadspot(FL_Module.TranslationVar.ProcessValue.Speed, Settings.deadspot);
        FR_Module.TranslationVar.ProcessValue.Speed = Deadspot(FR_Module.TranslationVar.ProcessValue.Speed, Settings.deadspot);
        RL_Module.TranslationVar.ProcessValue.Speed = Deadspot(RL_Module.TranslationVar.ProcessValue.Speed, Settings.deadspot);
        RR_Module.TranslationVar.ProcessValue.Speed = Deadspot(RR_Module.TranslationVar.ProcessValue.Speed, Settings.deadspot);

        ProcessValue.AddingDeadspots =   FL_Module.TranslationVar.ProcessValue.Speed
                                       + FR_Module.TranslationVar.ProcessValue.Speed
                                       + RL_Module.TranslationVar.ProcessValue.Speed
                                       + RR_Module.TranslationVar.ProcessValue.Speed;

        if(ProcessValue.AddingDeadspots != 0){
            // Calculate Rotation angle
            FL_Module.RotationVar.ProcessValue.Angle = ResultingAngle(FL_Module.ProcessValues.X, FL_Module.ProcessValues.Y);
            FR_Module.RotationVar.ProcessValue.Angle = ResultingAngle(FR_Module.ProcessValues.X, FR_Module.ProcessValues.Y);
            RL_Module.RotationVar.ProcessValue.Angle = ResultingAngle(RL_Module.ProcessValues.X, RL_Module.ProcessValues.Y);
            RR_Module.RotationVar.ProcessValue.Angle = ResultingAngle(RR_Module.ProcessValues.X, RR_Module.ProcessValues.Y);
        }
            
        // Set Speed of wheel
        FL_Module.TranslationMotor.set(ControlMode.PercentOutput, FL_Module.TranslationVar.ProcessValue.Speed);
        FR_Module.TranslationMotor.set(ControlMode.PercentOutput, FR_Module.TranslationVar.ProcessValue.Speed);
        RL_Module.TranslationMotor.set(ControlMode.PercentOutput, RL_Module.TranslationVar.ProcessValue.Speed);
        RR_Module.TranslationMotor.set(ControlMode.PercentOutput, RR_Module.TranslationVar.ProcessValue.Speed);

        // Set Position of wheel
        FL_PID.setReference(FL_Module.RotationVar.ProcessValue.Angle, CANSparkMax.ControlType.kPosition);
        FR_PID.setReference(FR_Module.RotationVar.ProcessValue.Angle, CANSparkMax.ControlType.kPosition);
        RL_PID.setReference(RL_Module.RotationVar.ProcessValue.Angle, CANSparkMax.ControlType.kPosition);
        RR_PID.setReference(RR_Module.RotationVar.ProcessValue.Angle, CANSparkMax.ControlType.kPosition);

        SmartDashboard.putNumber("Front left speed" , FL_Module.TranslationVar.ProcessValue.Speed);
        SmartDashboard.putNumber("Front Right speed", FR_Module.TranslationVar.ProcessValue.Speed);
        SmartDashboard.putNumber("Rear left speed"  , RL_Module.TranslationVar.ProcessValue.Speed);
        SmartDashboard.putNumber("Rear Right speed" , RR_Module.TranslationVar.ProcessValue.Speed);

        SmartDashboard.putNumber("Front left Angle" , FL_Module.RotationVar.ProcessValue.Angle);
        SmartDashboard.putNumber("Front Right Angle", FR_Module.RotationVar.ProcessValue.Angle);
        SmartDashboard.putNumber("Rear left Angle"  , RL_Module.RotationVar.ProcessValue.Angle);
        SmartDashboard.putNumber("Rear Right Angle" , RR_Module.RotationVar.ProcessValue.Angle);
    }

}
