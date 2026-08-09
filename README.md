# earth-distances

## Overview

``earth-distances`` allows us to measure the direct-line distance between any two points on the surface of the Earth without regarding the altitude and the ocean depth of each point.

## How to

### Setup

This is a java software so you must install a Java Runtime Environment on your machine before installing ``earth-distances``.

Once a JRE is installed, you can download the latest release of the ``earth-distances`` executable available on this Github pages (releases section) and launch it on your machine.

### Usage

When you launch ``earth-distances`` from a terminal, you should see a prompt that allow you to enter commands. The prompt displays the current state of the 2-pins stack after each command execution ahead like this:

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

> pin <theta> <phi> <color>

Create a new pin on the Earth at latitude `theta` and longitude `phi` with a `color` among green, red, blue, yellow and pink (this has not yet any effect because `earth-distances` is still a command-line tool).

### Disclaimer

This is a beta version of `earth-distances`. You may encounter several bugs or technical issues while using this software. You can report any issue on the Github repository of this project by providing system info, screenshots and detailed steps to reproduce the bug you encounter.

## Thank you !
