package com.harshil.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.harshil.models.LocationStats;

@Service
public class CoronaVirusDeathService {

	private static String VIRUS_DATA_URL_DEATH="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
	
	private List<LocationStats> allstats_death=new ArrayList<>();
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchDeathData() throws IOException, InterruptedException {
    List<LocationStats> newstats_death=new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request=HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL_DEATH))
				.build();
		HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader=new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationstat=new LocationStats();
		    locationstat.setState(record.get("Province/State"));
		    locationstat.setCountry(record.get("Country/Region"));
		    int DeathCases=Integer.parseInt(record.get(record.size()-1));
		    int prevDeathCases=Integer.parseInt(record.get(record.size()-2));
		    locationstat.setLatestDeathCases(DeathCases);
		    locationstat.setDiffdeathFromPrevDay(DeathCases-prevDeathCases);
		    newstats_death.add(locationstat);
		}
		this.allstats_death=newstats_death;
	}

	public List<LocationStats> getAllstatsDeath() {
		return allstats_death;
	}
}
