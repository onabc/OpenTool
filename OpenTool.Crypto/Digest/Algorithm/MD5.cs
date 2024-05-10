using OpenTool.Crypto.Enum;

namespace OpenTool.Crypto.Digest
{
    public class MD5 : Digester
    {
        protected override DigestAlgorithm Algorithm => DigestAlgorithm.MD5;
    }
}