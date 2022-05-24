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

import java.time.LocalDate;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/** The View Class is an abstract class which provides some basic data needed to construct input or output views
  */
abstract class View {

    // Today's date
    public LocalDate todayDate = LocalDate.now();
    public int day = todayDate.getDayOfMonth();
    public int month = todayDate.getMonthValue();
    public int year = todayDate.getYear();

    // Month names and their acronyms(in multiple languages)
    public final static int type_of_month_lang = 2;
    public Map<Integer, String[]> months = new HashMap<Integer, String[]>() {
        {
            put(1,	new String[] {"Jan",	"Jan",	"Janar",	"January"});
            put(2,	new String[] {"Shk",	"Feb",	"Shkurt",	"February"});
            put(3,	new String[] {"Mar",	"Mar",	"Mars",	    "March"});
            put(4,	new String[] {"Pri",	"Apr",	"Prill",	"April"});
            put(5,	new String[] {"Maj",	"May",	"Maj",	    "May"});
            put(6,	new String[] {"Qer",	"Jun",	"Qershor",	"June"});
            put(7,	new String[] {"Kor",	"Jul",	"Korrik",	"July"});
            put(8,	new String[] {"Gus",	"Aug",	"Gusht",	"August"});
            put(9,	new String[] {"Sht",	"Sep",	"Shtator",	"September"});
            put(10,	new String[] {"Tet",	"Oct",	"Tetor",	"October"});
            put(11,	new String[] {"Nen",	"Nov",	"Nentor",	"November"});
            put(12,	new String[] {"Dhj",	"Dec",	"Dhjetor",	"December"});
        }
    };


    // Week labels (in multiple languages)
    public final static int type_of_week_lang = 0;
    public Map<Integer, String[]> weeks = new LinkedHashMap<Integer, String[]>() {
        {
            put(1,	new String[] {"Ha", "Mo"});
            put(2,	new String[] {"Ma", "Tu"});
            put(3,	new String[] {"Me", "We"});
            put(4,	new String[] {"En", "Th"});
            put(5,	new String[] {"Pr", "Fr"});
            put(6,	new String[] {"Sh", "Sa"});
            put(7,	new String[] {"Di", "Su"});
        }
    };
}
