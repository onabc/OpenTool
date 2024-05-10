namespace OpenTool.Crypto.Asymmetric
{
    /// <summary>
    /// 非对称解密器接口
    /// </summary>
    public interface IAsymmetricDecryptor
    {
        /// <summary>
        /// 解密
        /// </summary>
        /// <param name="bytes">被解密的bytes</param>
        /// <param name="keyType">私钥或公钥</param>
        /// <returns>解密后的bytes</returns>
        byte[] Decrypt(byte[] bytes, KeyType keyType);

        /// <summary>
        /// 解密为字符串
        /// </summary>
        /// <param name="bytes">被解密的Base64字符串</param>
        /// <param name="keyType">私钥或公钥</param>
        /// <returns>解密后的string</returns>
        string DecryptFromBase64(string str, KeyType keyType);

        /// <summary>
        /// 解密为字符串
        /// </summary>
        /// <param name="str">被解密的Hex字符串</param>
        /// <param name="keyType">私钥或公钥</param>
        /// <returns>解密后的string</returns>
        string DecryptFromHex(string str, KeyType keyType);
    }
}