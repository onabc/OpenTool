namespace OpenTool.Crypto.Asymmetric
{
    /// <summary>
    /// 非对称加密器接口
    /// </summary>
    public interface IAsymmetricEncryptor
    {
        /// <summary>
        /// 加密
        /// </summary>
        /// <param name="bytes">被加密的bytes</param>
        /// <param name="keyType">私钥或公钥</param>
        /// <returns>加密后的bytes</returns>
        byte[] Encrypt(byte[] bytes, KeyType keyType);

        /// <summary>
        /// 加密为Hex字符串
        /// </summary>
        /// <param name="str"></param>
        /// <param name="keyType"></param>
        /// <returns></returns>
        string EncryptToHex(string str, KeyType keyType);

        /// <summary>
        /// 加密为Base64字符串
        /// </summary>
        /// <param name="str"></param>
        /// <param name="keyType"></param>
        /// <returns></returns>
        string EncryptToBase64(string str, KeyType keyType);
    }
}