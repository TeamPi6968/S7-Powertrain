package frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.RotationVar;

//====================================================================
// Settings structure
//====================================================================

/** 
 * Settings are values that you change from start of developing but after that it doesn't need to change that often anymore.
 * NOTE: When variable value is set then make the variable final (constant).
*/
public class Settings {
    public final double P = 1;
    public final double I = 0;
    public final double D = 0;
    public final int MinimalOutput = -1;
    public final int MaximalOutput = -1;
    public final int CurrentLimit = 20;
}
