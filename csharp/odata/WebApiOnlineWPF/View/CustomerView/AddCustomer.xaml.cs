using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Net.Http;
using WebApiModel.Model;

namespace WebApiOnlineWPF.View.CustomerView
{
    /// <summary>
    /// Interaction logic for AddCustomer.xaml
    /// </summary>
    public partial class AddCustomer : Window
    {

        private DataGrid dataGrid;
        private HttpClient client;
        private MainWindow mainWindow;

        public AddCustomer(DataGrid dataGrid, HttpClient client, MainWindow mainWindow)
        {
            InitializeComponent();
            this.dataGrid = dataGrid;
            this.client = client;
            this.mainWindow = mainWindow;
        }

        private void buttonClick(Object sender, RoutedEventArgs e)
        {

            switch (((Button)sender).Name)
            {
                case "buttonAddAddress":
                    new AddAddress(comboBoxAddresses).Show();
                    break;
                default:
                    createCustomer();
                    break;
            }
        }

        private void createCustomer()
        {
            if (dataGrid != null && client != null)
            {

                if (textBoxFirstName.Text.Equals("") || textBoxLastName.Text.Equals("") || textBoxEmail.Text.Equals("") || textBoxPhone.Text.Equals("") || textBoxCreditCard.Text.Equals(""))
                {
                    MessageBox.Show("All fields must be filled.");
                    return;
                }

                if (comboBoxAddresses.Items.Count < 1)
                {
                    MessageBox.Show("You must insert at least one address.");
                    return;
                }

                Customer customer = new Customer();
                customer.Id = dataGrid.Items.Count + 1;
                customer.FirstName = textBoxFirstName.Text;
                customer.LastName = textBoxLastName.Text;
                customer.Email = textBoxEmail.Text;
                customer.Phone = textBoxPhone.Text;
                customer.CreditCard = textBoxCreditCard.Text;

                ICollection<Address> addresses = new List<Address>();
                foreach (Address item in comboBoxAddresses.Items)
                {
                    addresses.Add(item);
                }

                customer.Addresses = addresses;

                HttpResponseMessage response = client.PostAsJsonAsync("greg_api/customers", customer).Result;
                if (response.IsSuccessStatusCode)
                {
                    this.Close();
                    mainWindow.refreshTables();
                }
                else
                {
                    Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
                }
            }
        }
    }
}
