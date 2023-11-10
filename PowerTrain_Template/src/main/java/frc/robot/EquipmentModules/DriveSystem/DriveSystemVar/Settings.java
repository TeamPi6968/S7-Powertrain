package frc.robot.EquipmentModules.DriveSystem.DriveSystemVar;

//====================================================================
// Settings structure
//====================================================================

/** 
 * Settings are values that you change from start of developing but after that it doesn't need to change that often anymore.
 * NOTE: When variable value is set then make the variable final (constant).
*/
public class Settings {
    public final int Length = 600; // [mm] Length from front wheel to rear wheel
    public final int Width  = 600; // [mm] Width from Left wheel to right wheel
    public final double RadiansFactor = 2 * Math.PI; // Radians
    public final double RadiansPerSecondsFactor = (2 * Math.PI) / 60; // Radian / seconds
    public final double MinimalAngle = Math.toRadians(0);
    public final double MaximumAngle = Math.toRadians(360);
}
