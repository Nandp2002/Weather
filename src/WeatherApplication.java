import java.util.*;

// Weather interface
interface Weather {
    void getWeather();
}

// Abstract class for common weather properties labeling date location
abstract class WeatherData implements Weather {
    protected String location;
    protected Date date;

    public WeatherData(String location, Date date) {
        this.location = location;
        this.date = date;
    }

    @Override
    public abstract void getWeather();
}

// Weather exception class
class WeatherException extends Exception {
    public WeatherException(String message) {
        super(message);
    }
}

// Weather implementation class
class WeatherImpl extends WeatherData {
    private double temperature;

    public WeatherImpl(String location, Date date, double temperature) {
        super(location, date);
        this.temperature = temperature;
    }

    @Override
    public void getWeather() {
        System.out.println("Weather on " + date + " in " + location + ": " + temperature + "°C");
    }
}

// Weather forecast class using method overloading
class WeatherForecast extends WeatherData {
    private ArrayList<String> forecast;

    public WeatherForecast(String location, Date date, ArrayList<String> forecast) {
        super(location, date);
        this.forecast = forecast;
    }

    public WeatherForecast(String location, Date date, String... forecast) {
        super(location, date);
        this.forecast = new ArrayList<>(Arrays.asList(forecast));
    }

    @Override
    public void getWeather() {
        System.out.println("Weather forecast for " + location + " on " + date + ":");
        for (String weather : forecast) {
            System.out.println("- " + weather);
        }
    }
}

// Weather history previous temperatures as well as present class using inheritance
class WeatherHistory extends WeatherImpl {
    private Stack<String> history;

    public WeatherHistory(String location, Date date, double temperature) {
        super(location, date, temperature);
        this.history = new Stack<>();
    }

    public void addHistory(String entry) {
        history.push(entry);
    }

    public void printHistory() {
        System.out.println("Weather history for " + location + " on " + date + ":");
        while (!history.isEmpty()) {
            System.out.println("- " + history.pop());
        }
    }
}

// Weather application class where it displays the area of the weather at a time and date
public class WeatherApplication {
    public static void main(String[] args) throws WeatherException {
        Date today = new Date();
        WeatherImpl currentWeather = new WeatherImpl("New York", today, 25.5);
        currentWeather.getWeather();

        ArrayList<String> forecastList = new ArrayList<>();
        forecastList.add("Sunny");
        forecastList.add("Cloudy");
        forecastList.add("Rainy");
        WeatherForecast forecast = new WeatherForecast("London", today, forecastList);
        forecast.getWeather();

        WeatherHistory history = new WeatherHistory("Paris", today, 20.0);
        history.addHistory("Yesterday: 22.0°C");
        history.addHistory("Last week: 18.5°C");
        history.printHistory();
    }
}
