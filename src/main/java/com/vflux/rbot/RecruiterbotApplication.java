package com.vflux.rbot;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.vflux.rbot.texttospeech.CustomPolly;

import javazoom.jl.decoder.JavaLayerException;

@SpringBootApplication
public class RecruiterbotApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(RecruiterbotApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws IOException, JavaLayerException  {
		//
		// Sample Hello World Text
		//
		String sampleText = "Hello World! How are you doing? This is Polly. I am happy to talk with you.";
		//
		// create the CustomPolly class 
		//
		CustomPolly customPolly = new CustomPolly(Region.getRegion(Regions.US_EAST_1));
		//
		// Have CustomPolly play the text to speech
		//
		customPolly.play(sampleText);
	}

}