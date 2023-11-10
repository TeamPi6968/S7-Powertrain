package frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;

import frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.RotationVar.RotationVar;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.TranslationVar.TranslationVar;

public class SwerveModule {
    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------
    public TalonFX TranslationMotor;
    public CANSparkMax RotationMotor;
    public TranslationVar TranslationVar = new TranslationVar();
    public RotationVar RotationVar = new RotationVar();
    public ProcessValues ProcessValues = new ProcessValues();
    //----------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------
    public SwerveModule(TalonFX TranslationMotor, CANSparkMax RotationMotor){
        this.TranslationMotor = TranslationMotor;
        this.RotationMotor = RotationMotor;
    }

    //----------------------------------------------------------------
    // Objects
    //----------------------------------------------------------------   



    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------  
  
}
