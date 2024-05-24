using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OpenTool.Core.Model
{
    public class MailAccount
    {
        /// <summary>
        /// SMTP服务器域名
        /// </summary>
        public string Host { get; set; }

        /// <summary>
        /// SMTP服务端口
        /// </summary>
        public int Port { get; set; }

        /// <summary>
        /// 用户名
        /// </summary>
        public string Username { get; set; }

        /// <summary>
        /// 发件人邮箱密码
        /// </summary>
        public string Password { get; set; }

        /// <summary>
        /// 是否使用STARTTLS安全连接
        /// </summary>
        public bool StarttlsEnable { get; set; } = false;

        /// <summary>
        /// 是否使用SSL安全连接
        /// </summary>
        public bool EnableSsl { get; set; } = true;
    }
}