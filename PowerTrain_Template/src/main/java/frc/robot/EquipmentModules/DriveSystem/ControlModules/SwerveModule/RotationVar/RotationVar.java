package frc.robot.EquipmentModules.DriveSystem.ControlModules.SwerveModule.RotationVar;
import com.revrobotics.SparkMaxAbsoluteEncoder.*;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

public class RotationVar {
    private CANSparkMax RotationMotor;

    public RotationVar(CANSparkMax RotationMotor){
        this.RotationMotor = RotationMotor;
    }

    public ProcessValues ProcessValue      = new ProcessValues();
    public Setpoints Setpoint              = new Setpoints();
    public Status Status                   = new Status();
    public Settings Settings               = new Settings();
    public Parameters Parameters           = new Parameters();
    public SparkMaxAbsoluteEncoder Encoder = RotationMotor.getAbsoluteEncoder(Type.kDutyCycle);
    public SparkMaxPIDController PID       = RotationMotor.getPIDController();
}