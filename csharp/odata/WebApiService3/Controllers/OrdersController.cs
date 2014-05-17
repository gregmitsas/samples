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
    public class OrdersController : ApiController
    {
        private ModelContext context = new ModelContext();

        public HttpResponseMessage Get()
        {
            var orders = context.Orders;
            var response = Request.CreateResponse<IEnumerable<Order>>(HttpStatusCode.OK, orders);
            return response;
        }

        public HttpResponseMessage Get(int id)
        {
            var order = context.Orders.FirstOrDefault(o => o.Id == id);
            if (order == null)
            {
                var response = Request.CreateResponse(HttpStatusCode.NotFound, "Order not found");
                throw new HttpResponseException(response);
            }
            return Request.CreateResponse<Order>(HttpStatusCode.OK, order);
        }

        // POST api/orders
        public void Post(Order order)
        {
            context.Orders.Add(order);
            context.SaveChanges();
        }

        // PUT api/orders
        public void Put(Order order)
        {
            Order orderToUpdate = context.Orders.Where(o => o.Id == order.Id).FirstOrDefault();

            if (orderToUpdate != null)
            {
                context.Entry<Order>(orderToUpdate).CurrentValues.SetValues(order);
                context.SaveChanges();
            }
        }

        // DELETE api/orders/12345
        public void Delete(int id)
        {
            Order orderToRemove = context.Orders.Where(o => o.Id == id).FirstOrDefault();
            context.Orders.Remove(orderToRemove);
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
