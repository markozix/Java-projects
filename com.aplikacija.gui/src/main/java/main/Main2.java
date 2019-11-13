package main;



import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;

import ReadData.ReadFiles;
import WriteData.WriteFiles;
import imp_main.Imp_Local;
import init.Initialization;

public class Main2 extends Imp_Local implements ReadFiles, WriteFiles, Initialization {

	ArrayList<String> lista = new ArrayList<String>();
	private static ArrayList<Object> listaGUI = new ArrayList<Object>();
	
	public ArrayList<String> getFilesNames(String directoryPath) {
		File file = new File(directoryPath);
		File listaOdRoota[] = file.listFiles();
	
		for(File f : listaOdRoota) {
			if(f.isDirectory()) {
				lista.add(f.getName());
			}
		}

		return lista;
	}
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		final Main2 m = new Main2();
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader("config-test.json"));
			
		
		Settings settings = gson.fromJson(br, Settings.class);
		
		//vraca sve velikim slovima
		String mode = settings.getStorage();
		
		
		if(mode.equals("LOCAL")) {
			//LOCAL DIO
		final String root = settings.getRootPath() + settings.getRootName();
		System.out.println(root);
		ArrayList<String> imenaFajlova = m.getFilesNames(root + "/" + "UUP2018-januar");
		System.out.println(imenaFajlova);
		String[] lista = new String[2];
		
		for(int i=0;i<imenaFajlova.size();i++) {
			lista[i] = imenaFajlova.get(i);
		}
		System.out.println(lista);

		
		JFrame jFrame = new JFrame();
		jFrame.setTitle("");
		jFrame.setLocationRelativeTo(null);
		jFrame.setSize(new Dimension(900, 700));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jPanelMain = new JPanel();
		BoxLayout boxMain = new BoxLayout(jPanelMain, BoxLayout.Y_AXIS);
		jPanelMain.setLayout(boxMain);
		
		
		
		
		JPanel jPanel1 = new JPanel();
		BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
		jPanel1.setLayout(box1);
		
		JLabel jLabelIme = new JLabel("Ime: ");
		final JTextField jTextFieldIme = new JTextField();
		jTextFieldIme.setMinimumSize(new Dimension(120, 20));
		jTextFieldIme.setMaximumSize(new Dimension(120, 20));
		
		
		jPanelMain.add(Box.createVerticalStrut(5));
		
		
		jPanel1.add(jLabelIme);
		jPanel1.add(jTextFieldIme);
		
		jPanelMain.add(jPanel1);
		
		
		jPanelMain.add(Box.createVerticalStrut(5));
		
		
		JPanel jPanel2 = new JPanel();
		BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
		jPanel2.setLayout(box2);
		
		
		JLabel jLabelPrezime = new JLabel("Prezime: ");
		final JTextField jTextFieldPrezime = new JTextField();
		jTextFieldPrezime.setMinimumSize(new Dimension(120, 20));
		jTextFieldPrezime.setMaximumSize(new Dimension(120, 20));
		
		
		jPanel2.add(jLabelPrezime);
		jPanel2.add(jTextFieldPrezime);
		
		jPanelMain.add(jPanel2);
		
		
		jPanelMain.add(Box.createVerticalStrut(5));
		
		
		
		JPanel jPanel3 = new JPanel();
		BoxLayout box3 = new BoxLayout(jPanel3, BoxLayout.X_AXIS);
		jPanel3.setLayout(box3);
		
		JLabel jLabelIndex = new JLabel("Index: ");
		final JTextField jTextFieldIndex = new JTextField();
		jTextFieldIndex.setMinimumSize(new Dimension(120, 20));
		jTextFieldIndex.setMaximumSize(new Dimension(120, 20));
		
		jPanel3.add(jLabelIndex);
		jPanel3.add(jTextFieldIndex);
		
		
		jPanelMain.add(jPanel3);			
		
		
		jPanelMain.add(Box.createVerticalStrut(5));

		
		
		JPanel jPanel4 = new JPanel();
		BoxLayout box4 = new BoxLayout(jPanel4, BoxLayout.X_AXIS);
		jPanel4.setLayout(box4);
		
		JLabel jLabelGrupa = new JLabel("Grupa: ");
		final JComboBox<String> jComboBox = new JComboBox<String>(lista);
		
		jComboBox.setMinimumSize(new Dimension(120, 20));
		jComboBox.setMaximumSize(new Dimension(120, 20));
		
		jPanel4.add(jLabelGrupa);
		jPanel4.add(jComboBox);
		
		
		
		jPanelMain.add(jPanel4);
		
		
		
		
		jPanelMain.add(Box.createVerticalStrut(5));
		
		
		JPanel jPanel5 = new JPanel();
		BoxLayout box5 = new BoxLayout(jPanel5, BoxLayout.X_AXIS);
		jPanel5.setLayout(box5);
		
		
		JButton btnDownload = new JButton("Download");
		
		
		
		btnDownload.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fajl = jComboBox.getSelectedItem().toString();
				System.out.println(fajl);
				
				File file = new File(root + "/"+ "UUP2018-januar"+ "/" + fajl + "/");
				System.out.println(root + "/"+ "UUP2018-januar"+ "/" + fajl + "/" + fajl + ".txt");
				
			
				m.downloadFiles(file, "F:/");
				
				
				try {
					FileWriter fw = new FileWriter("F:/meta.txt");
					PrintWriter pw = new PrintWriter(fw);
					pw.println(jTextFieldIme.getText());
					pw.println(jTextFieldPrezime.getText());
					pw.print(jTextFieldIndex.getText());
					pw.print(".zip");
					pw.close();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		JLabel jLabelDownloadPutanja = new JLabel("Path:");
		JTextField jTextFieldPutanja = new JTextField();
		jTextFieldPutanja.setMinimumSize(new Dimension(120, 20));
		jTextFieldPutanja.setMaximumSize(new Dimension(120, 20));
		jTextFieldPutanja.setEditable(true);
		
		
		jPanel5.add(btnDownload);
		jPanel5.add(Box.createRigidArea(new Dimension(10, 10)));
		jPanel5.add(jLabelDownloadPutanja);
		jPanel5.add(Box.createRigidArea(new Dimension(10, 10)));
		jPanel5.add(jTextFieldPutanja);
		
		
		jPanelMain.add(jPanel5);
		
		
		jPanelMain.add(Box.createVerticalStrut(5));
		jPanelMain.add(Box.createRigidArea(new Dimension(10, 0)));
		
		
		JPanel jPanel6 = new JPanel();
		BoxLayout box6 = new BoxLayout(jPanel6, BoxLayout.X_AXIS);
		jPanel6.setLayout(box6);
		
		
		//JTextField field = new JTextField();
		JButton btnUpload = new JButton("Upload");
		
		btnUpload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String putanja = jTextFieldPutanja.getText();
				
				File f1 = new File(putanja);
				File f2 = new File("F:/meta.txt");
				ArrayList<File> lista = new ArrayList<>();
				
				lista.add(f1);
				lista.add(f2);
				
				String fileName = "";
				
				try {
					Scanner sc = new Scanner(f2);
					while(sc.hasNextLine()) {
						fileName = fileName + sc.nextLine();
					}
					
					sc.close();
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
				m.saveFileCollectionArchive("null", lista, fileName, f1);
				
				
			}
		});
		

		jPanel6.add(btnUpload);
		jPanel6.add(Box.createHorizontalBox());
		
		
		jPanelMain.add(jPanel6);
		
		
		
		
		jFrame.add(jPanelMain);
		
		
		
		
		
		
		//-------------------------------------------------------------------
		
		
		
		
		listaGUI.add(jTextFieldIme);
		listaGUI.add(jTextFieldIme);
		listaGUI.add(jTextFieldIndex);
		listaGUI.add(jComboBox);
		listaGUI.add(jTextFieldPutanja);
		
		
		
		
		
		
		
		
		jFrame.show();
		
		
		}else {
			
			
			JFrame jFrame = new JFrame();
			jFrame.setTitle("");
			jFrame.setLocationRelativeTo(null);
			jFrame.setSize(new Dimension(900, 700));
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel jPanelMain = new JPanel();
			BoxLayout boxMain = new BoxLayout(jPanelMain, BoxLayout.Y_AXIS);
			jPanelMain.setLayout(boxMain);
			
			
			
			
			JPanel jPanel1 = new JPanel();
			BoxLayout box1 = new BoxLayout(jPanel1, BoxLayout.X_AXIS);
			jPanel1.setLayout(box1);
			
			JLabel jLabelIme = new JLabel("Ime: ");
			JTextField jTextFieldIme = new JTextField();
			jTextFieldIme.setMinimumSize(new Dimension(120, 20));
			jTextFieldIme.setMaximumSize(new Dimension(120, 20));
			
			
			jPanelMain.add(Box.createVerticalStrut(5));
			
			
			jPanel1.add(jLabelIme);
			jPanel1.add(jTextFieldIme);
			
			jPanelMain.add(jPanel1);
			
			
			jPanelMain.add(Box.createVerticalStrut(5));
			
			
			JPanel jPanel2 = new JPanel();
			BoxLayout box2 = new BoxLayout(jPanel2, BoxLayout.X_AXIS);
			jPanel2.setLayout(box2);
			
			
			JLabel jLabelPrezime = new JLabel("Prezime: ");
			JTextField jTextFieldPrezime = new JTextField();
			jTextFieldPrezime.setMinimumSize(new Dimension(120, 20));
			jTextFieldPrezime.setMaximumSize(new Dimension(120, 20));
			
			
			jPanel2.add(jLabelPrezime);
			jPanel2.add(jTextFieldPrezime);
			
			jPanelMain.add(jPanel2);
			
			
			jPanelMain.add(Box.createVerticalStrut(5));
			
			
			
			JPanel jPanel3 = new JPanel();
			BoxLayout box3 = new BoxLayout(jPanel3, BoxLayout.X_AXIS);
			jPanel3.setLayout(box3);
			
			JLabel jLabelIndex = new JLabel("Index: ");
			JTextField jTextFieldIndex = new JTextField();
			jTextFieldIndex.setMinimumSize(new Dimension(120, 20));
			jTextFieldIndex.setMaximumSize(new Dimension(120, 20));
			
			jPanel3.add(jLabelIndex);
			jPanel3.add(jTextFieldIndex);
			
			
			jPanelMain.add(jPanel3);			
			
			
			jPanelMain.add(Box.createVerticalStrut(5));

			
			
			JPanel jPanel4 = new JPanel();
			BoxLayout box4 = new BoxLayout(jPanel4, BoxLayout.X_AXIS);
			jPanel4.setLayout(box4);
			
			JLabel jLabelGrupa = new JLabel("Grupa: ");
			JComboBox<String> jComboBox = new JComboBox<String>();
			jComboBox.setMinimumSize(new Dimension(120, 20));
			jComboBox.setMaximumSize(new Dimension(120, 20));
			
			jPanel4.add(jLabelGrupa);
			jPanel4.add(jComboBox);
			
			
			
			jPanelMain.add(jPanel4);
			
			
			
			
			jPanelMain.add(Box.createVerticalStrut(5));
			
			
			JPanel jPanel5 = new JPanel();
			BoxLayout box5 = new BoxLayout(jPanel5, BoxLayout.X_AXIS);
			jPanel5.setLayout(box5);
			
			
			JButton btnDownload = new JButton("Download");
			JLabel jLabelDownloadPutanja = new JLabel("Path:");
			JTextField jTextFieldPutanja = new JTextField();
			jTextFieldPutanja.setMinimumSize(new Dimension(120, 20));
			jTextFieldPutanja.setMaximumSize(new Dimension(120, 20));
			jTextFieldPutanja.setEditable(false);
			
			
			jPanel5.add(btnDownload);
			jPanel5.add(Box.createRigidArea(new Dimension(10, 10)));
			jPanel5.add(jLabelDownloadPutanja);
			jPanel5.add(Box.createRigidArea(new Dimension(10, 10)));
			jPanel5.add(jTextFieldPutanja);
			
			
			jPanelMain.add(jPanel5);
			
			
			jPanelMain.add(Box.createVerticalStrut(5));
			jPanelMain.add(Box.createRigidArea(new Dimension(10, 0)));
			
			
			JPanel jPanel6 = new JPanel();
			BoxLayout box6 = new BoxLayout(jPanel6, BoxLayout.X_AXIS);
			jPanel6.setLayout(box6);
			
			
			//JTextField field = new JTextField();
			JButton btnUpload = new JButton("Upload");
			

			jPanel6.add(btnUpload);
			jPanel6.add(Box.createHorizontalBox());
			
			
			jPanelMain.add(jPanel6);
			
			
			
			
			jFrame.add(jPanelMain);
			
			
			
			
			
			
			//-------------------------------------------------------------------
			
			
			
			
			listaGUI.add(jTextFieldIme);
			listaGUI.add(jTextFieldIme);
			listaGUI.add(jTextFieldIndex);
			listaGUI.add(jComboBox);
			listaGUI.add(jTextFieldPutanja);
			
			
			
			
			
			
			
			
			jFrame.show();
			
			
			
			
		}
	}







}
