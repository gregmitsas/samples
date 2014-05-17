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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net.Http;
using System.Net.Http.Headers;
using WebApiModel.Model;

namespace WebApiOnlineWPF.View
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private HttpClient client;

        public MainWindow()
        {
            InitializeComponent();
            client = initializeClient("http://localhost:1879");
            serviceCall(client);
        }

        private void buttonClick(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            switch (button.Name)
            {
                case "buttonAdd":
                    add(tabControl.SelectedIndex);
                    break;
                case "buttonEdit":
                    edit(tabControl.SelectedIndex);
                    break;
                case "buttonDelete":
                    delete(tabControl.SelectedIndex);
                    break;
            }
        }

        private void add(int index)
        {
            switch (index)
            {
                case 0:
                    new CustomerView.AddCustomer(dataGridCustomers, client, this).Show();
                    break;
                case 1:
                    new ProductView.AddProduct(dataGridProducts, client, this).Show();
                    break;
                case 2:
                    new OrderView.AddOrder(dataGridOrders, client, this).Show();
                    break;
            }
        }

        private void edit(int index)
        {
            switch (index)
            {
                case 0:
                    new CustomerView.EditCustomer(dataGridCustomers, client, this).Show();
                    break;
                case 1:
                    new ProductView.EditProduct(dataGridProducts, client, this).Show();
                    break;
                case 2:
                    MessageBox.Show("Edit Order");
                    break;
            }
        }

        private void delete(int index)
        {
            switch (index)
            {
                case 0:
                    MessageBox.Show("Delete Customer");
                    break;
                case 1:
                    removeProduct(dataGridProducts.SelectedIndex);
                    break;
                case 2:
                    MessageBox.Show("Delete Order");
                    break;
            }
        }

        private void removeProduct(int index)
        {
            if (index > -1)
            {
                HttpResponseMessage response = client.DeleteAsync("greg_api/products/" + (index+1)).Result;
                if (response.IsSuccessStatusCode)
                {
                    refreshTables();
                }
                else
                {
                    Console.WriteLine("{0} ({1})", (int)response.StatusCode, response.ReasonPhrase);
                }
            }
        }

        private HttpClient initializeClient(string uri)
        {
            try
            {
                client = new HttpClient();
                client.BaseAddress = new Uri(uri);
                return client;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                return null;
            }
            finally
            {
                client = null;
            }
        }

        private void serviceCall(HttpClient client)
        {
            if (client != null)
            {
                HttpResponseMessage response1 = null, response2 = null, response3 = null;
                try
                {
                    client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
                    response1 = client.GetAsync("greg_api/customers").Result;
                    response2 = client.GetAsync("greg_api/orders").Result;
                    response3 = client.GetAsync("greg_api/products").Result;

                    if (response1.IsSuccessStatusCode)
                    {
                        var customers = response1.Content.ReadAsAsync<IEnumerable<Customer>>().Result;
                        if (customers != null)
                        {
                            dataGridCustomers.ItemsSource = customers;
                        }
                    }

                    if (response2.IsSuccessStatusCode)
                    {
                        var orders = response2.Content.ReadAsAsync<IEnumerable<Order>>().Result;
                        if (orders != null)
                        {
                            dataGridOrders.ItemsSource = orders;
                        }
                    }

                    if (response3.IsSuccessStatusCode)
                    {
                        var products = response3.Content.ReadAsAsync<IEnumerable<Product>>().Result;
                        if (products != null)
                        {
                            dataGridProducts.ItemsSource = products;
                        }
                    }
                }
                catch(Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
                finally
                {
                    response1.Dispose();
                    response2.Dispose();
                    response3.Dispose();
                }
            }
        }

        public void refreshTables()
        {
            if (client != null)
            {
                HttpResponseMessage response1 = null, response2 = null, response3 = null;
                try
                {
                    response1 = client.GetAsync("greg_api/customers").Result;
                    response2 = client.GetAsync("greg_api/orders").Result;
                    response3 = client.GetAsync("greg_api/products").Result;

                    if (response1.IsSuccessStatusCode)
                    {
                        var customers = response1.Content.ReadAsAsync<IEnumerable<Customer>>().Result;
                        if (customers != null)
                        {
                            dataGridCustomers.ItemsSource = customers;
                        }
                    }

                    if (response2.IsSuccessStatusCode)
                    {
                        var orders = response2.Content.ReadAsAsync<IEnumerable<Order>>().Result;
                        if (orders != null)
                        {
                            dataGridOrders.ItemsSource = orders;
                        }
                    }

                    if (response3.IsSuccessStatusCode)
                    {
                        var products = response3.Content.ReadAsAsync<IEnumerable<Product>>().Result;
                        if (products != null)
                        {
                            dataGridProducts.ItemsSource = products;
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
                    response3.Dispose();
                }
            }
        }
    }
}
