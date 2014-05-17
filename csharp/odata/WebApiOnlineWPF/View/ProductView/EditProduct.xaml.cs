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

namespace WebApiOnlineWPF.View.ProductView
{
    /// <summary>
    /// Interaction logic for EditProduct.xaml
    /// </summary>
    public partial class EditProduct : Window
    {

        private DataGrid dataGrid;
        private HttpClient client;
        private MainWindow mainWindow;
        private Product product;

        public EditProduct(DataGrid dataGrid, HttpClient client, MainWindow mainWindow)
        {
            InitializeComponent();
            this.dataGrid = dataGrid;
            this.client = client;
            this.mainWindow = mainWindow;
            this.product = (Product)dataGrid.Items.GetItemAt(dataGrid.SelectedIndex);
            this.Title += " " + product.Id;
            this.textBoxInput.Text = product.Name;
            this.textBoxInput.Focus();
            this.textBoxInput.SelectAll();
        }

        public void textBoxInputTextChanged(Object sender, TextChangedEventArgs e)
        {
            if (textBoxInput.Text.Equals(""))
            {
                buttonApply.IsEnabled = false;
            }
            else
            {
                buttonApply.IsEnabled = true;
            }
        }

        private void buttonApplyClick(Object sender, RoutedEventArgs e)
        {
            if (dataGrid != null && client != null)
            {
                product.Name = textBoxInput.Text;

                HttpResponseMessage response = null;
                try
                {
                    response = client.PutAsJsonAsync("greg_api/products", product).Result;

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
}
