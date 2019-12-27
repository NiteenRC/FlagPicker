## FlagPicker application
This application has continents and continent has countries and each country has flag.


##This application has covered below points.
1.Rest end points to fetch data based on user inputs
2.JUNIT test cases
3.Global Exceptions
4.Performance monitoring for all service
5.Metric service to provide number of times continent/country has been viewed.  


############ REST END POINTS #################

## FETCH ALL CONTINENTS ##
	METHOD	TYPE	: GET
	URL				: http://localhost:8090/flagpicker/continent/fetchAll
	REQUEST BODY	: NA

## FETCH COUNTRIES BY CONTINENT ##
	METHOD	TYPE	: GET
	URL				: http://localhost:8090/flagpicker/continent/Africa
	REQUEST BODY	: NA

## FETCH FLAG BY COUNTRY ##
	METHOD	TYPE	: GET
	URL				: http://localhost:8090/flagpicker/continent/country/Nigeria
	REQUEST BODY	: NA

## METRICS FOR ALL REST END POINTS ##
	METHOD	TYPE	: GET
	URL				: http://localhost:8090/flagpicker/metric
	REQUEST BODY	: NA