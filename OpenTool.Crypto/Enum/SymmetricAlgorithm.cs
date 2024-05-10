using System.ComponentModel;

namespace OpenTool.Crypto.Enum
{
    public enum SymmetricAlgorithm
    {
        /// <summary>
        /// AES加密方式
        /// </summary>
        [Description("AES")]
        AES,

        /// <summary>
        /// DES加密方式
        /// </summary>
        [Description("DES")]
        DES,

        /// <summary>
        /// 国密sm4
        /// </summary>
        [Description("SM4")]
        SM4,
    }
}