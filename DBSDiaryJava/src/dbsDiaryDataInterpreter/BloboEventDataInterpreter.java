package dbsDiaryDataInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

public class BloboEventDataInterpreter {

	private final String DIARY_ENTRY_FILE_EXTENSION = "txt"; //File extension for diary entry file

	public BloboEventDataInterpreter(){
	}
	
	public void start(){
		File selectedFolder = this.chooseDirectory();
		ArrayList<File> fileArray = getDiaryEntries(selectedFolder);
		File[] entryFiles = fileArray.toArray(new File[fileArray.size()]);
		ArrayList<BloboEventData> bloboEventEntries = new ArrayList<BloboEventData>();
		for(File f : entryFiles){
			ArrayList<BloboEventData> beData = parseBloboEventData(f);
			if(beData != null){
				bloboEventEntries.addAll(beData);
			}
		}
		//Write to file
		writeBloboEventsToFile(bloboEventEntries);
	}
	
	/*
	 * Write Blobo events to file
	 */
	private void writeBloboEventsToFile(ArrayList<BloboEventData> bloboEventEntries){
		File f = this.chooseFile();

		if(f!= null){
			try {
				f.createNewFile();
				PrintWriter pw = new PrintWriter(f);
				for(BloboEventData entry: bloboEventEntries){
					pw.print(entry.toCSVString());
					pw.println();
					pw.flush();
				}					
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * parse records of Blobo squeezed events
	 * input structure:
	 * <Created Time>;<Location Latitude>,<Location Longitude>;<Recorded Pressure>:<Pressure Threshold>;<Date>;<Time>
	 */
	private ArrayList<BloboEventData> parseBloboEventData(File f){
		BufferedReader br = null;
		ArrayList<BloboEventData> beData = new ArrayList<BloboEventData>();
		try {
			br = new BufferedReader(new FileReader(f));
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(sCurrentLine, ";");
				String createdTimeString = st.nextToken();
				String locationString = st.nextToken();
				String pressureInfoString = st.nextToken();
				String dateString = st.nextToken();
				String timeString = st.nextToken();
				
				st = new StringTokenizer(locationString, ",");
				String latitudeString = st.nextToken();
				String longitudeString = st.nextToken();
				
				st = new StringTokenizer(pressureInfoString, ":");
				String pressureString = st.nextToken();
				String pressureThresholdString = st.nextToken();
				
				BloboEventData bed = new BloboEventData(
						createdTimeString,
						latitudeString,
						longitudeString,
						pressureString,
						pressureThresholdString,
						dateString,
						timeString);
				beData.add(bed);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beData;
	}
	
	/*
	 * Traverse a selected directory and get all of diary entry files that are inside the selected directory
	 */
	private ArrayList<File> getDiaryEntries(File selectedFile){
		ArrayList<File> diaryEntryArray = new ArrayList<File>();
		if(selectedFile.isFile()){
			String ext = FilenameUtils.getExtension(selectedFile.getName());
			if(ext.toLowerCase().endsWith(DIARY_ENTRY_FILE_EXTENSION)){ //check file extension
				diaryEntryArray.add(selectedFile);
			}
		} else if(selectedFile.isDirectory()){
			File [] diaryDates = selectedFile.listFiles();
			for(File f : diaryDates){
				diaryEntryArray.addAll(getDiaryEntries(f));
			}
		}
		return diaryEntryArray;
	}
	
	
	/*
	 * Pop up a FileChooser for selecting a directory
	 */
	private File chooseDirectory(){
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(null);
		return fc.getSelectedFile();
	}
	
	/*
	 * Select an output file. Default name "output.csv".
	 */
	private File chooseFile(){
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(new File("blobo_events_output.csv"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
		fc.setFileFilter(filter);
		int retrival = fc.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
