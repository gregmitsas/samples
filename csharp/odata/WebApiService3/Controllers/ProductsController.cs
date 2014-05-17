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
    public class ProductsController : ApiController
    {
        private ModelContext context = new ModelContext();

        // GET api/employees
        public HttpResponseMessage Get()
        {
            var products = context.Products;
            var response = Request.CreateResponse<IEnumerable<Product>>(HttpStatusCode.OK, products);
            return response;
        }

        // GET api/employees/1
        public HttpResponseMessage Get(int id)
        {
            var product = context.Products.FirstOrDefault(p => p.Id == id);
            if (product == null)
            {
                var response = Request.CreateResponse(HttpStatusCode.NotFound, "Product not found");
                throw new HttpResponseException(response);
            }
            return Request.CreateResponse<Product>(HttpStatusCode.OK, product);
        }

        // POST api/products
        public void Post(Product product)
        {
            context.Products.Add(product);
            context.SaveChanges();
        }

        // PUT api/products
        public void Put(Product product)
        {
            Product prodToUpdate = context.Products.Where(p => p.Id == product.Id).FirstOrDefault();

            if (prodToUpdate != null)
            {
                context.Entry<Product>(prodToUpdate).CurrentValues.SetValues(product);
                context.SaveChanges();
            }
        }

        // DELETE api/employees/12345
        public void Delete(int id)
        {
            Product prodToRemove = context.Products.Where(p => p.Id == id).FirstOrDefault();
            context.Products.Remove(prodToRemove);
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
