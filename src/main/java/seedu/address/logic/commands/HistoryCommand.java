package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.task.FacilityContainsKeywords;
import seedu.address.model.task.MaintenanceTask;

/**
 * Lists all maintenance tasks for a specific facility.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks for a facility. "
            + "Parameters: f/FACILITY";
    public static final String MESSAGE_SUCCESS = "Maintenance history for: %1$s\n%2$s";
    public static final String MESSAGE_NO_TASKS = "No maintenance history found for: %1$s";

    private final FacilityContainsKeywords predicate;
    private final String facilityName;

    /**
     * @param facilityName The name of the facility to filter by.
     */
    public HistoryCommand(String facilityName) {
        this.facilityName = facilityName;
        this.predicate = new FacilityContainsKeywords(facilityName);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // Update the model's filtered list so UI syncs and test assertions pass
        model.updateFilteredMaintenanceTaskList(predicate);
        List<MaintenanceTask> matchingTasks = model.getFilteredMaintenanceTaskList();

        if (matchingTasks.isEmpty()) {
            return new CommandResult(String.format(MESSAGE_NO_TASKS, facilityName));
        }

        String taskListString = IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i).toString())
                .collect(Collectors.joining("\n"));

        return new CommandResult(String.format(MESSAGE_SUCCESS, facilityName, taskListString));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HistoryCommand)) {
            return false;
        }

        HistoryCommand otherCommand = (HistoryCommand) other;
        return facilityName.equals(otherCommand.facilityName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("facilityName", facilityName)
                .toString();
    }
}
