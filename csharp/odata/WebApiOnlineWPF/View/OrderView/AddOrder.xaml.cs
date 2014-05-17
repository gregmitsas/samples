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

namespace WebApiOnlineWPF.View.OrderView
{
    /// <summary>
    /// Interaction logic for AddOrder.xaml
    /// </summary>
    public partial class AddOrder : Window
    {

        private DataGrid dataGrid;
        private HttpClient client;
        private MainWindow mainWindow;

        public AddOrder(DataGrid dataGrid, HttpClient client, MainWindow mainWindow)
        {
            InitializeComponent();
            serviceCall(client);
            this.dataGrid = dataGrid;
            this.client = client;
            this.mainWindow = mainWindow;
        }

        private void serviceCall(HttpClient client)
        {
            if (client != null)
            {
                HttpResponseMessage response1 = null, response2 = null;
                try
                {
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    response1 = client.GetAsync("greg_api/customers").Result;
                    response2 = client.GetAsync("greg_api/products").Result;

                    if (response1.IsSuccessStatusCode)
                    {
                        var customers = response1.Content.ReadAsAsync<IEnumerable<Customer>>().Result;
                        if (customers != null)
                        {
                            comboBoxCustomers.ItemsSource = customers;
                        }
                    }

                    if (response2.IsSuccessStatusCode)
                    {
                        var products = response2.Content.ReadAsAsync<IEnumerable<Product>>().Result;
                        if (products != null)
                        {
                            comboBoxProducts.ItemsSource = products;
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
                finally
                {
                    response1.Dispose();
                    response2.Dispose();
                }
            }
        }

        private void comboBoxCustomersSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Customer selectedCustomer = null;

            try
            {
                selectedCustomer = (Customer)((ComboBox)sender).SelectedItem;
                comboBoxShipAddresses.ItemsSource = selectedCustomer.Addresses;
                comboBoxBillAddresses.ItemsSource = selectedCustomer.Addresses;
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                selectedCustomer = null;
            }
        }

        private void buttonClick(object sender, RoutedEventArgs e)
        {
            switch (((Button)sender).Name)
            {
                default:
                    createOrder();
                    break;
            }
        }

        private void createOrder()
        {
            Customer customer = null;
            Address shippingAddress = null;
            Address billingAddress = null;
            Product product = null;
            Random random = null;
            Order order = null;
            OrderDetails orderDetails = null;
            HttpResponseMessage response1 = null, response2 = null;

            try
            {
                customer = (Customer)comboBoxCustomers.SelectedItem;
                shippingAddress = (Address)comboBoxShipAddresses.SelectedItem;
                billingAddress = (Address)comboBoxBillAddresses.SelectedItem;
                product = (Product)comboBoxProducts.SelectedItem;
                random = new Random();
                int id = dataGrid.Items.Count + 1;

                order = new Order() { Id = id, Customer = customer, Date = DateTime.Now, ShipAddress = shippingAddress, BillingAddress = billingAddress };
                orderDetails = new OrderDetails() { Id = id, Order = order, Product = product, Price = random.Next(1, 100) };

                response1 = client.PostAsJsonAsync("greg_api/orders", order).Result;
                response2 = client.PostAsJsonAsync("greg_api/orderdetais", orderDetails).Result;

                if (response1.IsSuccessStatusCode && response2.IsSuccessStatusCode)
                {
                    this.Close();
                    mainWindow.refreshTables();
                }
                else
                {
                    if (!response1.IsSuccessStatusCode) Console.WriteLine("{0} ({1})", (int)response1.StatusCode, response1.ReasonPhrase);
                    if (!response2.IsSuccessStatusCode) Console.WriteLine("{0} ({1})", (int)response2.StatusCode, response2.ReasonPhrase);
                }
            }
            catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                customer = null;
                shippingAddress = null;
                billingAddress = null;
                product = null;
                random = null;
                order = null;
                orderDetails = null;
                if (response1 != null) response1.Dispose();
                if (response2 != null) response2.Dispose();
            }
        }
    }
}
