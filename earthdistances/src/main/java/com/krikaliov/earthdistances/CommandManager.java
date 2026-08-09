package com.krikaliov.earthdistances;

import java.util.HashMap;

public class CommandManager {
  // > help
  // > pin --keep 50.92460 3.09080 green
  // > quit
  private final HashMap<String, Command> commands = new HashMap<>();

  // ------------------
  // HELP ('?') SECTION
  // ------------------
  protected void helpFn(String cmd) {
    if (".".equals(cmd)) {
      System.out.println("List of available commands:");
      for (final String c : this.commands.keySet().toArray(new String[this.commands.size()])) {
        System.out.println(" - " + c);
      }
      System.out.println("Type 'help <cmd>' to get info details about any command.");
    } else {
      final String helpMsg = (this.commands.get(cmd) == null)
        ? cmd + " is not recognized as a command line!"
        : this.commands.get(cmd).getHelp();
      System.out.println(helpMsg);
    }
  }
  @SuppressWarnings("rawtypes")
  protected final CommandFunction helpCmdFn = (Argument[] args) -> {
    final String cmd = (String) args[0].getValue();
    this.helpFn(cmd);
  };
  protected final String[] helpArgValues = { ".", "?", "help", "quit", "pin" };

  // -------------
  // BUILDING CMDS
  // -------------
  @SuppressWarnings("rawtypes")
  public CommandManager(App app) {
    // HELP
    this.commands.put("help", new Command("help")
      .addArg(new ArgumentStringSet("cmd", helpArgValues))
      .setTrigger(this.helpCmdFn)
    );
    // ?
    this.commands.put("?", new Command("?")
      .setTrigger((Argument[] _) -> {
        this.helpFn(".");
      })
    );
    // QUIT
    this.commands.put("quit", new Command("quit")
      .setTrigger(app.quitFn)
    );
    // PIN
    this.commands.put("pin", new Command("pin")
      .addArg(new ArgumentDouble("theta"))
      .addArg(new ArgumentDouble("phi"))
      .addArg(new ArgumentStringSet("color", PinDataViewer.colors))
      .setTrigger((Argument[] args) -> {
        final double theta = (double) args[0].getValue();
        final double phi = (double) args[1].getValue();
        final String color = (String) args[2].getValue();
        PinDataManager.getInstance().push(new PinData(theta, phi, color));
      })
    );
  }

  // ---------------------
  // SCANNING INPUT BUFFER
  // ---------------------
  public void scan(String buf) {
    final String cmdName = buf.trim().split(" ")[0];
    try {
      final String inputWithoutName = buf.substring(cmdName.length()).trim();
      final Command cmd = this.commands.get(cmdName);
      cmd.trigger(inputWithoutName);
    } catch (BadArgumentException | MissingArgumentException e) {
      System.out.println(e.getMessage());
    } catch (NullPointerException e) {
      System.out.println(cmdName + " is not recognized as a command!");
    }
  }
}
