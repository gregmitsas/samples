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
using WebApiModel.Model;
using System.Net.Http;
using System.Net.Http.Headers;

namespace WebApiOnlineWPF.View.ProductView
{
    /// <summary>
    /// Interaction logic for AddProduct.xaml
    /// </summary>
    public partial class AddProduct : Window
    {
        private DataGrid dataGrid;
        private HttpClient client;
        private MainWindow mainWindow;

        public AddProduct(DataGrid dataGrid, HttpClient client, MainWindow mainWindow)
        {
            InitializeComponent();
            this.dataGrid = dataGrid;
            this.client = client;
            this.mainWindow = mainWindow;
            this.textBoxInput.Focus();
        }

        public void textBoxTextChanged(Object sender, TextChangedEventArgs e)
        {
            if (textBoxInput.Text.Equals(""))
            {
                buttonInsert.IsEnabled = false;
            }
            else
            {
                buttonInsert.IsEnabled = true;
            }
        }

        public void buttonClick(Object sender, RoutedEventArgs e)
        {
            if (dataGrid != null && client != null)
            {
                Product product = new Product();
                product.Id = dataGrid.Items.Count + 1;
                product.Name = textBoxInput.Text;

                HttpResponseMessage response = client.PostAsJsonAsync("greg_api/products", product).Result;
                
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
