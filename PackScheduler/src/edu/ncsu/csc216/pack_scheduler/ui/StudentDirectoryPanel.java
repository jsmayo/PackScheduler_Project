package edu.ncsu.csc216.pack_scheduler.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Creates a user interface for working with the StudentDirectory.
 * 
 * @author Sarah Heckman
 */
public class StudentDirectoryPanel extends JPanel implements ActionListener {
	
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;
	/** JFrame for the GUI */
	private static JFrame gui;
	/** WolfSchedulerGUI title */
	private static final String APP_TITLE = "WolfScheduler";
	/** Button for resetting the schedule */
	private JButton btnNewStudentList;
	/** Button for resetting the schedule */
	private JButton btnLoadStudentList;
	/** Button for displaying the final schedule */
	private JButton btnSaveStudentList;
	/** JTable for displaying the directory of Students */
	private JTable tableStudentDirectory;
	/** Scroll pane for table */
	private JScrollPane scrollStudentDirectory;
	/** TableModel for directory of Students */
	private StudentDirectoryTableModel studentDirectoryTableModel;
	/** JLabel for firstName */
	private JLabel lblFirstName;
	/** JLabel for lastName */
	private JLabel lblLastName;
	/** JLabel for id */
	private JLabel lblId;
	/** JLabel for email */
	private JLabel lblEmail;
	/** JLabel for password */
	private JLabel lblPassword;
	/** JLabel for repeat password */
	private JLabel lblRepeatPassword;
	/** JLabel for maxCredits */
	private JLabel lblMaxCredits;
	/** JTextField for firstName */
	private JTextField txtFirstName;
	/** JTextField for lastName */
	private JTextField txtLastName;
	/** JTextField for id */
	private JTextField txtId;
	/** JTextField for email */
	private JTextField txtEmail;
	/** JPasswordField for password */
	private JPasswordField txtPassword;
	/** JPasswordField for repeat password */
	private JPasswordField txtRepeatPassword;
	/** JTextField for maxCredits */
	private JTextField txtMaxCredits;
	/** Button for adding the selected course in the catalog to the schedule */
	private JButton btnAddStudent;
	/** Button for removing the selected Course from the schedule */
	private JButton btnRemoveStudent;
	/** Reference to StudentDirectory */
	private StudentDirectory studentDirectory;
	
