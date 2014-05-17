package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import main.Main;

@SuppressWarnings("serial")
public class ProductView extends JFrame
{
	public ProductView()
	{
		initComponents();
		this.setSize(800, 650);
		this.setResizable(false);
	}

	public JTextField getSearchField()
	{
		return searchField;
	}

	public JComboBox getViewByOptionBox()
	{
		return viewByOptionBox;
	}

	public JComboBox getViewByBox()
	{
		return viewByBox;
	}

	public JLabel getNoticeLabel()
	{
		return this.noticeLabel;
	}

	public JLabel getEditMovieLabel()
	{
		return this.showDetailsLabel;
	}

	public JLabel getLoginLabel()
	{
		return this.loginLabel;
	}

	public JButton getLogButton()
	{
		return this.logButton;
	}

	public JButton getAddMovieButton()
	{
		return this.addMovieButton;
	}

	public JButton getManageCustomerButton()
	{
		return this.manageCustomerButton;
	}

	public JButton getOrdersButton()
	{
		return this.ordersButton;
	}

	public JRadioButton getDvdRadio()
	{
		return this.dvdRadio;
	}

	public JRadioButton getBluerayRadio()
	{
		return this.bluerayRadio;
	}

	private void initComponents()
	{

		mTableLabel = new JLabel();
		viewByLabel = new JLabel();
		tableScrollPane = new JScrollPane();
		viewByBox = new JComboBox();
		viewByOptionBox = new JComboBox();
		addMovieButton = new JButton();
		searchLabel = new JLabel();
		searchField = new JTextField();
		searchButton = new JButton();
		noticeLabel = new JLabel();
		showDetailsLabel = new JLabel();
		viewUserDetailsLabel = new JLabel();
		loginLabel = new JLabel();
		logButton = new JButton();
		manageCustomerButton = new JButton();
		ordersButton = new JButton();
		buttonsGroup = new javax.swing.ButtonGroup();
		dvdRadio = new JRadioButton();
		bluerayRadio = new JRadioButton();

		moviesTable = new JTable()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Video Club");

		mTableLabel.setFont(new Font("Ubuntu", 3, 24)); // NOI18N
		mTableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mTableLabel.setText("Movies List");

		viewByLabel.setText("View Movies By:");

		moviesTable.setModel(model);
		moviesTable.setColumnSelectionAllowed(true);
		moviesTable.getTableHeader().setReorderingAllowed(false);
		tableScrollPane.setViewportView(moviesTable);
		moviesTable.getColumnModel().getSelectionModel()
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		moviesTable.getColumnModel().getColumn(0).setResizable(true);
		moviesTable.getColumnModel().getColumn(1).setResizable(true);
		moviesTable.getColumnModel().getColumn(2).setResizable(true);
		moviesTable.getColumnModel().getColumn(3).setResizable(true);
		moviesTable.getColumnModel().getColumn(4).setResizable(true);
		moviesTable.getColumnModel().setColumnSelectionAllowed(false);
		moviesTable.setSelectionMode(0);

		viewByBox.setModel(new DefaultComboBoxModel(new String[] { "All",
				"Genre", "Rating", "Year", "Type" }));
		viewByOptionBox.setVisible(false);

		logButton.setFont(new Font("Ubuntu", 3, 18));
		logButton.setText("Login");

		loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		addMovieButton.setText("Add New Movie");
		addMovieButton.setEnabled(false);
		addMovieButton.setVisible(false);

		manageCustomerButton.setText("Manage Users");
		manageCustomerButton.setEnabled(false);
		manageCustomerButton.setVisible(false);

		ordersButton.setText("View Orders");
		ordersButton.setEnabled(false);
		ordersButton.setVisible(false);

		searchLabel.setText("Search By Title:");

		searchButton.setText("Search");

		noticeLabel.setFont(new Font("Ubuntu", 1, 14));
		noticeLabel.setVisible(false);

		showDetailsLabel.setFont(new Font("Ubuntu", 1, 14));
		showDetailsLabel.setText("Double-Click on a movie to view its details");
		showDetailsLabel.setVisible(true);

		viewUserDetailsLabel.setFont(new Font("Ubuntu", 1, 14));
		viewUserDetailsLabel
				.setText("Double-Click on your name to view your account info");
		viewUserDetailsLabel.setVisible(false);

		buttonsGroup.add(dvdRadio);
		dvdRadio.setText("DVD");
		dvdRadio.setSelected(true);

		buttonsGroup.add(bluerayRadio);
		bluerayRadio.setText("Blu-Ray");

		productType = "DVD";

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(tableScrollPane)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(12, 12,
																		12)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						viewByLabel)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										viewByBox,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										viewByOptionBox,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										searchLabel)
																								.addGap(12,
																										12,
																										12)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														searchField,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														263,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		dvdRadio)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addComponent(
																																		bluerayRadio)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		Short.MAX_VALUE)
																																.addComponent(
																																		searchButton,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		90,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))))
																				.addComponent(
																						noticeLabel))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		12,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						manageCustomerButton,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						addMovieButton,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						140,
																						Short.MAX_VALUE)
																				.addComponent(
																						ordersButton,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addComponent(
														mTableLabel,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		viewUserDetailsLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		loginLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		logButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		99,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		showDetailsLabel)
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(9, 9, 9)
								.addComponent(mTableLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(loginLabel)
												.addComponent(logButton)
												.addComponent(
														viewUserDetailsLabel))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(showDetailsLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tableScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										359,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		addMovieButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		manageCustomerButton))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(6, 6, 6)
																.addComponent(
																		viewByLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						viewByBox,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						viewByOptionBox,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						searchLabel)
																				.addComponent(
																						searchField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						dvdRadio)
																				.addComponent(
																						bluerayRadio)
																				.addComponent(
																						searchButton))))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														noticeLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														17,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(ordersButton))
								.addContainerGap(56, Short.MAX_VALUE)));
	}

	public void addViewByBoxItemStateChanged(ItemListener viewBy)
	{
		viewByBox.addItemListener(viewBy);
	}

	public void addViewByOptionBoxItemStateChanged(ItemListener viewByOption)
	{
		viewByOptionBox.addItemListener(viewByOption);
	}

	public void addButtonsGroupItemStateChanged(ItemListener buttonsGroupOption)
	{
		dvdRadio.addItemListener(buttonsGroupOption);
		bluerayRadio.addItemListener(buttonsGroupOption);
	}

	public void addLogListener(ActionListener log)
	{
		logButton.addActionListener(log);
	}

	public void addNameLabelListener(MouseListener name)
	{
		loginLabel.addMouseListener(name);
	}

	public void addSubmitSearchListener(ActionListener search)
	{
		searchButton.addActionListener(search);
	}

	public void addNewMovieListener(ActionListener addMovie)
	{
		addMovieButton.addActionListener(addMovie);
	}

	public void addManageCustomerListener(ActionListener manageCustomer)
	{
		manageCustomerButton.addActionListener(manageCustomer);
	}

	public void addViewOrdersListener(ActionListener viewOrders)
	{
		ordersButton.addActionListener(viewOrders);
	}

	public void addSearchFieldFocusGained(FocusListener searchFocus)
	{
		searchField.addFocusListener(searchFocus);
	}

	public void addTableListener(MouseListener tableDoubleClick)
	{
		moviesTable.addMouseListener(tableDoubleClick);
	}

	public void userLoggedIn()
	{
		logButton.setText("Logout");
		loginLabel.setText("Welcome " + Main.current_user.getName());
		loginLabel.setVisible(true);
	}

	public void userLoggedOut()
	{
		showDetailsLabel.setVisible(false);
		loginLabel.setVisible(false);
		addMovieButton.setEnabled(false);
		addMovieButton.setVisible(false);
		manageCustomerButton.setEnabled(false);
		manageCustomerButton.setVisible(false);
		ordersButton.setEnabled(false);
		ordersButton.setVisible(false);
		logButton.setText("Login");
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String type)
	{
		productType = type;
	}

	public void showAddMovieButton()
	{
		addMovieButton.setEnabled(true);
		addMovieButton.setVisible(true);
	}

	public void showUserDetailsLabel()
	{
		viewUserDetailsLabel.setVisible(true);
	}

	public void showManageCustomerButton()
	{
		manageCustomerButton.setEnabled(true);
		manageCustomerButton.setVisible(true);
	}

	public void showViewOrdersButton()
	{
		ordersButton.setEnabled(true);
		ordersButton.setVisible(true);
	}

	public void addOne(ArrayList<Object> oneProduct)
	{
		row[0] = oneProduct.get(0);
		row[1] = oneProduct.get(1);
		row[2] = oneProduct.get(2);
		row[3] = oneProduct.get(3);
		row[4] = oneProduct.get(4);
		model.addRow(row);
	}

	public void showAll(ArrayList<Object> allProducts)
	{
		int tableSize = model.getRowCount();

		for (int i = 0; i < tableSize; i++)
		{
			model.removeRow(tableSize - i - 1);
		}

		for (int i = 0; i < allProducts.size(); i += 5)
		{
			row[0] = allProducts.get(i);
			row[1] = allProducts.get(i + 1);
			row[2] = allProducts.get(i + 2);
			row[3] = allProducts.get(i + 3);
			row[4] = allProducts.get(i + 4);
			model.addRow(row);
		}
	}
	
	public void populateGenres()
	{
		String[] genres = { "Action", "Adventure", "Animation", "Biography",
				"Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy",
				"Film-Noir", "Game-Show", "History", "Horror", "Music",
				"Musical", "Mystery", "News", "Reality-TV", "Romance",
				"Sci-Fi", "Sport", "Talk-Show", "Thriller", "War", "Western" };
		viewByOptionBox.setModel(new DefaultComboBoxModel(genres));
		viewByOptionBox.setVisible(true);
	}

	public void populateRatings()
	{
		String[] ratings = { "UR (Unrated)", "G", "PG", "PG-13", "R", "NC-17" };
		viewByOptionBox.setModel(new DefaultComboBoxModel(ratings));
		viewByOptionBox.setVisible(true);
	}

	public void populateYears()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String[] years = new String[year - 1949];

		for (int i = 0; i < years.length; i++)
		{
			years[i] = Integer.toString(year - i);
		}
		viewByOptionBox.setModel(new DefaultComboBoxModel(years));
		viewByOptionBox.setVisible(true);
	}

	public void populateTypes()
	{
		String[] types = { "DVD", "Blu-Ray" };
		viewByOptionBox.setModel(new DefaultComboBoxModel(types));
		viewByOptionBox.setVisible(true);
	}

	public void updateRow(int row, ArrayList<Object> product)
	{
		moviesTable.setValueAt(product.get(0), row, 0);
		moviesTable.setValueAt(product.get(1), row, 1);
		moviesTable.setValueAt(product.get(2), row, 2);
		moviesTable.setValueAt(product.get(3), row, 3);
		moviesTable.setValueAt(product.get(4), row, 4);
	}

	private JButton addMovieButton;
	private JButton searchButton;
	private JButton logButton;
	private JButton manageCustomerButton;
	private JButton ordersButton;
	private JLabel mTableLabel;
	private JTable moviesTable;
	private JTextField searchField;
	private JLabel searchLabel;
	private JLabel showDetailsLabel;
	private JLabel viewUserDetailsLabel;
	private JLabel noticeLabel;
	private JScrollPane tableScrollPane;
	private JComboBox viewByBox;
	private JLabel viewByLabel;
	private JComboBox viewByOptionBox;
	private JLabel loginLabel;
	private ButtonGroup buttonsGroup;
	private JRadioButton dvdRadio;
	private JRadioButton bluerayRadio;

	private String[] columns = { "Title", "Genre", "Rating", "Release Year", "Type" };
	private Object[] row = new Object[5];
	private DefaultTableModel model = new DefaultTableModel(columns, 0);

	private String productType;
}