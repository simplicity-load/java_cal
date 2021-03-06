# cal(1) clone in Java

Simple project reproducing a similarly functioning program as the `cal(1)` program from the `util-linux` package.

## Building the project

Get a copy of the repository by running

`git clone https://github.com/simplicity-load/java_cal.git`

You can compile the whole project by changing into the `src/` directory and running

`javac Main.java`

## Usage

**Install ''Windows Terminal''(or any other terminal that emulates VT100) from the ''Windows Store'' before using for the full experience**

You can learn how to use it by running

`java Main -h`

That will output this help message

```
cal(1) clone (in Java)

Usage:
java Main [options] [[month] year] [month year]

Display a calendar, or some part of it.
Without any arguments, display the current month.

Options:
-1          show only a single month (default)
-3          show three months spanning the date
-n <num>    show num months starting with date's month
-y          show the whole year
```

# License

Copyright (C) 2022 simplicity-load

This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License along with this program. If not, see https://www.gnu.org/licenses/.
