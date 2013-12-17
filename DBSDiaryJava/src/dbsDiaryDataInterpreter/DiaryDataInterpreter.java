package dbsDiaryDataInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DiaryDataInterpreter {

	private final String DIARY_ENTRY_FILE_EXTENSION = "txt"; //File extension for diary entry file

	public DiaryDataInterpreter(){
	}
	
	/*
	 * Method for starting to interpret saved DBS diary files in JSON format
	 */
	public void start(){
		File selectedFolder = this.chooseDirectory();
		ArrayList<File> fileArray = getDiaryEntries(selectedFolder);
		File[] entryFiles = fileArray.toArray(new File[fileArray.size()]);
		ArrayList<DiaryData> diaryEntries = new ArrayList<DiaryData>();
		for(File f : entryFiles){
			DiaryData diaryData = parseJSON(f);
			if(diaryData != null)
				diaryEntries.add(diaryData);
		}
		writeDiaryEntriesToFile(diaryEntries);
	}
	

	/*
	 * Write diary entries to file
	 */
	private void writeDiaryEntriesToFile(ArrayList<DiaryData> diaryEntries){
		File f = this.chooseFile();

		if(f!= null){
			try {
				f.createNewFile();
				PrintWriter pw = new PrintWriter(f);
				for(DiaryData diaryEntry: diaryEntries){
					pw.print(diaryEntry.toCSVString());
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
	 * Read a file and parse its content JSON
	 */
	private DiaryData parseJSON(File file){
		//convert contentString to JSON object
		//http://www.mkyong.com/java/json-simple-example-read-and-write-json/

		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;

			String diaryDate = (String) jsonObject.get(DiaryData.DIARY_ENTRY_DATE);
			String diaryTime = (String) jsonObject.get(DiaryData.DIARY_ENTRY_TIME);
			String diaryLocation = (String) jsonObject.get(DiaryData.DIARY_ENTRY_LOCATION_ADDRESS);
			String diaryContent = (String) jsonObject.get(DiaryData.DIARY_ENTRY_CONTENT);
			long diaryLastUpdated = (Long) jsonObject.get(DiaryData.DIARY_ENTRY_LAST_UPDATED_TIME);
			long diaryCreatedTime = (Long) jsonObject.get(DiaryData.DIARY_ENTRY_CREATED_TIME);
			String diaryLocationLat = (String) jsonObject.get(DiaryData.DIARY_ENTRY_LOCATION_LAT);
			String diaryLocationLong = (String) jsonObject.get(DiaryData.DIARY_ENTRY_LOCATION_LONG);

			return new DiaryData(
					diaryDate, 
					diaryTime,
					diaryLocation,
					diaryContent,
					diaryLastUpdated,
					diaryCreatedTime,
					diaryLocationLat,
					diaryLocationLong);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		fc.setSelectedFile(new File("output.csv"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
		fc.setFileFilter(filter);
		int retrival = fc.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}
}
