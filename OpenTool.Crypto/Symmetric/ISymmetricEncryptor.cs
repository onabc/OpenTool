namespace OpenTool.Crypto.Symmetric
{
    /// <summary>
    /// 对称加密器接口
    /// </summary>
    public interface ISymmetricEncryptor
    {
        /// <summary>
        /// 加密
        /// </summary>
        /// <param name="data">被加密的bytes</param>
        /// <returns>加密后的bytes</returns>
        byte[] Encrypt(byte[] data);

        /// <summary>
        /// 加密
        /// </summary>
        /// <param name="data">被加密的字符串</param>
        /// <returns>加密后的Base64字符串</returns>
        string EncryptToBase64(string str);

        /// <summary>
        /// 加密
        /// </summary>
        /// <param name="data">被加密的字符串</param>
        /// <returns>加密后的Hex字符串</returns>
        string EncryptToHex(string str);
    }
}