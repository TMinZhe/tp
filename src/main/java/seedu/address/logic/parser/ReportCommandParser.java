package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReportCommand object.
 */
public class ReportCommandParser implements Parser<ReportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReportCommand
     * and returns a ReportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ReportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MONTH);

        if (!argMultimap.getValue(PREFIX_MONTH).isPresent()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportCommand.MESSAGE_USAGE));
        }

        String monthStr = argMultimap.getValue(PREFIX_MONTH).get().trim();

        try {
            YearMonth yearMonth = YearMonth.parse(monthStr);
            return new ReportCommand(yearMonth);
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportCommand.MESSAGE_USAGE));
        }
    }
}
