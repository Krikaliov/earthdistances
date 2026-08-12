package com.krikaliov.earthdistances;

/**
 * Handler for any generic command line like this:
 * > pin --keep 50.92460 3.09080 green
 */
public class Command {
  @SuppressWarnings("rawtypes")
  private Argument[] args;
  private final String name;

  private CommandFunction triggerFn;

  public Command(String name) {
    this.name = name;
    this.args = new Argument[0];
  }

  public String getName() {
    return this.name;
  }

  @SuppressWarnings("rawtypes")
  public String getHelp() {
    String msg = "Usage: " + this.getName();
    for (Argument arg : this.args) {
      if (arg instanceof ArgumentRequired) {
        msg += " <" + arg.getArgName() + ">";
      } else if (arg instanceof ArgumentOptional) {
        msg += " [--" + arg.getArgName() + "]";
      }
    }
    return msg;
  }

  public boolean matchesName(String name) {
    return this.name.equals(name);
  }

  @SuppressWarnings("rawtypes")
  public Command addArg(Argument arg) {
    final Argument[] newArgs = new Argument[this.args.length + 1];
    System.arraycopy(this.args, 0, newArgs, 0, this.args.length);
    newArgs[this.args.length] = arg;
    this.args = null;
    this.args = newArgs;
    return this;
  }

  public Command setTrigger(CommandFunction triggerFn) {
    this.triggerFn = triggerFn;
    return this;
  }

  @SuppressWarnings("rawtypes")
  public void trigger(String inputWithoutName) throws BadArgumentException, MissingArgumentException {
    String[] inputArgs = (inputWithoutName.length() < 1) ? new String[0] : inputWithoutName.trim().split(" ");

    for (final Argument arg : this.args) {
      if (inputArgs.length < 1) {
        break;
      }

      if (arg instanceof ArgumentOptional argumentOptional) {
        int pos = 0;
        while (pos < inputArgs.length) {
          if (argumentOptional.matches(inputArgs[pos])) {
            arg.parse(inputArgs[pos]);
            String[] newInputArgs = new String[inputArgs.length - 1];
            System.arraycopy(inputArgs, 0, newInputArgs, 0, pos);
            if (pos + 1 < inputArgs.length) {
              System.arraycopy(inputArgs, pos + 1, newInputArgs, pos, inputArgs.length - 1);
            }
            inputArgs = null;
            inputArgs = newInputArgs;
          } else {
            pos++;
          }
        }
      }
      
      else if (arg instanceof ArgumentRequired) {
        arg.parse(inputArgs[0]);
        String[] newInputArgs = new String[inputArgs.length - 1];
        System.arraycopy(inputArgs, 1, newInputArgs, 0, inputArgs.length - 1);
        inputArgs = null;
        inputArgs = newInputArgs;
      }
    }

    this.triggerFn.exec(this.args);
  }
}
