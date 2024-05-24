using OpenTool.Core.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

namespace OpenTool.Core.Util
{
    public class MailUtil
    {
        private string callbackToken = "SUCCESS";
        private SmtpClient _smtpClient;

        public MailUtil(MailAccount account)
        {
            _smtpClient = new SmtpClient
            {
                UseDefaultCredentials = false,
                EnableSsl = account.EnableSsl,
                Host = account.Host,
                Port = account.Port,
                Credentials = new NetworkCredential(account.Username, account.Password),
                DeliveryMethod = SmtpDeliveryMethod.Network
            };
            _smtpClient.SendCompleted += SendCompletedCallback;
        }

        public void Send(string from, string recipients, string? subject, string? body)
        {
            MailMessage mailMessage = new MailMessage(from, recipients, subject, body);
            _smtpClient.SendAsync(mailMessage, callbackToken);
        }

        private void SendCompletedCallback(object sender, AsyncCompletedEventArgs e)
        {
        }
    }
}