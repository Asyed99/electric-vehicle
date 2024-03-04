Electric Vehicles (EVs) are becoming more prevalent around the world. Tesla used to be the best known manufacturer of EVs, but as of 2023, Ford, GM, BMW, Kia and Volvo all have major offerings. 

This project focuses on creating a class to represent electric vehicles, and calculate their ranges based on a number of characteristics and criteria.

This project comprises of an ElectricVehicle (note the Pascal-case naming here) class that represents an electric vehicle. ElectricVehicle instances know their names (String), battery size (double), state of charge (double), current efficiency (double), and default efficiency (double - the "rated" efficiency of the vehicle). 
An electric vehicle calculates its range by multiplying its current efficiency * state of charge * battery size.


If the current temperature is in this range: 65.0F <= current temp <= 77.0F then the current efficiency is 100% of the default efficiency.
If the current temperature is in this range: current temp > 77.0F then the current efficiency is 85% of the default efficiency.
If the current temperature is in this range: current temp < 65.0F then the current efficiency is reduced by 1% for every degree fahrenheit below 65 degrees, up to a maximum decrease of 50%. For example, at 64.0F, the EV's current efficiency would be 99.0% of its default efficiency. At 15.0F, the current efficiency would be 50% the default. Likewise, at 0 degrees fahrenheit, the current efficiency would still be 50% of the default. Fractional efficiency losses are possible, so at 64.1F, the current efficiency would be 99.1%.
