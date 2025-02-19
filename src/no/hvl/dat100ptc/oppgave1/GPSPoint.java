package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;

public class GPSPoint {

	// TODO - objektvariable
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;

	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - konstruktør
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;

	}

	// TODO - get/set metoder
	public int getTime() {
		return time;

	}

	public void setTime(int time) {
		this.time = time;

	}

	public double getLatitude() {

		return latitude;

	}

	public void setLatitude(double latitude) {

		this.latitude = latitude;

	}

	public double getLongitude() {

		return longitude;
	}

	public void setLongitude(double longitude) {

		this.longitude = longitude;
	}

	public double getElevation() {

		return elevation;

	}

	public void setElevation(double elevation) {

		this.elevation = elevation;
	}

	public String toString() {
		
		
		String str;
		str = "";
		
		str += Integer.toString(this.time); 
		str += " ("; 
		str += Double.toString(this.latitude);
		str += ","; 
		str += Double.toString(this.longitude); 
		str += ") ";
		str += Double.toString(this.elevation);
		str += "\n";
		
		
		
		return str; 
		// TODO - start
	//	return this.time + " (" + this.latitude + "," + this.longitude + ") " + this.elevation + "\n";

		// TODO - slutt

	}
}
