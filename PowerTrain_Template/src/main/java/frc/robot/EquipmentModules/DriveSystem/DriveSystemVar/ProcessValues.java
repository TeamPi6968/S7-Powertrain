package frc.robot.EquipmentModules.DriveSystem.DriveSystemVar;

//====================================================================
// ProcessValue structure
//====================================================================

/** 
 * Process values are outputs of an certain calculatons.
*/
public class ProcessValues {
    // public
    public double Straffe_X;
    public double Straffe_Y;
    public double RotationLength;
    public double Rotation_X;
    public double Rotation_Y;
    public double DiagonalLength;
    public double AddingDeadspots;

    // private
    private double MinOld;
    private double MaxOld;
    private double MinNew;
    private double MaxNew;
    private double OldRange;
    private double NewRange;
    private double SpeedFactor;

    //----------------------------------------------------------------
    // Objects
    //----------------------------------------------------------------
    Setpoints Setpoints = new Setpoints();
    //----------------------------------------------------------------
    // Functions
    //----------------------------------------------------------------
    /**
    * Returns the resulting vector of the X and Y inputs.
    *
    * @param X X coördinate
    * @param Y Y coördinate
    * @return resulting vector of X and Y.
    */
    public double ResultingVector(double X, double Y){
        return Math.sqrt(Math.pow(X,2) + Math.pow(Y,2));
    }

    /**
    * Returns the angle in degrees of the X and Y inputs.
    *
    * @param X X coördinate
    * @param Y Y coördinate
    * @return Angle in degrees
    */
    public double ResultingAngle(double X, double Y){
        return Math.atan2 (X, Y) * (180 / Math.PI);
    }

    /**
    * Returns the controller value with a added deadspot. If the input is within the deadspot the value will be 0.
    *
    * @param Input X coördinate
    * @param DeadSpot Y coördinate
    * @return {@code Input} value unless it's inside the deadspot range. then it returns 0.
    */
    public double Deadspot(double Input, double DeadSpot){

        if(Math.abs(Input) < DeadSpot) {
            Input = 0;
        }
        return Input;
    }

    public void SetMaxSpeed(double SpeedFactor){
        Setpoints.MaxSpeedFactor = SpeedFactor;
    }

    /**
    * Returns value of the old range to the value for the new range.
    *
    * @param Value Old value in old range
    * @param OldRange The old range = {{@code min[0], max[1]}}
    * @param NewRange The new range = {{@code min[0], max[1]}}
    * @return The converted value for the new range.
    */
    public double NormalizeRange(double Value, double[] OldRange, double[] NewRange){
        // Old range minimal/maximal
        this.MinOld = OldRange[0];
        this.MaxOld = OldRange[1];
        this.OldRange = this.MaxOld - this.MinOld;
       
        // New range minimal/maximal
        this.MinNew = NewRange[0];
        this.MaxNew = NewRange[1];
        this.NewRange = (Setpoints.MaxSpeedFactor * this.MaxNew) - this.MinNew;

        // Convert old range into new range
        return (((Value - this.MinOld) * this.NewRange) / this.OldRange) + this.MinNew;
    }

    /**
    * Returns angle from range -180 | 180 to range 0 | 360
    *
    * @param Angle Calculated Angle with range of -180 | 180
    * @return Angle with range of 0 | 360
    */
    public double SetAngleRange(double Angle){
        if(Angle < 0){
            Angle = 360 + Angle;
        }

        return Angle;
    }
    


    
}
