# earthdistances

## Overview

``earthdistances`` allows us to measure the fly distance between any two points, called "pins", on the surface of the Earth without regarding the altitude and the ocean depth of each point. The pins are stored in a 2-pins stack structure, which means that it has a capacity of 2 pins and a LIFO structure.

## How to

### Setup

This is a Java software so you must install a Java Runtime Environment on your machine before installing ``earthdistances``. [Java 8](https://www.java.com/en/download/manual.jsp) is enough but you must be able to run this software with any higher version of JRE.

Once a JRE is installed, you can download the latest release of the ``earthdistances`` executable available on this Github pages (releases section) and launch it on your machine.

### Usage

When you launch ``earthdistances`` from a terminal, you should see a prompt that allow you to enter commands. The prompt displays the current state of the 2-pins stack after each command execution ahead like this:

 - `[() , ()]<>` when the stack is empty
 - `[(44.602 -124.054) , ()]<>` when the stack only contains 1 pin (*here at position 44.602 -124.054*)
 - `[(48.865 2.336) , (44.602 -124.054) , ()]<14041.788 km>` when the stack is full showing the distance between both pins.

### Commands

> ?

List all available commands.

> help <cmd>

Display how to use for the given command `cmd`. Use **.** to list all available commands.

> quit

Quit the program.

> clear

Empty the stack.

> pin [--lower] <theta> <phi> <color>

Create a new pin on the Earth at latitude `theta` and longitude `phi` with a `color` among green, red, blue, yellow and pink (this has not yet any effect because `earthdistances` is still a command-line tool).

When the `--lower` optional argument is triggered, the newly created pin is pushed down the stack. Thus, it has no effect if the stack was empty.

### DISCLAIMER

This is a beta version of `earthdistances`. You may encounter several bugs or technical issues while using this software. You can report any issue on the Github repository of this project by providing system info, screenshots and detailed steps to reproduce the bug you encounter.

## Thank you !
