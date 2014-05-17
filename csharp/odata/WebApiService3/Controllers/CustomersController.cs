using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebApiModel.Model;
using WebApiModel.Model.Context;

namespace WebApiService3.Controllers
{
    public class CustomersController : ApiController
    {
        private ModelContext context = new ModelContext();
        
        public HttpResponseMessage Get()
        {
            var customers = context.Customers;
            var response = Request.CreateResponse<IEnumerable<Customer>>(HttpStatusCode.OK, customers);
            return response;
        }

        public HttpResponseMessage Get(int id)
        {
            var customer = context.Customers.FirstOrDefault(e => e.Id == id);
            if (customer == null)
            {
                var response = Request.CreateResponse(HttpStatusCode.NotFound, "Customer not found");
                throw new HttpResponseException(response);
            }
            return Request.CreateResponse<Customer>(HttpStatusCode.OK, customer);
        }

        // POST api/products
        public void Post(Customer customer)
        {
            context.Customers.Add(customer);
            context.SaveChanges();
        }

        // PUT api/products
        public void Put(Customer customer)
        {
            Customer customerToUpdate = context.Customers.Where(c => c.Id == customer.Id).FirstOrDefault();

            if (customerToUpdate != null)
            {
                context.Entry<Customer>(customerToUpdate).CurrentValues.SetValues(customer);
                context.SaveChanges();
            }
        }

        // DELETE api/employees/12345
        public void Delete(int id)
        {
            Customer customerToRemove = context.Customers.Where(c => c.Id == id).FirstOrDefault();
            context.Customers.Remove(customerToRemove);
            context.SaveChanges();
        }

        protected override void Dispose(bool disposing)
        {
            if (context != null)
                context.Dispose();
            base.Dispose(disposing);
        }
    }
}
