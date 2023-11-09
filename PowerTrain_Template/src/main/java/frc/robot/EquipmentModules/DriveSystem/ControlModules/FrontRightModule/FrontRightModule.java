package frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontRightModule;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.RotationVar.RotationVar;
import frc.robot.EquipmentModules.DriveSystem.ControlModules.FrontLeftModule.TranslationVar.TranslationVar;

public class FrontRightModule {
    //----------------------------------------------------------------
    // Variables
    //----------------------------------------------------------------

    //----------------------------------------------------------------
    // Standard structure
    //----------------------------------------------------------------   
    public TalonFX TranslationMotor = new TalonFX(0);
    public TalonFX RotationMotor = new TalonFX(1);

    public TranslationVar TranslationVar = new TranslationVar();
    public RotationVar RotationVar = new RotationVar();

    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------  
    
}
