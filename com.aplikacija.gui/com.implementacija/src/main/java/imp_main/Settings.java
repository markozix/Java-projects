package imp_main;


import java.io.Serializable;



class Settings implements Serializable {

	private static final long serialVersionUID = 6430277723380416662L;
	private String rootName;
	private String rootPath;
	private RemoteUser loggedUser;

	private String token;
	private String bannedExtension; 
	private String storage;	
	private String fileName;

	public Settings() {

	}

	public Settings(String rootName, String rootPath, RemoteUser loggedUser, String token, String bannedExtension) {
		this.rootName = rootName;
		this.rootPath = rootPath;
		this.loggedUser = loggedUser;
		this.token = token;
		this.bannedExtension = bannedExtension; // String: ".exe,.txt"

	}
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public RemoteUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(RemoteUser loggedUser) {
		this.loggedUser = loggedUser;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBannedExtension() {
		return bannedExtension;
	}

	public void setBannedExtension(String bannedExtension) {
		this.bannedExtension = bannedExtension;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void print() {
		
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("Root: " + this.rootName);
		System.out.println("Root Path: " + this.rootPath);
		System.out.println("Logged User: " + this.loggedUser.getIndex());
		System.out.println("Token: " + this.token);
		System.out.println("Restricted Files: " + this.bannedExtension);
		System.out.println("-----------------------------------------------------------------------------------------------");
	
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return rootName + " " + rootPath;
	}

}