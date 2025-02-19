package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;
		
		
		for (int i = 0; i<gpspoints.length-1; i++) {
			
				distance += GPSUtils.distance(gpspoints[i], gpspoints [i+1]);
			}
		return distance;
		}

		
		

	

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		double a;
		double b;
		

		for (int i = 0; i < gpspoints.length-1; i++) {
			a = gpspoints[i+1].getElevation();
			b =  gpspoints[i].getElevation ();
			if (a > b) {
				elevation += gpspoints[i+1].getElevation() - gpspoints[i].getElevation ();
			}
		}

		return elevation;
	}
		

	

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int sekund = 0;

		for (int i = 0; i < gpspoints.length-1; i++) {
		sekund += (GPSUtils.distance(gpspoints[i], gpspoints[i+1]) * 3.6) / GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		
		return sekund;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		double [] fart = new double[gpspoints.length-1]; 
		
	
		for (int i = 0; i < gpspoints.length-1; i++) {
			
			fart[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			
		}
		
			return fart; 
	}
	
	public double maxSpeed() {
		
		
		double max = GPSUtils.findMax(speeds()); 
		return max; 
		}
		
		 
			
			
		
		
	

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
		for (int i= 0; i<gpspoints.length-1; i++) {
			average += speeds()[i];
		}
		
		average = average / (gpspoints.length-1.5);
		
		return average;
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		if ( speedmph < 10)
			met = 4;
		else if( speedmph >= 10 && speedmph <=12)
			met = 6;
		else if ( speedmph >= 12 && speedmph <= 14)
			met = 8;
		else if ( speedmph >= 14 && speedmph <= 16)
			met = 10;
		else if ( speedmph >= 16 && speedmph <= 20)
			met = 12;
		else 
			met = 16;
		
		
		kcal = met * weight * (secs/60/60);
		return kcal;
		
		
		}

		// TODO - SLUTT
		
	

	public double totalKcal(double weight) {
		double a = 0;
		int tid = 0; 
		double fart = 0; 
		
		int tid2 = 0;
		int tid1 = 0; 
		int secs = 0;
		int sumsec = 0;
		double totalkcal = 0;
		double vekt =0; 
		for ( int i = 0; i < gpspoints.length-1; i ++) {
		}
		for(int i=0; i<gpspoints.length;i++) {
			  totalkcal += kcal(WEIGHT, gpspoints[i+1].getTime() - gpspoints[i].getTime(),GPSUtils.speed(gpspoints[i], gpspoints[i+1]));
			}
			
			totalkcal = kcal(WEIGHT, totalTime(), totalDistance());
		return a ; 
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");
		System.out.println("Total Time     :  " + GPSUtils.formatTime(this.totalTime()));
		System.out.println("Total distance : " + GPSUtils.formatDouble(this.totalDistance()/1000) + " km");
		System.out.println("Total elevation:" + GPSUtils.formatDouble(this.totalElevation()) + " m");
		System.out.println("Max speed      : " + GPSUtils.formatDouble(this.maxSpeed()) + " km/t");
		System.out.println("Average speed  : " + GPSUtils.formatDouble(this.averageSpeed()) + " km/t");
		System.out.println("Energy         :" + GPSUtils.formatDouble(this.totalKcal(WEIGHT)) + " kcal"); 
		System.out.println("==============================================");

		// TODO - START

		
		
		// TODO - SLUTT
		
	}

}
