package imp_main;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;


import ReadData.ReadFiles;
import WriteData.ArchiveType;
import WriteData.WriteFiles;
import init.Initialization;

public class Imp_Local implements WriteFiles, Initialization,ReadFiles {
	
	/**
	 * Dvije liste radi cuvanja naziva i drugih osobina u daljoj implementaciji
	 */
	ArrayList<String> lista = new ArrayList<>();
	ArrayList<String> pomocna = new ArrayList<>();
	static Imp_Local imp_Local = new Imp_Local();
	static Settings settings;
	
	
	
	/**
	 * Citamo meta data lokaciju za root
	 * npr. "C:\\Programfiles\\Users\\marko"
	 * koja mora biti upisana u config fajl koji se prosljedjuje u prvoj liniji fajla
	 */
	@Override
	public void initialization(File configFile) {
		
		
		Gson gson = new Gson();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(configFile));
			
			
			settings = gson.fromJson(br, Settings.class);
			String root = settings.getRootPath() + settings.getRootName();
			new File(root).mkdirs();
			String[] tmp = settings.getBannedExtension().split(",");
			for (String extension : tmp) {
				imp_Local.forbiddenFiles.add(extension);
			}
			
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveFile(File file, String path) {
		
		path = path + file.getName();
			if(checkFileBan(file)) {
			
			try {
				Files.move(Paths.get(file.getAbsolutePath()), Paths.get(path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void saveFileMetedata(File file, File meta, String pathFile) {
		String metaName = meta.getName();
		String metaPath = pathFile + metaName;
		pathFile = pathFile + file.getName();
		
		if(checkFileBan(file)) {
			if(checkFileBan(meta)) {
		
				
				try {
					Files.move(Paths.get(file.getAbsolutePath()), Paths.get(pathFile));
					Files.move(Paths.get(meta.getAbsolutePath()), Paths.get(metaPath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}


	@Override
	public void saveFileCollection(ArrayList<File> fileList, String pathFiles) {
		
		for(File f : fileList) {
			if(f.isFile() && checkFileBan(f)) {
				pathFiles = pathFiles + f.getName();
				try {
					Files.move(Paths.get(f.getAbsolutePath()), Paths.get(pathFiles));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Pravimo folder sa zadatim imenom i izabranom putanjom
	 */
	@Override
	public void createFolder(String folderName, String path) {
		new File(path + "\\" + folderName).mkdir();
		
	}

	@Override
	public void saveFileCollectionArchive(String path, ArrayList<File> fileList, String archiveName) {
	
		archiveFileCollection(fileList, archiveName, ArchiveType.ZIP);
		
	}

	@Override
	public void saveFileCollectionArchive(String path, ArrayList<File> fileList, String archiveName, File metadata) {
		zipFileCollection(fileList, archiveName);
		
	}

	@Override
	public void saveFileCollectionArchive(String path, ArrayList<File> fileList, String archiveName,
			HashMap<String, String> metadata) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	@Override
	public ArrayList<String> getAllFoldersInDirectory(String path) {
		
		File file = new File(path);
		
		File[] s = file.listFiles();
		ArrayList<String> listaFoldera = new ArrayList<>();
		for(File f : s) {
			listaFoldera.add(f.getName());
		}
	
		
		
		return listaFoldera;
	}

	@Override
	public ArrayList<String> getAllFilesInDirectory(String path) {
		
		File file = new File(path);
		File listaOdRoota[] = file.listFiles();
	
		for(File f : listaOdRoota) {
			if(f.isDirectory()) {
				lista.add(f.getName());
				getAllFilesInDirectory(f.getAbsolutePath());
			}else {
				lista.add(f.getName());
			}
		}

		return lista;
	}

	@Override
	public void downloadFiles(File file, String whereToStore) {
		File[] files = file.listFiles();
		ArrayList<String> lista = new ArrayList<>();
		for(File f : files) {
			if(f.isFile()) {
				lista.add(f.getAbsolutePath());
			}
		}
		
		for(int i = 0; i <lista.size();i++) {
			File f1 = new File(lista.get(i));
			//ako ne postoji napravice se novo, jer neko moze i rucno da ukuca
			//me mora da se bira preko necega putanja
			new File(whereToStore).mkdirs();
			
			File f2 = new File(whereToStore+ "/" + f1.getName());
			try {
				Files.copy(Paths.get(f1.getAbsolutePath()), Paths.get(f2.getAbsolutePath()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public ArrayList<String> getFilesNames(String directoryPath, String fileName) {
		File file = new File(directoryPath);
		File listaOdRoota[] = file.listFiles();
	
		for(File f : listaOdRoota) {
			if(f.isDirectory()) {
				lista.add(f.getName());
				getAllFilesInDirectory(f.getAbsolutePath());
			}else {
				lista.add(f.getName());
			}
		}

		return lista;
	}

	@Override
	public ArrayList<String> getFilesNamesLeaf(String directoryPath, String fileName) {
		File file = new File(directoryPath);
		File[] files = file.listFiles();
		ArrayList<String> lista = new ArrayList<>();
		for(File f : files) {
			if(f.isFile()) {
				lista.add(f.getName());
			}
		}
		return lista;
	}

	@Override
	public ArrayList<String> getFilesExtensions(String directoryPath, String extensionType) {
		
		File file = new File(directoryPath);
		File listaOdRoota[] = file.listFiles();
		
	
		for(File f : listaOdRoota) {
			if(f.isDirectory()) {
				lista.add(f.getName());
				getAllFilesInDirectory(f.getAbsolutePath());
			}else {
				lista.add(f.getName());
			}
		}
		
		
		for(int i = 0; i< lista.size();i++) {
			int lastIndexOf = lista.get(i).lastIndexOf(".");
		    if (lastIndexOf == -1) {
		        continue;
		    }
		    if(lista.get(i).substring(lastIndexOf).contains(extensionType)) {
		    	pomocna.add(lista.get(i).substring(lastIndexOf));
		    }
		}
		
		

		return pomocna;
		
	}

	@Override
	public void saveFileMetedata(File file, HashMap<String, String> meta, String pathFile) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveFileMetedata(File file, String pathFile) {
		saveFile(file, pathFile);
		
	}
	
	
	
	/*
	public static void main(String[] args) {
		
		File file = new File("config-test.json");
		File f1 = new File("F:\\Test\\te\\mode.txt");
		String s = "F:\\Test\\";
		imp_Local.initialization(file);
		imp_Local.saveFile(f1, s);
	}
	
	*/
	
}
