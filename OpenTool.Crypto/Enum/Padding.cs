namespace OpenTool.Crypto.Enum
{
    /// <summary>
    /// 补码方式
    /// </summary>
    public enum Padding
    {
        /// <summary>
        /// 无补码
        /// </summary>
        NoPadding,

        /// <summary>
        /// 0补码，即不满block长度时使用0填充
        /// </summary>
        ZeroPadding,

        /// <summary>
        /// This padding for block ciphers is described in 5.2 Block Encryption Algorithms in the W3C's "XML Encryption Syntax and Processing" document.
        /// </summary>
        ISO10126Padding,

        /// <summary>
        /// Optimal Asymmetric Encryption Padding scheme defined in PKCS1
        /// </summary>
        OAEPPadding,

        /// <summary>
        /// The padding scheme described in PKCS #1, used with the RSA algorithm
        /// </summary>
        PKCS1Padding,

        /// <summary>
        /// The padding scheme described in RSA Laboratories, "PKCS #5: Password-Based Encryption Standard," version 1.5, November 1993.
        /// </summary>
        PKCS5Padding,

        /// <summary>
        ///
        /// </summary>
        PKCS7Padding,

        /// <summary>
        /// The padding scheme defined in the SSL Protocol Version 3.0, November 18, 1996, section 5.2.3.2 (CBC block cipher)
        /// </summary>
        SSL3Padding
    }
}