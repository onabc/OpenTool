using OpenTool.Core.Model;
using OpenTool.Core.Util;

namespace Test.Core.Util
{
    public class MailUtilTest
    {
        private MailAccount _mailAccount;

        [SetUp]
        public void Init()
        {
            _mailAccount = new MailAccount
            {
                Host = "smtp.qq.com",
                Port = 587,
                EnableSsl = false,
                Username = "10346083",
                Password = "",
            };
        }

        [Test]
        public void Send_Test()
        {
            string from = "10346083@qq.com";
            string recipients = "hesbiu@gmail.com";
            string? subject = "测试邮件推送";
            string? body = "这里是邮件内容😊😊";
            MailUtil mailUtil = new MailUtil(_mailAccount);
            mailUtil.Send(from, recipients, subject, body);
            Thread.Sleep(5000);
        }
    }
}