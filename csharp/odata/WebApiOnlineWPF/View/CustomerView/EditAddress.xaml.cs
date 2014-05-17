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

namespace WebApiOnlineWPF.View.CustomerView
{
    /// <summary>
    /// Interaction logic for EditAddress.xaml
    /// </summary>
    public partial class EditAddress : Window
    {
        private EditCustomer editCustomer;

        public EditAddress(EditCustomer editCustomer)
        {
            InitializeComponent();
            this.editCustomer = editCustomer;
            textBoxStreet.Text = ((Address)editCustomer.comboBoxAddresses.SelectedItem).Street;
            textBoxCity.Text = ((Address)editCustomer.comboBoxAddresses.SelectedItem).City;
        }

        private void buttonClick(object sender, RoutedEventArgs e)
        {
            switch (((Button)sender).Name)
            {
                default:
                    applyChanges();
                    break;
            }
        }

        private void applyChanges()
        {
            if (textBoxStreet.Text.Equals("") || textBoxCity.Text.Equals(""))
            {
                MessageBox.Show("All fields must be filled.");
                return;
            }
            ((Address)editCustomer.comboBoxAddresses.SelectedItem).Street = textBoxStreet.Text;
            ((Address)editCustomer.comboBoxAddresses.SelectedItem).City = textBoxCity.Text;
            this.Close();
        }
    }
}
