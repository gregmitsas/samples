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
    /// Interaction logic for AddAddress.xaml
    /// </summary>
    public partial class AddAddress : Window
    {
        private ComboBox comboBox;

        public AddAddress(ComboBox comboBox)
        {
            InitializeComponent();
            this.comboBox = comboBox;
        }

        private void buttonClick(object sender, RoutedEventArgs e)
        {
            switch (((Button)sender).Name)
            {
                default:
                    insertAddress();
                    break;
            }
        }

        private void insertAddress()
        {
            if (comboBox != null)
            {
                if (textBoxStreet.Text.Equals("") || textBoxCity.Text.Equals(""))
                {
                    MessageBox.Show("All fields must be filled.");
                    return;
                }
                comboBox.Items.Add(new Address() { Id = comboBox.Items.Count + 1, Street = textBoxStreet.Text, City = textBoxCity.Text });
                this.Close();
            }
        }
    }
}
