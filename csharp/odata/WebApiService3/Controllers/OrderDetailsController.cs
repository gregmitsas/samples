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
    public class OrderDetailsController : ApiController
    {

        private ModelContext context = new ModelContext();

        public HttpResponseMessage Get()
        {
            var orderDetails = context.OrderDetails;
            var response = Request.CreateResponse<IEnumerable<OrderDetails>>(HttpStatusCode.OK, orderDetails);
            return response;
        }

        public HttpResponseMessage Get(int id)
        {
            var orderDetails = context.OrderDetails.FirstOrDefault(o => o.Id == id);
            if (orderDetails == null)
            {
                var response = Request.CreateResponse(HttpStatusCode.NotFound, "Order Details not found");
                throw new HttpResponseException(response);
            }
            return Request.CreateResponse<OrderDetails>(HttpStatusCode.OK, orderDetails);
        }

        // POST api/orderdetails
        public void Post(OrderDetails orderDetails)
        {
            context.OrderDetails.Add(orderDetails);
            context.SaveChanges();
        }

        // PUT api/orderdetails
        public void Put(OrderDetails orderDetails)
        {
            OrderDetails orderDetailsToUpdate = context.OrderDetails.Where(o => o.Id == orderDetails.Id).FirstOrDefault();

            if (orderDetailsToUpdate != null)
            {
                context.Entry<OrderDetails>(orderDetailsToUpdate).CurrentValues.SetValues(orderDetails);
                context.SaveChanges();
            }
        }

        // DELETE api/orderdetails/12345
        public void Delete(int id)
        {
            OrderDetails orderDetailsToRemove = context.OrderDetails.Where(o => o.Id == id).FirstOrDefault();
            context.OrderDetails.Remove(orderDetailsToRemove);
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