	/**
	 * Constructs the StudentDirectoryGUI and sets up the GUI 
	 * components.
	 */
	public StudentDirectoryPanel() {
		super(new GridLayout(4, 1));
		
		studentDirectory = new StudentDirectory();
		
		//Set up Directory buttons
		btnNewStudentList = new JButton("New Student Directory");
		btnNewStudentList.addActionListener(this);
		btnLoadStudentList = new JButton("Load Student Directory");
		btnLoadStudentList.addActionListener(this);
		btnSaveStudentList = new JButton("Save Student Directory");
		btnSaveStudentList.addActionListener(this);
		
		JPanel pnlDirectoryButton = new JPanel();
		pnlDirectoryButton.setLayout(new GridLayout(1, 3));
		pnlDirectoryButton.add(btnNewStudentList);
		pnlDirectoryButton.add(btnLoadStudentList);
		pnlDirectoryButton.add(btnSaveStudentList);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");
		pnlDirectoryButton.setBorder(boarder);
		pnlDirectoryButton.setToolTipText("Directory Buttons");
		
		//Set up Directory table
		studentDirectoryTableModel = new StudentDirectoryTableModel();
		tableStudentDirectory = new JTable(studentDirectoryTableModel);
		tableStudentDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStudentDirectory.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableStudentDirectory.setFillsViewportHeight(true);
		
		scrollStudentDirectory = new JScrollPane(tableStudentDirectory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Student Directory");
		scrollStudentDirectory.setBorder(boarder);
		scrollStudentDirectory.setToolTipText("Student Directory");
		
		//Set up Student buttons
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(this);
		btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.addActionListener(this);
		
		JPanel pnlStudentButtons = new JPanel();
		pnlStudentButtons.setLayout(new GridLayout(1, 2));
		pnlStudentButtons.add(btnAddStudent);
		pnlStudentButtons.add(btnRemoveStudent);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Student Controls");
		pnlStudentButtons.setBorder(boarder);
		pnlStudentButtons.setToolTipText("StudentControls");
		
		//Set up Student form
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblId = new JLabel("ID");
		lblEmail = new JLabel("Email");
		lblPassword = new JLabel("Password");
		lblRepeatPassword = new JLabel("Repeat Password");
		lblMaxCredits = new JLabel("Max Credits");
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtId = new JTextField(20);
		txtEmail = new JTextField(20);
		txtPassword = new JPasswordField(20);
		txtRepeatPassword = new JPasswordField(20);
		txtMaxCredits = new JTextField(20);
		
		JPanel pnlStudentForm = new JPanel();
		pnlStudentForm.setLayout(new GridLayout(7, 2));
		pnlStudentForm.add(lblFirstName);
		pnlStudentForm.add(txtFirstName);
		pnlStudentForm.add(lblLastName);
		pnlStudentForm.add(txtLastName);
		pnlStudentForm.add(lblId);
		pnlStudentForm.add(txtId);
		pnlStudentForm.add(lblEmail);
		pnlStudentForm.add(txtEmail);
		pnlStudentForm.add(lblPassword);
		pnlStudentForm.add(txtPassword);
		pnlStudentForm.add(lblRepeatPassword);
		pnlStudentForm.add(txtRepeatPassword);
		pnlStudentForm.add(lblMaxCredits);
		pnlStudentForm.add(txtMaxCredits);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Student Information");
		pnlStudentForm.setBorder(boarder);
		pnlStudentForm.setToolTipText("Student Information");
		
		this.add(pnlDirectoryButton);
		this.add(scrollStudentDirectory);
		this.add(pnlStudentButtons);
		this.add(pnlStudentForm);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoadStudentList) {
			String fileName = getFileName(true);
			try {
				studentDirectory.loadStudentsFromFile(fileName);
				studentDirectoryTableModel.updateData();
				scrollStudentDirectory.revalidate();
				scrollStudentDirectory.repaint();
				studentDirectoryTableModel.fireTableDataChanged();
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(gui, iae.getMessage());
			}
		} else if (e.getSource() == btnSaveStudentList) {
			String fileName = getFileName(false);
			try {
				studentDirectory.saveStudentDirectory(fileName);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(gui, iae.getMessage());
			}
		} else if (e.getSource() == btnNewStudentList) {
			studentDirectory.newStudentDirectory();
			studentDirectoryTableModel.updateData();
			scrollStudentDirectory.revalidate();
			scrollStudentDirectory.repaint();
			studentDirectoryTableModel.fireTableDataChanged();
		} else if (e.getSource() == btnAddStudent) {
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String id = txtId.getText();
			String email = txtEmail.getText();
			char[] password = txtPassword.getPassword();
			char[] repeatPassword = txtRepeatPassword.getPassword();
			int maxCredits = 0;
			try {
				maxCredits = Integer.parseInt(txtMaxCredits.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(gui, "Max credits must be a positive number between 3 and 18.");
				return;
			}
			
			String pwString = "";
			for (int i = 0; i < password.length; i++) {
				pwString += password[i];
			}
			
			String repeatPWString = "";
			for (int i = 0; i < repeatPassword.length; i++) {
				repeatPWString += repeatPassword[i];
			}
			
			try {
				if (studentDirectory.addStudent(firstName, lastName, id, email, pwString, repeatPWString, maxCredits)) {
					txtFirstName.setText("");
					txtLastName.setText("");
					txtId.setText("");
					txtEmail.setText("");
					txtPassword.setText("");
					txtRepeatPassword.setText("");
					txtMaxCredits.setText("");
				} else {
					JOptionPane.showMessageDialog(gui, "Student already in system.");
				}
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(gui, iae.getMessage());
			}
			studentDirectoryTableModel.updateData();
		} else if (e.getSource() == btnRemoveStudent) {
			int row = tableStudentDirectory.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(gui, "No student selected.");
			} else {
				try {
					studentDirectory.removeStudent(tableStudentDirectory.getValueAt(row, 2).toString());
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(gui, "No student selected.");
				}
			}
			studentDirectoryTableModel.updateData();
		}
		
		gui.validate();
		gui.repaint();
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @param chooserType true if open, false if save
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;
		if (chooserType) {
			fc.setDialogTitle("Load Course Catalog");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Schedule");
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File catalogFile = fc.getSelectedFile();
		return catalogFile.getAbsolutePath();
	}
	
	/**
	 * Starts the Wolf Scheduler program.
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		gui = new JFrame();
		gui.setSize(900, 800);
		gui.setLocation(50, 50);
		gui.setTitle(APP_TITLE);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = gui.getContentPane();
		c.add(new StudentDirectoryPanel(), BorderLayout.CENTER);
		
		gui.setVisible(true);
	}
	
	/**
	 * {@link StudentDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Student}s to the user.
	 * @author Sarah Heckman
	 */
	private class StudentDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link StudentDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public StudentDirectoryTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Student} information from the {@link StudentDirectory}.
		 */
		public void updateData() {
			data = studentDirectory.getStudentDirectory();
		}
	}

}
