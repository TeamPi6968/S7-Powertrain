package frc.robot.EquipmentModules.General.Controller.ControllerVar;

//====================================================================
// ProcessValue structure
//====================================================================

/** 
 * When a variable is switching within a few defined states.
*/
public class Status {
    private boolean ToggleState = false;

    public boolean Toggle(boolean ControllerButton){
      
      if (ControllerButton) {
        if (this.ToggleState) {
           // Current state is true so turn off
           this.ToggleState = false;
        } else {
           // Current state is false so turn on
           this.ToggleState = true;
        } 
     }

     return this.ToggleState;
    }
}
