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
using System.Net.Http.Headers;
using WebApiModel.Model;

namespace WebApiOnlineWPF.View.CustomerView
{
    /// <summary>
    /// Interaction logic for EditCustomer.xaml
    /// </summary>
    public partial class EditCustomer : Window
    {

        private DataGrid dataGrid;
        private HttpClient client;
        private MainWindow mainWindow;
        private Customer customer;

        public EditCustomer(DataGrid dataGrid, HttpClient client, MainWindow mainWindow)
        {
            InitializeComponent();
            this.dataGrid = dataGrid;
            this.client = client;
            this.mainWindow = mainWindow;
            this.customer = (Customer)dataGrid.Items.GetItemAt(dataGrid.SelectedIndex);
            this.textBoxFirstName.Text = customer.FirstName;
            this.textBoxLastName.Text = customer.LastName;
            this.textBoxEmail.Text = customer.Email;
            this.textBoxPhone.Text = customer.Phone;
            this.textBoxCreditCard.Text = customer.CreditCard;
            this.comboBoxAddresses.ItemsSource = this.customer.Addresses;
            this.textBoxFirstName.Focus();
            this.textBoxFirstName.SelectAll();
        }

        public void textBoxTextChanged(Object sender, TextChangedEventArgs e)
        {
            if (((TextBox)sender).Text.Equals(""))
            {
                buttonApply.IsEnabled = false;
            }
            else
            {
                buttonApply.IsEnabled = true;
            }
        }

        private void buttonClick(Object sender, RoutedEventArgs e)
        {
            if (dataGrid != null && client != null)
            {
                switch (((Button)sender).Name)
                {
                    case "buttonEditAddress":
                        new EditAddress(this).Show();
                        break;
                    case "buttonApply":
                        applyChanges();
                        break;
                }
            }
        }

        private void applyChanges()
        {
            customer.FirstName = textBoxFirstName.Text;
            customer.LastName = textBoxLastName.Text;
            customer.Email = textBoxEmail.Text;
            customer.Phone = textBoxPhone.Text;
            customer.CreditCard = textBoxPhone.Text;

            HttpResponseMessage response = null;

            try
            {
                response = client.PutAsJsonAsync("greg_api/customers", customer).Result;

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
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

    }
}
