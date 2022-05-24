/*
    Cal(1) clone written in Java
    Copyright (C) 2022  simplicity-load
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.
    
    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

/** An example Controller module for the OutputView and InputView Classes
  */
class Main {
    public static void main(String[] args) {
        InputView iv = new InputView(args);
        OutputView ov = new OutputView();
        if (iv.parseArgs())
            ov.printRange(iv.beginMonth, iv.beginYear, iv.endMonth, iv.endYear);
    }
}
