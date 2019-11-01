package frc.everlib.utils;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import frc.everlib.shuffleboard.handlers.DashboardConstants;
import frc.everlib.subsystems.motors.subsystems.MotorSubsystem;

/**
 * A utillity class to easily keep constant parameters, and to avoid cluttering constructors.
 */
public class PIDSettings {

    /**Proportional constant supplier*/
    public final Supplier<Double> kP;
    /**Integral Constant supplier*/
    public final Supplier<Double> kI;
    /**Derivative constant supplier */
    public final Supplier<Double> kD;
    /**Feedforward constant supplier*/
    public final Supplier<Double> kF;
    /**Tolernace supplier */
    public final Supplier<Double> tolerance;

    /**The source which supplies the current position / angle / speed */
    public final PIDSource source;

    /**The controller to write the information into.*/
    public final PIDOutput output;


    /**
     * Constructs {@link PIDSettings} according to input P, I and D constants and given subsystem.
     * @param subsystem - The subsystem to use the PID loop on.
     * @param kP - The proportional constants.
     * @param kI - The integral constant
     * @param kD - The derivative constant.
     */
    public PIDSettings(MotorSubsystem subsystem, double kP, double kI, double kD) {

        this.kP = DashboardConstants.addDouble(subsystem.getName() + " PID - kP", kP);
        this.kI = DashboardConstants.addDouble(subsystem.getName() + " PID - kI", kI);
        this.kD = DashboardConstants.addDouble(subsystem.getName() + " PID - kD", kD);

        this.tolerance = () -> 0.0;
        this.kF = () -> 0.0;

        source = subsystem.getSensor();
        output = subsystem;
   
    }


    /**
     * Constructs {@link PIDSettings} according to input P, I and D constants and given subsystem.
     * @param subsystem - The subsystem to use the PID loop on.
     * @param kP - The proportional constants.
     * @param kI - The integral constant
     * @param kD - The derivative constant.
     * @param tolerance - the loop's tolerance; St what point away from the target should the loop
     * be satisfied?
     */
    public PIDSettings(MotorSubsystem subsystem, double kP, double kI, double kD, double tolerance) {
        this.kP = DashboardConstants.addDouble(subsystem.getName() + " PID - kP", kP);
        this.kI = DashboardConstants.addDouble(subsystem.getName() + " PID - kI", kI);
        this.kD = DashboardConstants.addDouble(subsystem.getName() + " PID - kD", kD);
                
        this.tolerance = DashboardConstants.addDouble(
            subsystem.getName() + " PID - Tolerance", tolerance);
        
        this.kF = () -> 0.0;

        source = subsystem.getSensor();
        output = subsystem;
    }


    /**
     * Constructs {@link PIDSettings} according to input P, I and D and F constants and given subsystem.
     * @param subsystem - The subsystem to use the PIDF loop on.
     * @param kP - The proportional constants.
     * @param kI - The integral constant
     * @param kD - The derivative constant.
     * @param tolerance - the loop's tolerance; St what point away from the target should the loop
     * be satisfied?
     * @param kF - The feed-forward constant.
    */
    public PIDSettings(MotorSubsystem subsystem, double kP, double kI, double kD, double tolerance, double kF) {

        this.kP = DashboardConstants.addDouble(subsystem.getName() + " PID - kP", kP);
        this.kI = DashboardConstants.addDouble(subsystem.getName() + " PID - kI", kI);
        this.kD = DashboardConstants.addDouble(subsystem.getName() + " PID - kD", kD);
        this.kF = DashboardConstants.addDouble(subsystem.getName() + " PID - kF", kF);
        
        this.tolerance = DashboardConstants.addDouble(
            subsystem.getName() + " PID - Tolerance", tolerance);
       
        source = subsystem.getSensor();
        output = subsystem::move;
       }
}