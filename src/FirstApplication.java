import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class FirstApplication {

	private JFrame frmPdf;
	private JTextField fileTextField;
	private JTextField directoryTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstApplication window = new FirstApplication();
					window.frmPdf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPdf = new JFrame();
		frmPdf.setResizable(false);
		frmPdf.setTitle("PDF разделитель");
		frmPdf.setBounds(100, 100, 486, 221);
		frmPdf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPdf.getContentPane().setLayout(null);
		
		fileTextField = new JTextField();
		fileTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fileTextField.setBounds(10, 36, 357, 27);
		frmPdf.getContentPane().add(fileTextField);
		fileTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Выберите файлы:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 564, 27);
		frmPdf.getContentPane().add(lblNewLabel);
		
		JButton firstReviewButton = new JButton("Обзор");
		firstReviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter(".pdf", "pdf"));
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setMultiSelectionEnabled(true);
				chooser.showOpenDialog(null);
				File[] f = chooser.getSelectedFiles();
				StringBuilder filename = new StringBuilder();
				for (File file : f)
				{
					filename.append(file.getAbsolutePath());
					filename.append("; ");
					
				}
				fileTextField.setText(filename.toString().trim());
				
			}
		});
		firstReviewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstReviewButton.setBounds(377, 36, 89, 27);
		frmPdf.getContentPane().add(firstReviewButton);
		
		directoryTextField = new JTextField();
		directoryTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		directoryTextField.setBounds(10, 90, 357, 27);
		frmPdf.getContentPane().add(directoryTextField);
		directoryTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Выберите папку для сохранения:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 74, 564, 14);
		frmPdf.getContentPane().add(lblNewLabel_1);
		
		JButton secondReviewButton = new JButton("Обзор");
		secondReviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				directoryTextField.setText(filename);
			}
		});
		secondReviewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		secondReviewButton.setBounds(377, 90, 89, 27);
		frmPdf.getContentPane().add(secondReviewButton);
		
		JButton transformButton = new JButton("Преобразовать");
		transformButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PDFSplitter splitPDF = new PDFSplitter();
				splitPDF.Split(fileTextField.getText(), directoryTextField.getText());
			}
		});
		transformButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		transformButton.setBounds(172, 144, 140, 27);
		frmPdf.getContentPane().add(transformButton);
	}
}
