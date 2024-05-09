namespace OpenTool.Crypto.Enum
{
    public enum Mode
    {
        /// <summary>
        /// 无模式
        /// </summary>
        NONE,

        /// <summary>
        /// 密码分组连接模式（Cipher Block Chaining）
        /// </summary>
        CBC,

        /// <summary>
        /// 密文反馈模式（Cipher Feedback）
        /// </summary>
        CFB,

        /// <summary>
        /// 计数器模式（A simplification of OFB）
        /// </summary>
        CTR,

        /// <summary>
        /// Cipher Text Stealing
        /// </summary>
        CTS,

        /// <summary>
        /// 电子密码本模式（Electronic CodeBook）
        /// </summary>
        ECB,

        /// <summary>
        /// 输出反馈模式（Output Feedback）
        /// </summary>
        OFB,

        /// <summary>
        /// Propagating Cipher Block
        /// </summary>
        PCBC
    }
}