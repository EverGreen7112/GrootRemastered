package frc.everlib.betamimic;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/** 
 * A base classs mimicking the 2020 WPILib class <i>ParallelCommandGroup.</i> <p>
 * 
 * Runs commands in parallel. The commands can be set at the 
 * {@link #ParallelCommandGroup(Command) constructor}, or using the protected 
 * {@link #addParallel(Command, double)} when exetnding this. <p>
 * 
 * <i>Note:</i> in the 2020 version, this will be more simple class, and the method will be called
 * addCommands, and take an array. <p>
 * 
 * (in its implementation here,  this simply provided a direct constructor for
 * {@link CommandGroup}.
 */
public class ParallelCommandGroup extends CommandGroup{

    /**
     * Constructs a new {@link ParallelCommandGroup} that runs input commands. 
     * @param commands - the commands to run.
     */
    public ParallelCommandGroup(Command... commands) {
        for (Command command : commands) {
            addParallel(command);
        }
    }
}