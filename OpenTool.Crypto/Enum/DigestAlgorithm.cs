using System.ComponentModel;

namespace OpenTool.Crypto.Enum
{
    public enum DigestAlgorithm
    {
        [Description("MD2")]
        MD2,

        [Description("MD5")]
        MD5,

        [Description("SHA-1")]
        SHA1,

        [Description("SHA-256")]
        SHA256,

        [Description("SHA-384")]
        SHA384,

        [Description("SHA-512")]
        SHA512,

        [Description("SM3")]
        SM3
    }
}