package frc.robot.EquipmentModules.DriveSystem.ControlModules.RearLeftModule;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.RotationVar.RotationVar;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.TranslationVar.TranslationVar;

public class RearLeftModule {
    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------

    //----------------------------------------------------------------
    // Objects
    //----------------------------------------------------------------   
    public TranslationVar TranslationVar = new TranslationVar();
    public RotationVar RotationVar = new RotationVar();

    public TalonFX TranslationMotor = new TalonFX(TranslationVar.Parameters.Can_id);
    public TalonFX RotationMotor = new TalonFX(RotationVar.Parameters.Can_id);

    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------  
    
}
