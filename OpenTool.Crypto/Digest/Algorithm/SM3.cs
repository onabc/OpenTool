using OpenTool.Crypto.Enum;

namespace OpenTool.Crypto.Digest
{
    public class SM3 : Digester
    {
        protected override DigestAlgorithm Algorithm => DigestAlgorithm.SM3;
    }
}