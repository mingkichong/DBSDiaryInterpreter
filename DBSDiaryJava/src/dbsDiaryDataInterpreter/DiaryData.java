package dbsDiaryDataInterpreter;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DiaryData {
	
	public static final String DIARY_ENTRY_DATE = "DIARY_ENTRY_DATE";
	public static final String DIARY_ENTRY_TIME = "DIARY_ENTRY_TIME";
	public static final String DIARY_ENTRY_LOCATION_ADDRESS = "DIARY_ENTRY_LOCATION";
	public static final String DIARY_ENTRY_CONTENT = "DIARY_ENTRY_CONTENT";
	public static final String DIARY_ENTRY_LAST_UPDATED_TIME = "DIARY_ENTRY_LAST_UPDATED_TIME";
	public static final String DIARY_ENTRY_CREATED_TIME = "DIARY_ENTRY_CREATED_TIME";
	public static final String DIARY_ENTRY_LOCATION_LAT = "DIARY_ENTRY_LOCATION_LAT";
	public static final String DIARY_ENTRY_LOCATION_LONG = "DIARY_ENTRY_LOCATION_LONG";

	private String diaryDate, diaryTime, diaryLocation, diaryContent, diaryLocationLat, diaryLocationLong;
	private long diaryLastUpdatedTime, diaryCreatedTime;
	
	public DiaryData(){
		this(	"NULL_EMPTY_DATE", 
				"NULL_EMPTY_TIME", 
				"NULL_EMPTY_LOCATION", 
				"NULL_EMPTY_CONTENT", 
				-1L, 
				-1L, 
				"NULL_EMPTY_LOCATION.LAT", 
				"NULL_EMPTY_LOCATION.LONG");
	}
	
	public DiaryData(String diaryDate, String diaryTime, String diaryLocation,
			String diaryContent, long diaryLastUpdatedTime,
			long diaryCreatedTime, String diaryLocationLat,
			String diaryLocationLong) {
		this.diaryDate = diaryDate;
		this.diaryTime = diaryTime;
		this.diaryLocation = diaryLocation;
		this.diaryContent = diaryContent;
		this.diaryLastUpdatedTime = diaryLastUpdatedTime;
		this.diaryCreatedTime = diaryCreatedTime;
		this.diaryLocationLat = diaryLocationLat;
		this.diaryLocationLong = diaryLocationLong;
	}

	public String getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(String diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryTime() {
		return diaryTime;
	}

	public void setDiaryTime(String diaryTime) {
		this.diaryTime = diaryTime;
	}

	public String getDiaryLocation() {
		return diaryLocation;
	}

	public void setDiaryLocation(String diaryLocation) {
		this.diaryLocation = diaryLocation;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public long getDiaryLastUpdatedTime() {
		return diaryLastUpdatedTime;
	}

	public void setDiaryLastUpdatedTime(long diaryLastUpdatedTime) {
		this.diaryLastUpdatedTime = diaryLastUpdatedTime;
	}

	public long getDiaryCreatedTime() {
		return diaryCreatedTime;
	}

	public void setDiaryCreatedTime(long diaryCreatedTime) {
		this.diaryCreatedTime = diaryCreatedTime;
	}

	public String getDiaryLocationLat() {
		return diaryLocationLat;
	}

	public void setDiaryLocationLat(String diaryLocationLat) {
		this.diaryLocationLat = diaryLocationLat;
	}

	public String getDiaryLocationLong() {
		return diaryLocationLong;
	}

	public void setDiaryLocationLong(String diaryLocationLong) {
		this.diaryLocationLong = diaryLocationLong;
	}
	
	
	/*
	 * Convert a DiartData to csv format
	 * Structure:
	 * <Date>,<Time>,<Content>,<Location Address>,<Location latitude>,<Location longitude>,<Created Time>,<Last Updated Time>
	 */
	public String toCSVString(){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.print(this.getDiaryDate()); pw.print(',');
		pw.print(this.getDiaryTime()); pw.print(',');
		pw.print("\"" + this.getDiaryContent() + "\""); pw.print(',');
		pw.print("\"" + this.getDiaryLocation() + "\""); pw.print(',');
		pw.print(this.getDiaryLocationLat()); pw.print(',');
		pw.print(this.getDiaryLocationLong()); pw.print(',');
		pw.print(this.getDiaryCreatedTime()); pw.print(',');
		pw.print(this.getDiaryLastUpdatedTime());
		pw.flush(); pw.close();
		return sw.toString();
	}
}
