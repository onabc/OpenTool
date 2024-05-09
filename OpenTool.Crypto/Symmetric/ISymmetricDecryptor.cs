namespace OpenTool.Crypto.Symmetric
{
    /// <summary>
    /// 对称解密器接口
    /// </summary>
    public interface ISymmetricDecryptor
    {
        /// <summary>
        /// 解密
        /// </summary>
        /// <param name="bytes">被解密的bytes</param>
        /// <returns>解密后的bytes</returns>
        byte[] Decrypt(byte[] bytes);

        /// <summary>
        /// 解密为字符串
        /// </summary>
        /// <param name="bytes">被解密的Base64字符串</param>
        /// <returns>解密后的string</returns>
        string DecryptFromBase64(string str);

        /// <summary>
        /// 解密为字符串
        /// </summary>
        /// <param name="str">被解密的Hex字符串</param>
        /// <returns>解密后的string</returns>
        string DecryptFromHex(string str);
    }
}