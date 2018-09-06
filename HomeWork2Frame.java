package less02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HomeWork2Frame extends JFrame {
	MyFiles file = new MyFiles();
	JLayeredPane panel = null;
	JPanel panel1 = null;
	JPanel panel2 = null;
	JButton back = new JButton(new ImageIcon("Images/back.png"));
	JButton save = new JButton(new ImageIcon("Images/save.png"));
	JButton copy = new JButton(new ImageIcon("Images/copy.png"));
	JButton paste = new JButton(new ImageIcon("Images/paste.png"));
	JButton cut = new JButton(new ImageIcon("Images/paste.png"));
	JTextArea textFile = new JTextArea();
	Image img = null;
	JLabel path = new JLabel(file.getPath());
	String copiedFile = null;
	String selectedFile=null;

	HomeWork2Frame() {
		panel = getPanel();
		panel1 = getPanel1();
		panel2 = getPanel2();
		panel.add(panel1, new Integer(0), 0);
		panel.add(panel2, new Integer(1), 0);
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		setSize(720, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initListeners();
		JScrollPane scrollPane = new JScrollPane(panel1);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(50, 50, 300, 600);
		panel.add(scrollPane);
		JScrollPane scrollPane2 = new JScrollPane(panel2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(350, 50, 300, 600);
		panel.add(scrollPane2);
	}

	private JPanel getPanel2() {
		JPanel panel = new JPanel();
		panel.setBounds(350, 50, 300, 600);
		return panel;
	}

	private JLayeredPane getPanel() {
		JLayeredPane panel = new JLayeredPane();
		path.setBounds(50, -20, 300, 100);
		panel.add(path);
		back.setBounds(10, 250, 20, 20);
		panel.add(back);
		if (file.isRoot(file.getPath())) {
			back.setEnabled(false);
		}
		save.setBounds(670, 250, 30, 30);
		panel.add(save);
		copy.setBounds(670, 300, 30, 30);
		panel.add(copy);
		paste.setBounds(670, 350, 30, 30);
		panel.add(paste);
		cut.setBounds(670, 400, 30, 30);
		panel.add(cut);
		return panel;
	}

	private JPanel getPanel1() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBounds(50, 50, 300, 600);
		panel.setBackground(Color.WHITE);
		drawLabels(panel);
		return panel;
	}

	private void drawLabels(JPanel panel) {
		MyMouseHandler myMouseHandler = new MyMouseHandler();
		for (int i = 0; i < file.sort().size(); i++) {
			File newfile = file.sort().get(i);
			JLabel label = new JLabel(newfile.getName());
			label.addMouseListener(myMouseHandler);
			panel.add(label);
		}
	}

	private class MyMouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent evt) {
			JLabel source = (JLabel) evt.getSource();
			if (file.isPathToDirectory(file.getPath() + "\\" + source.getText())) {
				
				file.setPath(file.getPath() + "\\" + source.getText());
				panel1.removeAll();
				panel1.updateUI();
				drawLabels(panel1);
				path.setText(file.getPath());
				back.setEnabled(true);
				
			} else if (file.isTextFile(file.getPath() + "\\" + source.getText())) {
				selectedFile = source.getText();
				panel2.removeAll();
				panel2.add(textFile);
				textFile.setText("");
				FileReader reader;
				try {
					reader = new FileReader(file.getPath() + "\\" + source.getText());
					textFile.read(reader, file.getPath() + "\\" + source.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (file.isImageFile(file.getPath() + "\\" + source.getText())) {
				try {
					File input = new File(file.getPath() + "\\" + source.getText());
					img = ImageIO.read(input);
					panel2.removeAll();
					panel2.add(new JLabel(new ImageIcon(img), JLabel.CENTER));
					panel2.revalidate();
					panel2.repaint();
					panel2.updateUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent evt) {
			JLabel source = (JLabel) evt.getSource();
			source.setOpaque(true);
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent evt) {
			JLabel source = (JLabel) evt.getSource();
			source.setOpaque(false);
			repaint();
		}
	}

	private void initListeners() {
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				file.setPath(file.getPath().substring(0, file.getPath().indexOf(file.currentDirectory())));
				path.setText(file.getPath());
				panel1.removeAll();
				panel1.updateUI();
				drawLabels(panel1);
				path.setText(file.getPath());
				if (file.isRoot(file.getPath())) {
					back.setEnabled(false);
					
				}
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtn();
			}
		});
		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				copyBtn(file.getPath()+"\\"+selectedFile);
				copiedFile = selectedFile;
				System.out.println(file.getPath()+"\\"+selectedFile);
			}
			
		});
		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pasteBtn(file.getPath()+"\\"+copiedFile);
				System.out.println(file.getPath()+"\\"+copiedFile);
				panel1.removeAll();
				panel1.updateUI();
				drawLabels(panel1);
				path.setText(file.getPath());
			}
			
		});
		cut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cutBtn(file.getPath()+"\\"+selectedFile);
				copiedFile = selectedFile;
				System.out.println(file.getPath()+"\\"+selectedFile);
			}});
	}

	private Path copyBtn(String path) {
		Path getpath = Paths.get(path);
		Path temp = Paths.get("C:\\Users\\HalynaZ\\for copies\\newcopy.txt");
		try {
			Files.copy(getpath, temp, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
	private void saveBtn() {
		File file = null;
		FileWriter out = null;

		try {
			file = new File(this.file.getPath()+this.file.currentDirectory());
			out = new FileWriter(file);
			out.write(textFile.getText());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void pasteBtn(String path) {
		Path getpath = Paths.get(path);
		Path temp = Paths.get("C:\\Users\\HalynaZ\\for copies\\newcopy.txt");
		try {
			Files.copy(temp, getpath,StandardCopyOption.REPLACE_EXISTING);
			Files.delete(temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private Path cutBtn(String path) {
		Path getpath = Paths.get(path);
		Path temp = Paths.get("C:\\Users\\HalynaZ\\for copies\\newcopy.txt");
		try  {
			Files.move(Paths.get(path), temp, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		return temp;
	}
}
