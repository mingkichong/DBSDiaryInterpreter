package dbsDiaryDataInterpreter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BloboEventData {
	private String bloboCreatedTime;
	private String bloboLocationLat, bloboLocationLong;
	private String bloboPressure, bloboPressureThreshold;
	private String bloboDate, bloboTime;
	
	
	
	public BloboEventData(String bloboCreatedTime, String bloboLocationLat,
			String bloboLocationLong, String bloboPressure,
			String bloboPressureThreshold, String bloboDate, String bloboTime) {
		super();
		this.bloboCreatedTime = bloboCreatedTime;
		this.bloboLocationLat = bloboLocationLat;
		this.bloboLocationLong = bloboLocationLong;
		this.bloboPressure = bloboPressure;
		this.bloboPressureThreshold = bloboPressureThreshold;
		this.bloboDate = bloboDate;
		this.bloboTime = bloboTime;
	}
	
	/*
	 * Convert a BloboEvent to csv format
	 * Structure:
	 * <Date>,<Time>,<Created Time (long)>,<Latitude>,<Longitude>,<Recorded Pressure>,<Recorded Pressure Threshold>
	 */
	public String toCSVString() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.print(this.getBloboDate()); pw.print(',');
		pw.print(this.getBloboTime()); pw.print(',');
		pw.print(this.getBloboCreatedTime()); pw.print(',');
		pw.print(this.getBloboLocationLat()); pw.print(',');
		pw.print(this.getBloboLocationLong()); pw.print(',');
		pw.print(this.getBloboPressure()); pw.print(',');
		pw.print(this.getBloboPressureThreshold()); pw.print(',');
		
		pw.flush(); pw.close();
		return sw.toString();
	}



	public String getBloboCreatedTime() {
		return bloboCreatedTime;
	}
	public void setBloboCreatedTime(String bloboCreatedTime) {
		this.bloboCreatedTime = bloboCreatedTime;
	}
	public String getBloboLocationLat() {
		return bloboLocationLat;
	}
	public void setBloboLocationLat(String bloboLocationLat) {
		this.bloboLocationLat = bloboLocationLat;
	}
	public String getBloboLocationLong() {
		return bloboLocationLong;
	}
	public void setBloboLocationLong(String bloboLocationLong) {
		this.bloboLocationLong = bloboLocationLong;
	}
	public String getBloboPressure() {
		return bloboPressure;
	}
	public void setBloboPressure(String bloboPressure) {
		this.bloboPressure = bloboPressure;
	}
	public String getBloboPressureThreshold() {
		return bloboPressureThreshold;
	}
	public void setBloboPressureThreshold(String bloboPressureThreshold) {
		this.bloboPressureThreshold = bloboPressureThreshold;
	}
	public String getBloboDate() {
		return bloboDate;
	}
	public void setBloboDate(String bloboDate) {
		this.bloboDate = bloboDate;
	}
	public String getBloboTime() {
		return bloboTime;
	}
	public void setBloboTime(String bloboTime) {
		this.bloboTime = bloboTime;
	}
	
	
	
}
