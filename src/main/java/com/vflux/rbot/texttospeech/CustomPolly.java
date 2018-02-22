package com.vflux.rbot.texttospeech;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;

import javazoom.jl.decoder.JavaLayerException;


public class CustomPolly {
	
	private AmazonPolly amazonPolly;
	
	public CustomPolly(Region region) {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJHGKJ3GKOLSABU4Q",
				"LPjoQe3Qxr3R58Lm4ysBe21ugFbt7Ai4W3GACTky");
		//
		// Create an Amazon Polly client in a specific region
		//
		this.amazonPolly = AmazonPollyClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region.getName()).build();
	}
	
	public void play(String text) throws IOException, JavaLayerException {
		//
		// Get the audio stream created using the text
		//
		InputStream speechStream = this.synthesize(text, OutputFormat.Mp3);
		//
		// Play the audio
		//
		AudioPlayer.play(speechStream);
	}
	
	public InputStream synthesize(String text, OutputFormat format) throws IOException {
		//
		// Get the default voice
		//
		Voice voice = this.getVoice();
		//
		// Create speech synthesis request comprising of information such as following:
		// Text
		// Voice
		// The detail will be used to create the speech
		//
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
				.withOutputFormat(format);
		//
		// Create the speech
		//
		SynthesizeSpeechResult synthRes = this.amazonPolly.synthesizeSpeech(synthReq);
		//
		// Returns the audio stream
		//
		return synthRes.getAudioStream();
	}
	
	public Voice getVoice() {
		//
		// Create describe voices request.
		//
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
		// Synchronously ask Amazon Polly to describe available TTS voices.
		DescribeVoicesResult describeVoicesResult = this.amazonPolly.describeVoices(describeVoicesRequest);
		return describeVoicesResult.getVoices().get(0);
	}
	
	
	
	

}
