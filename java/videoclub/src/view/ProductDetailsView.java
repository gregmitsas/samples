package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class ProductDetailsView extends JDialog
{

	public ProductDetailsView(Frame parent, boolean modal, ArrayList<Object> product)
	{
		super(parent, modal);
		this.product = product;
		initComponents();
		location = parent.getLocation();
		this.setLocation(location.x + 140, location.y + 140);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
	}

	public JLabel getTitleLabel()
	{
		return titleLabel;
	}

	private void initComponents()
	{

		titleLabel = new JLabel();
		yearLabel = new JLabel();
		genreHeader = new JLabel();
		genreLabel = new JLabel();
		ratingHeader = new JLabel();
		ratingLabel = new JLabel();
		descriptionHeader = new JLabel();
		descriptionScrollPane = new JScrollPane();
		descriptionTextArea = new JTextArea();
		statusHeader = new JLabel();
		statusLabel = new JLabel();
		rentButton = new JButton();
		bindButton = new JButton();
		editButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Movie Details");

		titleLabel.setFont(new Font("Ubuntu", 3, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		String title = product.get(0).toString() + " ("
				+ product.get(4).toString() + ")";
		titleLabel.setText(title);

		yearLabel.setFont(new Font("Ubuntu", 2, 18));
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setText(product.get(3).toString());

		genreHeader.setFont(new Font("Ubuntu", 1, 14));
		genreHeader.setText("Genre:");

		genreLabel.setText(product.get(1).toString());

		ratingHeader.setFont(new Font("Ubuntu", 1, 14));
		ratingHeader.setText("Rating:");

		ratingLabel.setText(product.get(2).toString());

		descriptionTextArea.setEditable(false);
		descriptionTextArea.setBackground(new Color(231, 229, 229));
		descriptionTextArea.setColumns(20);
		descriptionTextArea.setRows(5);
		descriptionScrollPane.setViewportView(descriptionTextArea);
		if (product.get(5).toString().isEmpty())
			descriptionTextArea
					.setText("There is no description for this movie");
		else
			descriptionTextArea.setText(product.get(5).toString());

		statusHeader.setFont(new Font("Ubuntu", 1, 14));
		statusHeader.setText("Status:");

		setStatusLabel();

		descriptionHeader.setFont(new Font("Ubuntu", 1, 14));
		descriptionHeader.setText("Overview");

		editButton.setText("Edit Movie");
		editButton.setEnabled(false);
		editButton.setVisible(false);

		rentButton.setText("Rent Movie");
		rentButton.setEnabled(false);
		rentButton.setVisible(false);

		bindButton.setText("Bind Movie");
		bindButton.setEnabled(false);
		bindButton.setVisible(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
												.addComponent(
														descriptionScrollPane,
														GroupLayout.DEFAULT_SIZE,
														472, Short.MAX_VALUE)
												.addComponent(
														titleLabel,
														GroupLayout.Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														yearLabel,
														GroupLayout.Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		statusHeader)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		statusLabel,
																		GroupLayout.PREFERRED_SIZE,
																		150,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		rentButton))
												.addGroup(
														GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										genreHeader)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										genreLabel))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										ratingHeader)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										ratingLabel))
																				.addComponent(
																						descriptionHeader))
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		editButton)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		bindButton)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(titleLabel,
										GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(yearLabel,
										GroupLayout.PREFERRED_SIZE, 20,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(genreHeader)
												.addComponent(
														genreLabel,
														GroupLayout.PREFERRED_SIZE,
														17,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(ratingHeader)
												.addComponent(
														ratingLabel,
														GroupLayout.PREFERRED_SIZE,
														17,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(descriptionHeader)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(descriptionScrollPane,
										GroupLayout.PREFERRED_SIZE, 136,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(rentButton)
												.addComponent(statusHeader)
												.addComponent(statusLabel))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(bindButton)
												.addComponent(editButton))
								.addGap(50, 50, 50)));

		pack();
	}

	public void addRentListener(ActionListener rentMovie)
	{
		rentButton.addActionListener(rentMovie);
	}

	public void addBindListener(ActionListener bindMovie)
	{
		bindButton.addActionListener(bindMovie);
	}

	public void addEditMovieListener(ActionListener search)
	{
		editButton.addActionListener(search);
	}

	private void setStatusLabel()
	{
		if ((Boolean) product.get(6))
		{
			product.add("Available");
			statusLabel.setForeground(Color.green.darker().darker());
		}
		else
		{
			product.add("Rented!");
			statusLabel.setForeground(Color.red.darker());
		}
		product.remove(6);
		statusLabel.setFont(new Font("Ubuntu", 3, 14));
		statusLabel.setText(product.get(6).toString());
	}

	public void enableRentButton()
	{
		rentButton.setEnabled(true);
		rentButton.setVisible(true);
	}

	public void enableBindButton()
	{
		bindButton.setEnabled(true);
		bindButton.setVisible(true);
	}

	public void enableEditButton()
	{
		editButton.setEnabled(true);
		editButton.setVisible(true);
	}

	private JButton bindButton;
	private JScrollPane descriptionScrollPane;
	private JLabel descriptionHeader;
	private JTextArea descriptionTextArea;
	private JLabel genreHeader;
	private JLabel genreLabel;
	private JLabel ratingHeader;
	private JLabel ratingLabel;
	private JButton rentButton;
	private JLabel titleLabel;
	private JLabel yearLabel;
	private ArrayList<Object> product;
	private JButton editButton;
	private JLabel statusHeader;
	private JLabel statusLabel;
	
	private Point location;
}
